 CREATE TABLE `t_order_contact` (
  `id` int(11) NOT NULL auto_increment,
  `order_id` int(11) default NULL COMMENT '订单id',
  `contact_id` int(11) default NULL COMMENT '联系人id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

