 CREATE TABLE `t_order` (
  `id` int(11) NOT NULL auto_increment,
  `order_no` varchar(50) default NULL COMMENT '订单号',
  `route_detail_id` int(11) default NULL COMMENT '线路详情id',
  `user_id` int(11) default NULL COMMENT '用户id',
  `order_status` varchar(10) default NULL COMMENT '订单状态',
  `total_price` decimal(10,2) default NULL COMMENT '订单金额',
  `pay_type` varchar(10) default NULL COMMENT '支付类型',
  `pay_no` varchar(60) default NULL COMMENT '支付单号',
  `create_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
