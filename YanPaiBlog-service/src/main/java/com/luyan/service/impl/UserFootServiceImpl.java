package com.luyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.Article;
import com.luyan.entity.domain.UserFoot;
import com.luyan.mapper.ArticleMapper;
import com.luyan.mapper.UserFootMapper;
import com.luyan.service.UserFootService;
import com.luyan.utils.BaseContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserFootServiceImpl extends ServiceImpl<UserFootMapper, UserFoot>
        implements UserFootService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private UserFootMapper userFootMapper;

    private UserFoot getUserFoot(int uid, int articleId) {
        LambdaQueryWrapper<UserFoot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFoot::getUserId, uid);
        wrapper.eq(UserFoot::getDocumentId, articleId);
        return userFootMapper.selectOne(wrapper);
    }

    private UserFoot getOrCreateUserFoot(int articleId) {
        int uid = BaseContext.getCurrentId();
        UserFoot userFoot = getUserFoot(uid, articleId);
        if (userFoot == null) {
            userFoot = new UserFoot();
            userFoot.setUserId(uid);
            userFoot.setDocumentId(articleId);
            Integer documentUserId = articleMapper.selectById(articleId).getUserId();
            userFoot.setDocumentUserId(documentUserId);
        }
        return userFoot;
    }

    private void modifyArticleScore(int articleId, int plus) {
        Article article = articleMapper.selectById(articleId);
        article.setHotScore(article.getHotScore() + plus);
        articleMapper.updateById(article);
    }

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

    @Override
    public UserState getUserStatInArticle(int uid, int articleId) {
        UserFoot userFoot = getUserFoot(uid, articleId);
        UserState state = new UserState();
        state.setHasRead(userFoot.getReadStat() == 1);
        state.setHasPraised(userFoot.getPraiseStat() == 1);
        state.setHasCollection(userFoot.getCollectionStat() == 1);
        return state;
    }

    @Override
    public void setArticleReadState(int articleId, boolean readState) {
        Integer value = readState ? 1 : 0;
        UserFoot userFoot = getOrCreateUserFoot(articleId);
        if (value.equals(userFoot.getReadStat())) {
            return;
        }
        userFoot.setReadStat(value);
        userFootMapper.insertOrUpdate(userFoot);
        modifyArticleScore(articleId, readState ? 1 : -1);
    }

    @Override
    public void setArticlePraiseState(int articleId, boolean praiseState) {
        Integer value = praiseState ? 1 : 0;
        UserFoot userFoot = getOrCreateUserFoot(articleId);
        if (value.equals(userFoot.getPraiseStat())) {
            return;
        }
        userFoot.setPraiseStat(value);
        userFootMapper.insertOrUpdate(userFoot);
        modifyArticleScore(articleId, praiseState ? 2 : -2);
    }

    @Override
    public void setArticleCollectionState(int articleId, boolean collectionState) {
        Integer value = collectionState ? 1 : 0;
        UserFoot userFoot = getOrCreateUserFoot(articleId);
        if (value.equals(userFoot.getCollectionStat())) {
            return;
        }
        userFoot.setCollectionStat(value);
        userFootMapper.insertOrUpdate(userFoot);
        modifyArticleScore(articleId, collectionState ? 3 : -3);
    }
}
