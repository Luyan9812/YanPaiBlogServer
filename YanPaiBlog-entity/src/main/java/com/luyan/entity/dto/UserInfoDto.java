package com.luyan.entity.dto;

import com.luyan.entity.domain.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoDto extends UserInfo {
    private long jointDays;  // 加入天数
    private long fansNums;  // 粉丝数
    private long followedNums;  // 关注数
}
