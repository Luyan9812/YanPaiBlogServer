package com.luyan.entity.dto;

import com.luyan.entity.domain.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleDto extends Article {
    private String content;
    private List<Integer> tags;
}
