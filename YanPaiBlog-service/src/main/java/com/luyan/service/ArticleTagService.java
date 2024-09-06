package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.ArticleTag;
import com.luyan.entity.domain.Tag;

import java.util.List;

public interface ArticleTagService extends IService<ArticleTag> {
    // 根据文章获取标签集
    List<Tag> getTagByArticle(int articleId);

    // 根据文章删除标签
    void deleteTagsByArticle(int articleId);
}
