CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_blog_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `my_blog_db`;
/*Table structure for table `tb_admin_user` */
DROP TABLE IF EXISTS `tb_admin_user`;
CREATE TABLE `tb_admin_user` (
  `admin_user_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` VARCHAR(50) NOT NULL COMMENT '管理员登陆名称',
  `login_password` VARCHAR(50) NOT NULL COMMENT '管理员登陆密码',
  `nick_name` VARCHAR(50) NOT NULL COMMENT '管理员显示昵称',
  `locked` TINYINT(4) DEFAULT '0' COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`admin_user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/*Data for the table `tb_admin_user` */
INSERT  INTO `tb_admin_user`(`admin_user_id`,`login_user_name`,`login_password`,`nick_name`,`locked`) VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e','十三',0);






/*Table structure for table `tb_blog_category` */

CREATE TABLE `tb_blog_category` (
  `category_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '分类表主键',
  `category_name` VARCHAR(50) NOT NULL COMMENT '分类的名称',
  `category_icon` VARCHAR(50) NOT NULL COMMENT '分类的图标',
  `category_rank` INT(11) NOT NULL DEFAULT '1' COMMENT '分类的排序值 被使用的越多数值越大',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0=否 1=是',
  `create_time` DATETIME  DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`category_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


USE `my_blog_db`;
CREATE TABLE `tb_blog_tag` (
  `tag_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '标签表主键id',
  `tag_name` VARCHAR(100) NOT NULL COMMENT '标签名称',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0=否 1=是',
  `create_time` DATETIME  DEFAULT NULL COMMENT  '创建时间',
  PRIMARY KEY (`tag_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



CREATE TABLE `tb_blog_tag_relation` (
  `relation_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '关系表id',
  `blog_id` BIGINT(20) NOT NULL COMMENT '博客id',
  `tag_id` INT(11) NOT NULL COMMENT '标签id',
  `create_time` DATETIME  DEFAULT NULL COMMENT  '添加时间',
  PRIMARY KEY (`relation_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


USE `my_blog_db`;

SELECT * FROM tb_blog
CREATE TABLE `tb_blog` (
  `blog_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '博客表主键id',
  `blog_title` VARCHAR(200) NOT NULL COMMENT '博客标题',
  `blog_sub_url` VARCHAR(200) NOT NULL COMMENT '博客自定义路径url',
  `blog_cover_image` VARCHAR(200) NOT NULL COMMENT '博客封面图',
  `blog_content` MEDIUMTEXT NOT NULL COMMENT '博客内容',
  `blog_category_id` INT(11) NOT NULL COMMENT '博客分类id',
  `blog_category_name` VARCHAR(50) NOT NULL COMMENT '博客分类(冗余字段)',
  `blog_tags` VARCHAR(200) NOT NULL COMMENT '博客标签',
  `blog_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-草稿 1-发布',
  `blog_views` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '阅读量',
  `enable_comment` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-允许评论 1-不允许评论',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0=否 1=是',
  `create_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`blog_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

USE `my_blog_db`;
DROP TABLE IF EXISTS `tb_link`;
CREATE TABLE `tb_link` (
  `link_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '友链表主键id',
  `link_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '友链类别 0-友链 1-推荐 2-个人网站',
  `link_name` VARCHAR(50) NOT NULL COMMENT '网站名称',
  `link_url` VARCHAR(100) NOT NULL COMMENT '网站链接',
  `link_description` VARCHAR(100) NOT NULL COMMENT '网站描述',
  `link_rank` INT(11) NOT NULL DEFAULT '0' COMMENT '用于列表排序',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  `create_time` DATETIME DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`link_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


USE `my_blog_db`;
CREATE TABLE `tb_blog_comment` (
  `comment_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `blog_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '关联的blog主键',
  `commentator` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '评论者名称',
  `email` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '评论人的邮箱',
  `website_url` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '网址',
  `comment_body` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '评论内容',
  `comment_create_time` DATETIME DEFAULT NULL COMMENT '评论提交时间',
  `commentator_ip` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '评论时的ip地址',
  `reply_body` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '回复内容',
  `reply_create_time` DATETIME DEFAULT NULL COMMENT '回复时间',
  `comment_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否审核通过 0-未审核 1-审核通过',
  `is_deleted` TINYINT(4) DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`comment_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE `tb_config` (
  `config_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '配置项的名称',
  `config_value` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '配置项的值',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`config_name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `tb_config` */

INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('footerAbout','your personal blog. have fun.','2018-11-11 20:33:23','2018-11-12 11:58:06');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('footerCopyRight','2019 十三','2018-11-11 20:33:31','2018-11-12 11:58:06');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('footerICP','浙ICP备17008806号-3','2018-11-11 20:33:27','2018-11-12 11:58:06');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('footerPoweredBy','https://github.com/ZHENFENG13','2018-11-11 20:33:36','2018-11-12 11:58:06');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('footerPoweredByURL','https://github.com/ZHENFENG13','2018-11-11 20:33:39','2018-11-12 11:58:06');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('websiteDescription','personal blog是SpringBoot2+Thymeleaf+Mybatis建造的个人博客网站.SpringBoot实战博客源码.个人博客搭建','2018-11-11 20:33:04','2018-11-11 22:05:14');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('websiteIcon','/admin/dist/img/favicon.png','2018-11-11 20:33:11','2018-11-11 22:05:14');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('websiteLogo','/admin/dist/img/logo2.png','2018-11-11 20:33:08','2018-11-11 22:05:14');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('websiteName','personal blog','2018-11-11 20:33:01','2018-11-11 22:05:14');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('yourAvatar','/admin/dist/img/13.png','2018-11-11 20:33:14','2019-05-07 21:56:23');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('yourEmail','2449207463@qq.com','2018-11-11 20:33:17','2019-05-07 21:56:23');
INSERT  INTO `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) VALUES ('yourName','13','2018-11-11 20:33:20','2019-05-07 21:56:23');


