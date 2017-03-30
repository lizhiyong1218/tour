CREATE TABLE `t_banner` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(100) default NULL COMMENT '标题',
  `status` varchar(10) default NULL COMMENT '状态(ENABLED:可用,DISABLED:停用)',
  `sort` tinyint(4) default NULL COMMENT '顺序',
  `city` varchar(50) default NULL COMMENT '城市',
  `link_url` varchar(240) default NULL COMMENT '链接地址',
  `pic_url` varchar(240) default NULL COMMENT '图片地址',
  `start_time` datetime default NULL COMMENT '开始时间',
  `end_time` datetime default NULL COMMENT '结束时间',
  `create_time` datetime default NULL COMMENT '创建时间',
  `create_by` varchar(50) default NULL COMMENT '创建人',
  `update_time` datetime default NULL COMMENT '修改时间',
  `update_by` varchar(50) default NULL COMMENT '修改人',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

