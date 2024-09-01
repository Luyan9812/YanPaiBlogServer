package com.luyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.UserFoot;
import com.luyan.mapper.UserFootMapper;
import com.luyan.service.UserFootService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserFootServiceImpl extends ServiceImpl<UserFootMapper, UserFoot>
        implements UserFootService {

    @Resource
    private UserFootMapper userFootMapper;

    @Override
    public long getPraisedNumByUser(int uid) {
        LambdaQueryWrapper<UserFoot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFoot::getDocumentUserId, uid);
        wrapper.eq(UserFoot::getPraiseStat, 1);
        return userFootMapper.selectCount(wrapper);
    }

    @Override
    public long getReadNumByUser(int uid) {
        LambdaQueryWrapper<UserFoot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFoot::getDocumentUserId, uid);
        wrapper.eq(UserFoot::getReadStat, 1);
        return userFootMapper.selectCount(wrapper);
    }

    @Override
    public long getCollectionNumByUser(int uid) {
        LambdaQueryWrapper<UserFoot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFoot::getDocumentUserId, uid);
        wrapper.eq(UserFoot::getCollectionStat, 1);
        return userFootMapper.selectCount(wrapper);
    }

    @Override
    public long getReadNumByArticle(int articleId) {
        LambdaQueryWrapper<UserFoot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFoot::getDocumentId, articleId);
        wrapper.eq(UserFoot::getReadStat, 1);
        return userFootMapper.selectCount(wrapper);
    }

    @Override
    public long getCollectionNumByArticle(int articleId) {
        LambdaQueryWrapper<UserFoot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFoot::getDocumentId, articleId);
        wrapper.eq(UserFoot::getCollectionStat, 1);
        return userFootMapper.selectCount(wrapper);
    }
}
