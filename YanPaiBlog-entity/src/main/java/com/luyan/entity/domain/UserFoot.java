package com.luyan.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties({"createTime", "updateTime"})
public class UserFoot {
    private Integer id;
    private Integer userId;
    private Integer documentId;
    private Integer documentUserId;
    private Integer collectionStat;  // 收藏状态: 0-未收藏，1-已收藏，2-取消收藏
    private Integer readStat;  // 阅读状态: 0-未读，1-已读
    private Integer praiseStat;  // 点赞状态: 0-未点赞，1-已点赞，2-取消点赞

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
