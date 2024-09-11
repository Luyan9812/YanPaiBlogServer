package com.luyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.Article;
import com.luyan.entity.domain.NotifyMsg;
import com.luyan.entity.domain.UserInfo;
import com.luyan.entity.exception.ServiceException;
import com.luyan.mapper.ArticleMapper;
import com.luyan.mapper.NotifyMsgMapper;
import com.luyan.mapper.UserInfoMapper;
import com.luyan.service.NotifyMsgService;
import com.luyan.utils.BaseContext;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifyMsgServiceImpl extends ServiceImpl<NotifyMsgMapper, NotifyMsg>
        implements NotifyMsgService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private NotifyMsgMapper notifyMsgMapper;

    // 消息存在返回 null，否则返回新创建的消息对象
    private NotifyMsg existOrCreateMsgBean(int notifyUserId, int operateUserId, MsgType type) {
        LambdaQueryWrapper<NotifyMsg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NotifyMsg::getNotifyUserId, notifyUserId);
        wrapper.eq(NotifyMsg::getOperateUserId, operateUserId);
        wrapper.eq(NotifyMsg::getType, type.getCode());
        if (notifyMsgMapper.selectCount(wrapper) > 0) {
            return null;
        }
        NotifyMsg notifyMsg = new NotifyMsg();
        notifyMsg.setNotifyUserId(notifyUserId);
        notifyMsg.setOperateUserId(operateUserId);
        notifyMsg.setType(type.getCode());
        return notifyMsg;
    }

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

    @Override
    public List<NotifyMsg> getMessageByType(MsgType type) {
        Integer uid = BaseContext.getCurrentId();
        LambdaQueryWrapper<NotifyMsg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NotifyMsg::getNotifyUserId, uid);
        wrapper.eq(NotifyMsg::getType, type.getCode());
        wrapper.orderByDesc(NotifyMsg::getUpdateTime);
        return notifyMsgMapper.selectList(wrapper);
    }

    @Override
    public void sendMsg(int notifyUserId, int operateUserId, int articleId, MsgType type) {
        NotifyMsg notifyMsg = existOrCreateMsgBean(notifyUserId, operateUserId, type);
        if (notifyMsg == null) {  // 消息已经存在，不重复发送
            return;
        }
        StringBuilder builder = new StringBuilder();
        UserInfo info = userInfoMapper.selectById(operateUserId);
        if (type == MsgType.FOLLOW) {
            builder.append(String.format("用户 %s 关注了你", info.getNickName()));
        } else {
            Article article = articleMapper.selectById(articleId);
            if (type == MsgType.HEART) {
                builder.append(String.format("用户 %s 收藏了你的文章《%s》", info.getNickName(), article.getTitle()));
            } else if (type == MsgType.PRAISE) {
                builder.append(String.format("用户 %s 点赞了你的文章《%s》", info.getNickName(), article.getTitle()));
            }
        }
        notifyMsg.setMsg(builder.toString());
        notifyMsgMapper.insert(notifyMsg);
    }
}
