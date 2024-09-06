package com.luyan.entity.domain;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;
    private Integer userId;
    private String title;
    private String picture;
    private String summary;
    private Integer categoryId;
    private Integer status;  // 状态：0-未发布，1-已发布，2-配置文章（不在文章列表显示）
    private Integer hotScore;  // 热门分数，阅读+1，点赞+2，收藏+3

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
