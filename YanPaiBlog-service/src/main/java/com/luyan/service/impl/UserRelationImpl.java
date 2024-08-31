package com.luyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.UserRelation;
import com.luyan.mapper.UserRelationMapper;
import com.luyan.service.UserRelationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserRelationImpl extends ServiceImpl<UserRelationMapper, UserRelation>
        implements UserRelationService {

    @Resource
    private UserRelationMapper userRelationMapper;


    @Override
    public long getFansNumsByUid(int uid) {
        LambdaQueryWrapper<UserRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRelation::getFollowUserId, uid);
        return userRelationMapper.selectCount(wrapper);
    }

    @Override
    public long getFollowedNumsByUid(int uid) {
        LambdaQueryWrapper<UserRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRelation::getUserId, uid);
        return userRelationMapper.selectCount(wrapper);
    }
}
