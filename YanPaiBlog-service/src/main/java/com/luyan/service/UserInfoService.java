package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.UserInfo;
import com.luyan.entity.dto.UserInfoDto;

import java.util.Map;

public interface UserInfoService extends IService<UserInfo> {
    // 随机生成用户信息并保存
    void saveUserInfo(int uid);

    // 根据用户 id 获取用户信息
    UserInfo getUserInfoByUid(int uid);

    // 获取首页需要的用户信息：仅包含头像和未读消息数量
    Map<String, Object> getIndexUserInfo();

    // 获取用户的详细信息
    UserInfoDto getUserInfo();
}
