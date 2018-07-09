DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30),
  `mobile` VARCHAR(30),
  `VIP` smallint,
  `createTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
  `updateTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;