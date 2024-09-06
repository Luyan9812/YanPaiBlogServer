package com.luyan.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties({"createTime", "updateTime"})
public class ArticleTag {
    private Integer id;
    private Integer articleId;
    private Integer tagId;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
