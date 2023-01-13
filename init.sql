create database playground;

create user 'burns'@'%' identified by 'P@ssw0rd';

grant all privileges on playground.* TO 'burns'@'%';

flush privileges;

CREATE TABLE IF NOT EXISTS board (
                                     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
                                     `title` varchar(50) NOT NULL DEFAULT '' COMMENT '제목',
                                     `contents` text NOT NULL COMMENT '내용',
                                     `use_yn` varchar(1) NOT NULL COMMENT '사용여부',
                                     `creator_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '등록자 PK',
                                     `create_dt` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '등록시간',
                                     `modify_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '수정자 PK',
                                     `modify_dt` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '수정시간',
                                     PRIMARY KEY (`id`)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='게시판';
