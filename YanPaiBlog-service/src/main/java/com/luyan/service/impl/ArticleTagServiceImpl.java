package com.luyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.ArticleTag;
import com.luyan.entity.domain.Tag;
import com.luyan.mapper.ArticleTagMapper;
import com.luyan.service.ArticleTagService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
        implements ArticleTagService {
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public List<Tag> getTagByArticle(int articleId) {
        return articleTagMapper.getTagByArticle(articleId);
    }

    @Override
    public void deleteTagsByArticle(int articleId) {
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId, articleId);
        articleTagMapper.delete(wrapper);
    }
}
