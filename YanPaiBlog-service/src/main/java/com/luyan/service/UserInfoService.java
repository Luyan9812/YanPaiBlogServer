package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.UserInfo;

public interface UserInfoService extends IService<UserInfo> {
    // 随机生成用户信息并保存
    void saveUserInfo(int uid);

    // 根据用户 id 获取用户信息
    UserInfo getUserInfoByUid(int uid);
}
