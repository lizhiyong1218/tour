CREATE TABLE `t_route_detail` (
  `id` int(11) NOT NULL auto_increment,
  `route_id` int(11) default NULL COMMENT '主表id',
  `route_status` varchar(10) default NULL COMMENT '活动状态(报名中，已成行，已满员)',
  `start_time` datetime default NULL COMMENT '开始时间',
  `end_time` datetime default NULL COMMENT '结束时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;