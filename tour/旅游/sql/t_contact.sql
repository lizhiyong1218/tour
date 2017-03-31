 CREATE TABLE `t_contact` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL COMMENT '用户id',
  `nick_name` varchar(50) default NULL COMMENT '名称',
  `phone` varchar(20) default NULL COMMENT '电话',
  `id_card` varchar(20) default NULL COMMENT '身份证',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;