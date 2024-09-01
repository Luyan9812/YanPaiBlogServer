package com.luyan.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties({"createTime", "updateTime"})
public class UserInfo {
    private Integer id;
    private Integer userId;
    private String nickName;
    private String photo;
    private String position;  // 职位
    private String company;  // 公司
    private String profile;  // 个人简介

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
