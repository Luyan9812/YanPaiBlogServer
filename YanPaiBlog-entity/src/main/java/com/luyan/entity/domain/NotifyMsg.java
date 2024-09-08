package com.luyan.entity.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotifyMsg {
    private Integer id;
    private Integer notifyUserId;  // 通知的用户 id
    private Integer operateUserId;  // 触发这个通知的用户 id
    private String msg;
    private Integer type;  // 类型: 0-点赞，1-收藏，2-关注，3-系统
    private Integer state;  // 阅读状态: 0-未读，1-已读

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
