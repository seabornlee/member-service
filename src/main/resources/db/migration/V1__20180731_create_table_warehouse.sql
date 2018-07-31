DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
`id` int UNSIGNED NULL AUTO_INCREMENT,
`warehouse_no` varchar(50) NULL COMMENT '仓库编号',
`name` varchar(50) NULL COMMENT '名称',
`type` varchar(50) NULL,
`is_master` boolean NULL COMMENT '是否主库',
`is_default` boolean NULL COMMENT '是否默认',
`is_position_enabled` boolean NULL COMMENT '是否允许位置',
`province` int(11) NULL,
`city` int(11) NULL,
`district` int(11) NULL,
`address` varchar(255) NULL COMMENT '详细地址',
`tenant_id` int(11) NULL COMMENT '租户id',
`data_source` int NULL DEFAULT 1 COMMENT '数据来源： 1 e精灵',
PRIMARY KEY (`id`) ,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;