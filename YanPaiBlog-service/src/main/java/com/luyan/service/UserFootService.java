package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.UserFoot;

public interface UserFootService extends IService<UserFoot> {
    // 获取用户 uid 的文章被点赞数
    long getPraisedNumByUser(int uid);

    // 获取用户 uid 的文章被阅读数
    long getReadNumByUser(int uid);

    // 获取用户 uid 的文章被收藏数
    long getCollectionNumByUser(int uid);

    // 获取文章被阅读数
    long getReadNumByArticle(int articleId);

    // 获取文章被收藏数
    long getCollectionNumByArticle(int articleId);
}
