 CREATE TABLE `t_order` (
  `id` int(11) NOT NULL auto_increment,
  `order_no` varchar(50) default NULL COMMENT '������',
  `route_detail_id` int(11) default NULL COMMENT '��·����id',
  `user_id` int(11) default NULL COMMENT '�û�id',
  `order_status` varchar(10) default NULL COMMENT '����״̬',
  `total_price` decimal(10,2) default NULL COMMENT '�������',
  `pay_type` varchar(10) default NULL COMMENT '֧������',
  `pay_no` varchar(60) default NULL COMMENT '֧������',
  `create_time` datetime default NULL COMMENT '����ʱ��',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
