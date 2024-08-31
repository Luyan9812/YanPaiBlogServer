package com.luyan.entity.domain;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class NotifyMsg {
    private Integer id;
    private Integer notifyUserId;  // 通知的用户 id
    private Integer operateUserId;  // 触发这个通知的用户 id
    private String msg;
    private Integer type;  // 类型: 0-系统，1-点赞，2-收藏，3-关注
    private Integer state;  // 阅读状态: 0-未读，1-已读

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
