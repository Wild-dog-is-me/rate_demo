CREATE TABLE `comment` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
                         `username` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名称',
                         `user_id` int(11) DEFAULT NULL COMMENT '用户id',
                         `rate` decimal(10,1) DEFAULT NULL COMMENT '评分',
                         `foreign_id` int(11) DEFAULT NULL COMMENT '模块的id',
                         `pid` int(11) DEFAULT NULL COMMENT '父级评论id',
                         `target` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '回复对象',
                         `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
