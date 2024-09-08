package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.NotifyMsg;
import com.luyan.entity.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public interface NotifyMsgService extends IService<NotifyMsg> {
    @Getter
    @AllArgsConstructor
    enum MsgType {
        PRAISE(0, "点赞"),
        HEART(1, "收藏"),
        FOLLOW(2, "关注"),
        SYSTEM(3, "系统"),
        ALL(4, "全部");

        private final int code;
        private final String desc;

        public static MsgType fromCode(int code) {
            for (MsgType type : MsgType.values()) {
                if (type.getCode() == code) {
                    return type;
                }
            }
            throw new ServiceException("消息类别错误");
        }
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
     * @param type 类型: 0-点赞 1-收藏 2-关注 3-系统 4-全部
     * @param state 阅读状态: 0-未读，1-已读 2-全部
     * @return 消息条数
     */
    long getMsgCount(int uid, MsgType type, MsgState state);

    // 获取用户所有未读消息的数量
    long getAllUnreadMsgCount(int uid);

    // 根据消息类别获取消息
    List<NotifyMsg> getMessageByType(MsgType type);
}
