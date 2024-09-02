package com.luyan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luyan.entity.domain.UserInfo;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    List<UserInfo> getFansList(int uid);
    List<UserInfo> getFollowList(int uid);
}
