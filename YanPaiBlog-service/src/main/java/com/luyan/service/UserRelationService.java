package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.UserRelation;

public interface UserRelationService extends IService<UserRelation> {
    // 获取 uid 对应用户的粉丝数
    long getFansNumByUid(int uid);

    // 获取 uid 关注的人数
    long getFollowedNumByUid(int uid);

    // 返回 uid 是否关注了 authorId
    boolean hasFollowed(int uid, int authorId);

    // uid 关注 authorId
    void follow(int uid, int authorId);

    // uid 取消关注 authorId
    void unFollow(int uid, int authorId);
}
