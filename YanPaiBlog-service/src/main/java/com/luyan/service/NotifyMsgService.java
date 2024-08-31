package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.NotifyMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface NotifyMsgService extends IService<NotifyMsg> {
    @Getter
    @AllArgsConstructor
    enum MsgType {
        SYSTEM(0, "系统"),
        PRAISE(1, "点赞"),
        HEART(2, "收藏"),
        FOLLOW(3, "关注"),
        ALL(4, "全部");

        private final int code;
        private final String desc;
    }

    @Getter
    @AllArgsConstructor
    enum MsgState {
        UNREAD(0, "未读"),
        READ(1, "已读"),
        ALL(2, "全部");

        private final int code;
        private final String desc;
    }

    /**
     * 根据用户 id 与消息类型、状态获取消息数量
     * @param uid 用户 id
     * @param type 类型: 0-系统 1-点赞 2-收藏 3-关注 4-全部
     * @param state 阅读状态: 0-未读，1-已读 2-全部
     * @return
     */
    long getMsgCount(int uid, MsgType type, MsgState state);

    // 获取用户所有未读消息的数量
    long getAllUnreadMsgCount(int uid);
}
