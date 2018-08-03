DROP TABLE IF EXISTS `warehouse_bin`;
CREATE TABLE `warehouse_bin` (
`id` int UNSIGNED NOT NULL AUTO_INCREMENT,
`bin_no` varchar(50) NULL COMMENT '编号',
`name` varchar(50) NULL COMMENT '名称',
`area_type` varchar(50) NULL COMMENT '区域类型',
`area_name` varchar(50) NULL COMMENT '区域名',
`area_no` varchar(50) NULL COMMENT '区域编号',
`shelf_no` varchar(50) NULL COMMENT '货架编号',
`is_tmp` boolean NULL COMMENT '是否是临时',
`is_enabled` boolean NULL DEFAULT false COMMENT '是否禁用',
`data_source` int NULL DEFAULT 1 COMMENT '数据来源： 1 e精灵',
`warehouse_id` int NULL COMMENT '仓库id',
`warehouse_no` varchar(50) NULL COMMENT '仓库编号',
`capacity` int NULL COMMENT '容量',
`tenant_id` int(11) NULL COMMENT '租户id',
PRIMARY KEY (`id`) ,
INDEX `warehouse_bin_no_idx` (`bin_no` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;