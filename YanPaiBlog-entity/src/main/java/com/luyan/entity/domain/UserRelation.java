package com.luyan.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties({"createTime", "updateTime"})
public class UserRelation {
    private Integer id;
    private Integer userId;
    private Integer followUserId;  // 关注 userId 的用户 id，即粉丝的 id

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
