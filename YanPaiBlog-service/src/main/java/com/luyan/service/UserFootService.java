package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.UserFoot;
import lombok.Data;

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

    // 获取用户 uid 是否阅读、点赞、收藏了文章 articleId
    UserState getUserStatInArticle(int uid, int articleId);

    // 设置当前用户对文章的阅读状态
    void setArticleReadState(int articleId, boolean readState);

    // 设置当前用户对文章的点赞状态
    void setArticlePraiseState(int articleId, boolean praiseState);

    // 设置当前用户对文章的收藏状态
    void setArticleCollectionState(int articleId, boolean collectionState);

    @Data
    class UserState {
        private boolean hasRead;
        private boolean hasPraised;
        private boolean hasCollection;
    }
}
