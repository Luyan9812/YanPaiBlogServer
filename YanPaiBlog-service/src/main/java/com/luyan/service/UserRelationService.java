package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.UserRelation;

public interface UserRelationService extends IService<UserRelation> {
    // 获取 uid 对应用户的粉丝数
    long getFansNumsByUid(int uid);

    // 获取 uid 对应用户的关注数
    long getFollowedNumsByUid(int uid);
}
