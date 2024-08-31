package com.luyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.NotifyMsg;
import com.luyan.mapper.NotifyMsgMapper;
import com.luyan.service.NotifyMsgService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class NotifyMsgServiceImpl extends ServiceImpl<NotifyMsgMapper, NotifyMsg>
        implements NotifyMsgService {

    @Resource
    private NotifyMsgMapper notifyMsgMapper;

    @Override
    public long getMsgCount(int uid, MsgType type, MsgState state) {
        LambdaQueryWrapper<NotifyMsg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NotifyMsg::getNotifyUserId, uid);
        wrapper.eq(type != MsgType.ALL, NotifyMsg::getType, type.getCode());
        wrapper.eq(state != MsgState.ALL, NotifyMsg::getState, state.getCode());
        return notifyMsgMapper.selectCount(wrapper);
    }

    @Override
    public long getAllUnreadMsgCount(int uid) {
        return getMsgCount(uid, MsgType.ALL, MsgState.ALL);
    }
}
