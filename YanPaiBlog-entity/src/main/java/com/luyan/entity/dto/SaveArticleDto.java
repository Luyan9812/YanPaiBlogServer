package com.luyan.entity.dto;

import com.luyan.entity.domain.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 保存文章时的 Article DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SaveArticleDto extends Article {
    private String content;
    private List<Integer> tags;
}
