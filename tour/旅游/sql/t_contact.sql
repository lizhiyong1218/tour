 CREATE TABLE `t_contact` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL COMMENT '�û�id',
  `nick_name` varchar(50) default NULL COMMENT '����',
  `phone` varchar(20) default NULL COMMENT '�绰',
  `id_card` varchar(20) default NULL COMMENT '���֤',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;