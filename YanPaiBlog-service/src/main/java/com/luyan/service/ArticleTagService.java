package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.ArticleTag;
import com.luyan.entity.domain.Tag;

import java.util.List;

public interface ArticleTagService extends IService<ArticleTag> {
    List<Tag> getTagByArticle(int articleId);
}
