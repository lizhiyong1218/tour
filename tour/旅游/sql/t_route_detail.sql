CREATE TABLE `t_route_detail` (
  `id` int(11) NOT NULL auto_increment,
  `route_id` int(11) default NULL COMMENT '����id',
  `route_status` varchar(10) default NULL COMMENT '�״̬(�����У��ѳ��У�����Ա)',
  `start_time` datetime default NULL COMMENT '��ʼʱ��',
  `end_time` datetime default NULL COMMENT '����ʱ��',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;