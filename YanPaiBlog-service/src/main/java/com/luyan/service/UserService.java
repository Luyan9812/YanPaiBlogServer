package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.User;
import com.luyan.entity.dto.UserInfoDto;

import java.util.Locale;
import java.util.Map;

/**
* @author luyan
* @description 针对表【user(用户登录表)】的数据库操作Service
* @createDate 2024-08-24 11:51:32
*/
public interface UserService extends IService<User> {
    String login(String username, String password);
    void register(String username, String password);

    // 获取用户成就信息：发布文章数、文章被点赞数、文章被阅读数、文章被收藏数
    Map<String, Long> getAchievement();
}
