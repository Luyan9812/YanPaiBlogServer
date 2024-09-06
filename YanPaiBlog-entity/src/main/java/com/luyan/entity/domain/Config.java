package com.luyan.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties({"createTime", "updateTime"})
public class Config {
    private Integer id;
    private Integer type;  // 配置类型：1-首页，2-侧边栏，3-广告位，4-公告
    private String name;
    private String banner_url;
    private String jump_url;
    private String content;
    private Integer status;  // 状态：0-未发布，1-已发布
    private String tags;  // 配置关联标签，英文逗号分隔 1-火，2-官方，3-推荐

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
