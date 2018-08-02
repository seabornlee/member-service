DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
`id` int UNSIGNED NOT NULL AUTO_INCREMENT,
`sku_no` varchar(50) NULL COMMENT '编号',
`bar_code` varchar(50) NULL COMMENT '二维码',
`pic` varchar(300) NULL COMMENT '图片',
`tenant_id` int(11) NULL COMMENT '租户id',
`data_source` int NULL DEFAULT 1 COMMENT '数据来源： 1 e精灵',
PRIMARY KEY (`id`) ,
INDEX `sku_no_idx` (`sku_no` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;