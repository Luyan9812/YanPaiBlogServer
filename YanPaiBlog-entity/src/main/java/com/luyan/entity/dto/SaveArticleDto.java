package com.luyan.entity.dto;

import com.luyan.entity.domain.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 保存文章时的 Article DTO
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SaveArticleDto extends Article {
    private String content;
    private List<Integer> tags;
}
