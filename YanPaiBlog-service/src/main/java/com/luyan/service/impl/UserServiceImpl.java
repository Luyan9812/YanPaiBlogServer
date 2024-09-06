package com.luyan.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.User;
import com.luyan.entity.domain.UserInfo;
import com.luyan.entity.dto.UserInfoDto;
import com.luyan.entity.exception.ServiceException;
import com.luyan.entity.utils.ResultCodeEnum;
import com.luyan.mapper.UserMapper;
import com.luyan.service.*;
import com.luyan.utils.BaseContext;
import com.luyan.utils.JwtHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
* @author luyan
* @description 针对表【user(用户登录表)】的数据库操作Service实现
* @createDate 2024-08-24 11:51:32
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Value("${global.salt}")
    private String salt;

    @Resource
    private JwtHelper jwtHelper;

    @Resource
    private UserMapper userMapper;
    @Resource
    private ArticleService articleService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserFootService userFootService;
    @Resource
    private UserRelationService userRelationService;

    // 对密码加盐加密
    private String generatePwd(String pwd, int saltPos) {
        StringBuilder builder = new StringBuilder(pwd);
        builder.insert(saltPos, salt);
        return DigestUtil.md5Hex(builder.toString());
    }

    // 根据用户名查询用户
    private User getUserByUsername(String username) {
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUsername, username);
        return userMapper.selectOne(userWrapper);
    }

    @Override
    public String login(String username, String password) {
        User user = getUserByUsername(username);
        if (user == null) {  // 用户名不存在
            throw new ServiceException(ResultCodeEnum.USERNAME_NOT_FOUND_ERROR);
        }
        String pwd = generatePwd(password, user.getSaltPos());
        if (!pwd.equals(user.getPassword())) {
            throw new ServiceException(ResultCodeEnum.PASSWORD_ERROR);
        }
        return jwtHelper.createToken(user.getId());
    }

    @Override
    public void register(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {  // 用户名被占用
            throw new ServiceException(ResultCodeEnum.USERNAME_EXISTS_ERROR);
        }
        user = new User();
        user.setUsername(username);
        user.setSaltPos(RandomUtil.randomInt(0, password.length()));
        user.setPassword(generatePwd(password, user.getSaltPos()));
        userMapper.insert(user);

        // 添加一个默认的用户信息
        userInfoService.saveUserInfo(user.getId());
    }

    @Override
    public Map<String, Long> getAchievement() {
        int uid = BaseContext.getCurrentId();
        long publishNum = articleService.getPublishNum(uid);
        long praiseNum = userFootService.getPraisedNumByUser(uid);
        long readNum = userFootService.getReadNumByUser(uid);
        long collectionNum = userFootService.getCollectionNumByUser(uid);
        return new HashMap<>() {
            {
                put("publishNum", publishNum);
                put("praiseNum", praiseNum);
                put("readNum", readNum);
                put("collectionNum", collectionNum);
            }
        };
    }

    @Override
    public UserInfoDto getAuthorInfo(int authorId) {
        UserInfoDto dto = new UserInfoDto();
        UserInfo info = userInfoService.getUserInfoByUid(authorId);
        BeanUtils.copyProperties(info, dto);
        LocalDate localDate = info.getCreateTime().toLocalDate();
        long jointDays = Duration.between(localDate.atTime(LocalTime.MIN), LocalDateTime.now()).toDays();
        dto.setJointDays(jointDays + 1);
        dto.setPublishNum(articleService.getPublishNum(authorId));
        dto.setPraiseNum(userFootService.getPraisedNumByUser(authorId));
        dto.setCollectionNum(userFootService.getCollectionNumByUser(authorId));
        dto.setFansNum(userRelationService.getFansNumByUid(authorId));
        Integer uid = BaseContext.getCurrentId();
        log.info("userinfo: {}, {}", uid, authorId);
        if (uid != null) {
            dto.setMyself(uid.equals(authorId));
            dto.setHasFollowed(userRelationService.hasFollowed(uid, authorId));
        }
        return dto;
    }

    @Override
    public void changeFollowState(int authorId, boolean followState) {
        int uid = BaseContext.getCurrentId();
        if (followState) {
            userRelationService.follow(uid, authorId);
        } else {
            userRelationService.unFollow(uid, authorId);
        }
    }
}




