package com.luyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.ArticleTag;
import com.luyan.mapper.ArticleTagMapper;
import com.luyan.service.ArticleTagService;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
        implements ArticleTagService {

}
