package com.luyan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luyan.entity.domain.UserInfo;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    // 获取 uid 的粉丝列表
    List<UserInfo> getFansList(int uid);

    // 获取 uid 关注的人组成的列表
    List<UserInfo> getFollowList(int uid);
}
