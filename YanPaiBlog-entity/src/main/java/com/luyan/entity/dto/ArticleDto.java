package com.luyan.entity.dto;

import com.luyan.entity.domain.Article;
import com.luyan.entity.domain.Tag;
import com.luyan.entity.domain.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 展示文章时的 Article DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleDto extends Article {
    private Long readNum;
    private Long collectionNum;
    private UserInfo authorInfo;
    private List<Tag> tags;
    private String content;
}
