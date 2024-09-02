package com.luyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.ArticleDetail;
import com.luyan.mapper.ArticleDetailMapper;
import com.luyan.service.ArticleDetailService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleDetailServiceImpl extends ServiceImpl<ArticleDetailMapper, ArticleDetail>
        implements ArticleDetailService {
    @Resource
    private ArticleDetailMapper articleDetailMapper;

    @Override
    public ArticleDetail getArticleDetail(int articleId) {
        LambdaQueryWrapper<ArticleDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleDetail::getArticleId, articleId);
        return articleDetailMapper.selectOne(wrapper);
    }
}
