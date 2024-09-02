package com.luyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.UserRelation;
import com.luyan.entity.exception.ServiceException;
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
    public long getFansNumByUid(int uid) {
        LambdaQueryWrapper<UserRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRelation::getUserId, uid);
        return userRelationMapper.selectCount(wrapper);
    }

    @Override
    public long getFollowedNumByUid(int uid) {
        LambdaQueryWrapper<UserRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRelation::getFollowUserId, uid);
        return userRelationMapper.selectCount(wrapper);
    }

    @Override
    public boolean hasFollowed(int uid, int authorId) {
        LambdaQueryWrapper<UserRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRelation::getUserId, authorId);
        wrapper.eq(UserRelation::getFollowUserId, uid);
        return userRelationMapper.selectCount(wrapper) > 0;
    }

    @Override
    public void follow(int uid, int authorId) {
        if (uid == authorId) {
            throw new ServiceException("不可关注自己");
        }
        if (hasFollowed(uid, authorId)) {
            throw new ServiceException("不可重复关注");
        }
        UserRelation relation = new UserRelation();
        relation.setUserId(authorId);
        relation.setFollowUserId(uid);
        userRelationMapper.insert(relation);
    }

    @Override
    public void unFollow(int uid, int authorId) {
        if (!hasFollowed(uid, authorId)) {
            throw new ServiceException("不可取关未关注的用户");
        }
        LambdaQueryWrapper<UserRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRelation::getUserId, authorId);
        wrapper.eq(UserRelation::getFollowUserId, uid);
        userRelationMapper.delete(wrapper);
    }
}
