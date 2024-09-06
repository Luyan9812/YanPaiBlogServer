package com.luyan.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties({"createTime", "updateTime"})
public class ArticleDetail {
    private Integer id;
    private Integer articleId;
    private String content;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
