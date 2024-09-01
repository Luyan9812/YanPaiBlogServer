package com.luyan.entity.dto;

import com.luyan.entity.domain.Article;
import com.luyan.entity.domain.Tag;
import com.luyan.entity.domain.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class IndexArticleDto extends Article {
    private Long readNums;
    private Long collectionNums;
    private UserInfo userInfo;
    private List<Tag> tags;
}
