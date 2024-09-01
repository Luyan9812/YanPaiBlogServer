package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.UserFoot;

public interface UserFootService extends IService<UserFoot> {
    // 获取文章被点赞数
    long getPraisedNum(int uid);

    // 获取文章被阅读数
    long getReadNum(int uid);

    // 获取文章被收藏数
    long getCollectionNum(int uid);
}
