package com.luyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.ArticleDetail;
import com.luyan.mapper.ArticleDetailMapper;
import com.luyan.service.ArticleDetailService;
import org.springframework.stereotype.Service;

@Service
public class ArticleDetailServiceImpl extends ServiceImpl<ArticleDetailMapper, ArticleDetail>
        implements ArticleDetailService {

}
