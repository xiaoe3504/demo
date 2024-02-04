CREATE TABLE `user_info` (
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
`open_id` VARCHAR ( 50 ) COMMENT 'openId',
`phone_number` VARCHAR ( 50 ) COMMENT '电话号码',
`nick_name` VARCHAR ( 50 ) COMMENT '昵称',
`password` VARCHAR ( 50 ) COMMENT '密码',
`avatar_url` VARCHAR ( 150 ) COMMENT '头像url',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY ( `id` ),
UNIQUE KEY `uk_open_id` ( `open_id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户信息';