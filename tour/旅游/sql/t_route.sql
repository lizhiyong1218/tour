CREATE TABLE `t_route` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(100) default NULL COMMENT '标题',
  `city` varchar(20) default NULL COMMENT '城市',
  `status` varchar(10) default NULL COMMENT '状态(ENABLED:可用,DISABLED:停用)',
  `route_status` varchar(10) default NULL COMMENT '活动状态(报名中，已成行，已满员)',
  `route_type` varchar(10) default NULL COMMENT '活动类型(境内长线，境外长线，短线)',
  `route_feature` varchar(150) default NULL COMMENT '特色(徒步，海岸线)',
  `pic_url` varchar(255) default NULL COMMENT '封面图',
  `pub_time` datetime default NULL COMMENT '发布时间',
  `start_time` datetime default NULL COMMENT '开始时间',
  `end_time` datetime default NULL COMMENT '结束时间',
  `start_address` varchar(200) default NULL COMMENT '出发地点',
  `end_address` varchar(200) default NULL COMMENT '目的地',
  `total_people` int(11) default NULL COMMENT '总人数',
  `min_start_people` int(11) default NULL COMMENT '最小成行人数',
  `original_price` decimal(10,2) default NULL COMMENT '原价',
  `current_price` decimal(10,2) default NULL COMMENT '当前价',
  `detail_desc` text COMMENT '详细行程',
  `price_desc` text COMMENT '费用说明',
  `prepare_desc` text COMMENT '活动准备',
  `group_pic_url` varchar(240) default NULL COMMENT '进群图片',
  `create_time` datetime default NULL COMMENT '创建时间',
  `create_by` varchar(50) default NULL COMMENT '创建人',
  `update_time` datetime default NULL COMMENT '修改时间',
  `update_by` varchar(50) default NULL COMMENT '修改人',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;