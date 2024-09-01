package com.luyan.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.UserInfo;
import com.luyan.entity.dto.UserInfoDto;
import com.luyan.mapper.UserInfoMapper;
import com.luyan.service.NotifyMsgService;
import com.luyan.service.UserInfoService;
import com.luyan.service.UserRelationService;
import com.luyan.utils.BaseContext;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private NotifyMsgService notifyMsgService;
    @Resource
    private UserRelationService userRelationService;

    @Override
    public void saveUserInfo(int uid) {
        UserInfo info = new UserInfo();
        info.setUserId(uid);
        info.setNickName("用户_" + RandomUtil.randomNumbers(6));
        int idx = RandomUtil.randomInt(1, 26);
        info.setPhoto(String.format("/headers/00%s.png", idx < 10 ? "0" + idx : idx));
        userInfoMapper.insert(info);
    }

    @Override
    public UserInfo getUserInfoByUid(int uid) {
        LambdaQueryWrapper<UserInfo> userInfoWrapper = new LambdaQueryWrapper<>();
        userInfoWrapper.eq(UserInfo::getUserId, uid);
        return userInfoMapper.selectOne(userInfoWrapper);
    }

    @Override
    public Map<String, Object> getIndexUserInfo() {
        Integer uid = BaseContext.getCurrentId();
        String photo = getUserInfoByUid(uid).getPhoto();
        long msgCount = notifyMsgService.getAllUnreadMsgCount(uid);
        Map<String, Object> map = new HashMap<>();
        map.put("photo", photo);
        map.put("msgCount", msgCount);
        return map;
    }

    @Override
    public UserInfoDto getUserInfo() {
        Integer uid = BaseContext.getCurrentId();
        UserInfoDto dto = new UserInfoDto();
        UserInfo userInfo = getUserInfoByUid(uid);
        long fansNums = userRelationService.getFansNumsByUid(uid);
        long followedNums = userRelationService.getFollowedNumsByUid(uid);
        LocalDate localDate = userInfo.getCreateTime().toLocalDate();
        long jointDays = Duration.between(localDate.atTime(LocalTime.MIN), LocalDateTime.now()).toDays();
        dto.setFansNums(fansNums);
        dto.setFollowedNums(followedNums);
        dto.setJointDays(jointDays + 1);
        BeanUtils.copyProperties(userInfo, dto);
        return dto;
    }
}
