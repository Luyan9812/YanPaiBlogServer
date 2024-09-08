CREATE TABLE `article`
(
    `id`           int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`      int unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `title`        varchar(120) NOT NULL DEFAULT '' COMMENT '文章标题',
    `picture`      varchar(128) NOT NULL DEFAULT '' COMMENT '文章头图',
    `summary`      varchar(300) NOT NULL DEFAULT '' COMMENT '文章摘要',
    `category_id`  int unsigned NOT NULL DEFAULT '0' COMMENT '类目ID',
    `status`       tinyint      NOT NULL DEFAULT '0' COMMENT '状态：0-未发布，1-已发布',
    `hot_score`    int unsigned NOT NULL DEFAULT '0' COMMENT '热榜排序，阅读+1，点赞+2，收藏+3',
    `create_time`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY            `idx_category_id` (`category_id`),
    KEY `idx_title` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='文章表';


CREATE TABLE `article_detail`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `article_id`  int unsigned NOT NULL DEFAULT '0' COMMENT '文章ID',
    `content`     longtext COMMENT '文章内容',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='文章详情表';


CREATE TABLE `article_tag`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `article_id`  int unsigned NOT NULL DEFAULT '0' COMMENT '文章ID',
    `tag_id`      int       NOT NULL DEFAULT '0' COMMENT '标签ID',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY           `idx_tag_id` (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='文章标签映射';


CREATE TABLE `category`
(
    `id`            int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `category_name` varchar(64) NOT NULL DEFAULT '' COMMENT '类目名称',
    `create_time`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='类目管理表';


CREATE TABLE `notify_msg`
(
    `id`              int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `notify_user_id`  int unsigned NOT NULL DEFAULT '0' COMMENT '通知的用户id',
    `operate_user_id` int unsigned NOT NULL DEFAULT '0' COMMENT '触发这个通知的用户id',
    `msg`             varchar(1024) NOT NULL DEFAULT '' COMMENT '消息内容',
    `type`            tinyint unsigned NOT NULL DEFAULT '0' COMMENT '类型: 0-点赞 1-收藏 2-关注 3-系统',
    `state`           tinyint unsigned NOT NULL DEFAULT '0' COMMENT '阅读状态: 0-未读，1-已读',
    `create_time`     timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY               `key_notify_user_id_type_state` (`notify_user_id`,`type`,`state`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='消息通知列表';


CREATE TABLE `tag`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tag_name`    varchar(120) NOT NULL COMMENT '标签名称',
    `category_id` int unsigned NOT NULL DEFAULT '0' COMMENT '所属类目ID',
    `create_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='标签管理表';


CREATE TABLE `user`
(
    `id`               int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username`        varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
    `password`         varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
    `salt_pos`         int NOT NULL DEFAULT '0' COMMENT '密码加盐位置',
    `create_time`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='用户登录表';


CREATE TABLE `user_info`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     int unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `nick_name`   varchar(50)   NOT NULL DEFAULT '' COMMENT '用户昵称',
    `photo`       varchar(128)  NOT NULL DEFAULT '' COMMENT '用户图像',
    `position`    varchar(50)   NOT NULL DEFAULT '' COMMENT '职位',
    `company`     varchar(50)   NOT NULL DEFAULT '' COMMENT '公司',
    `profile`     varchar(225)  NOT NULL DEFAULT '' COMMENT '个人简介',
    `create_time` timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY           `key_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='用户个人信息表';


CREATE TABLE `user_foot`
(
    `id`               int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`          int unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `document_id`      int unsigned NOT NULL DEFAULT '0' COMMENT '文档ID',
    `document_user_id` int unsigned NOT NULL DEFAULT '0' COMMENT '发布该文档的用户ID',
    `collection_stat`  tinyint unsigned NOT NULL DEFAULT '0' COMMENT '收藏状态: 0-未收藏，1-已收藏，2-取消收藏',
    `read_stat`        tinyint unsigned NOT NULL DEFAULT '0' COMMENT '阅读状态: 0-未读，1-已读',
    `praise_stat`      tinyint unsigned NOT NULL DEFAULT '0' COMMENT '点赞状态: 0-未点赞，1-已点赞，2-取消点赞',
    `create_time`      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY                `idx_doucument_id` (`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='用户足迹表';


CREATE TABLE `user_relation`
(
    `id`             int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`        int unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
    `follow_user_id` int unsigned NOT NULL COMMENT '关注userId的用户id，即粉丝userId',
    `create_time`    timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_follow` (`user_id`,`follow_user_id`),
    KEY              `key_follow_user_id` (`follow_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='用户关系表';


CREATE TABLE `config`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `type`        tinyint      NOT NULL default '0' COMMENT '配置类型：1-首页，2-侧边栏，3-广告位，4-公告',
    `name`        varchar(64)  NOT NULL default '' COMMENT '名称',
    `banner_url`  varchar(256) NOT NULL default '' COMMENT '图片链接',
    `jump_url`    varchar(256) NOT NULL default '' COMMENT '跳转链接',
    `content`     varchar(256) NOT NULL default '' COMMENT '内容',
    `status`      tinyint      NOT NULL DEFAULT '0' COMMENT '状态：0-未发布，1-已发布',
    `tags`        varchar(64)  not null default '' comment '配置关联标签，英文逗号分隔 1 火 2 官方 3 推荐',
    `create_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='配置表';


