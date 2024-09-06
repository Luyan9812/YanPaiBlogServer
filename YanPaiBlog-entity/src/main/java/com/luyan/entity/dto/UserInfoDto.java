package com.luyan.entity.dto;

import com.luyan.entity.domain.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoDto extends UserInfo {
    private long jointDays;  // 加入天数
    private long fansNum;  // 粉丝数（由 UserRelation 维护）
    private long followedNum;  // 关注数（由 UserRelation 维护）
    private long publishNum;  // 发布文章数
    private long praiseNum;  // 所有文章被点赞数
    private long readNum;  // 所有文章被阅读数
    private long collectionNum;  // 所有文章被收藏数
    private boolean hasFollowed;  // 当前登录用户是否关注了该用户
    private boolean myself;  // 作者是否是当前用户本人
}
