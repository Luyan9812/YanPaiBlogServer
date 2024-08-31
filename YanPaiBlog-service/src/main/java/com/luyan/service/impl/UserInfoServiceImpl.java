package com.luyan.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.UserInfo;
import com.luyan.mapper.UserInfoMapper;
import com.luyan.service.UserInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

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
}
