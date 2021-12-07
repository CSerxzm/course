/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : course

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2021-12-07 23:08:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `admin_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `admin_username` varchar(255) NOT NULL,
  `admin_password` char(32) NOT NULL,
  `admin_privilege` int(11) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `idx_admin_username` (`admin_username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', '123456', '255');

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `class_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `class_major_id` int(10) unsigned NOT NULL,
  `class_grade` int(10) unsigned NOT NULL,
  `class_name` varchar(32) NOT NULL,
  PRIMARY KEY (`class_id`),
  KEY `fk_major_id` (`class_major_id`) USING BTREE,
  KEY `idx_class_name` (`class_name`) USING BTREE,
  CONSTRAINT `t_class_ibfk_1` FOREIGN KEY (`class_major_id`) REFERENCES `t_major` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('1', '1', '2017', '计信1班');
INSERT INTO `t_class` VALUES ('3', '4', '2017', '数学1班');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `course_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_teacher_id` int(10) unsigned NOT NULL,
  `course_name` varchar(64) NOT NULL,
  `course_grade` int(10) unsigned NOT NULL,
  `course_time` varchar(16) NOT NULL,
  `course_location` varchar(32) NOT NULL,
  `course_credit` int(10) unsigned NOT NULL,
  `course_selected_count` int(10) unsigned NOT NULL DEFAULT '0',
  `course_max_size` int(10) unsigned NOT NULL,
  `course_exam_date` datetime DEFAULT NULL,
  `course_exam_location` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `fk_course_teacher_id` (`course_teacher_id`) USING BTREE,
  KEY `idx_course_name` (`course_name`) USING BTREE,
  CONSTRAINT `t_course_ibfk_1` FOREIGN KEY (`course_teacher_id`) REFERENCES `t_teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('1', '1', 'C语言程序设计', '2017', '1-2-2', '信工楼107', '5', '18', '50', null, null);
INSERT INTO `t_course` VALUES ('2', '1', 'Java程序设计', '2017', '1-3-2', '信工楼106', '4', '1', '30', null, null);
INSERT INTO `t_course` VALUES ('3', '1', '数据库实用技术', '2017', '2-3-2', 'C区202', '2', '1', '50', null, null);
INSERT INTO `t_course` VALUES ('4', '1', 'ASP.Net开发', '2017', '5-5-3', 'E区315', '2', '1', '1', null, null);
INSERT INTO `t_course` VALUES ('5', '1', 'Spring企业级开发', '2017', '3-9-2', '信工楼101', '3', '2', '10', null, null);
INSERT INTO `t_course` VALUES ('6', '3', '数据库概论', '2017', '3-1-2', 'C区106', '5', '2', '40', null, null);
INSERT INTO `t_course` VALUES ('7', '3', 'Photoshop', '2017', '2-3-2', 'C区222', '2', '0', '20', null, null);
INSERT INTO `t_course` VALUES ('8', '4', '计算机网络', '2017', '4-1-3', '信工楼109', '5', '0', '20', null, null);
INSERT INTO `t_course` VALUES ('9', '10', '李四的高数', '2017', '1-1-2', '1', '2', '0', '50', null, '1');
INSERT INTO `t_course` VALUES ('10', '10', '李四的高数课for2017', '2017', '2-2-2', '1', '2', '1', '50', null, '1');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `department_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `department_name` varchar(32) NOT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('1', '计算机系');
INSERT INTO `t_department` VALUES ('2', '数学系');
INSERT INTO `t_department` VALUES ('3', '物理系');
INSERT INTO `t_department` VALUES ('4', '化学系');

-- ----------------------------
-- Table structure for t_major
-- ----------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major` (
  `major_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `major_department_id` int(10) unsigned NOT NULL,
  `major_name` varchar(32) NOT NULL,
  PRIMARY KEY (`major_id`),
  KEY `fk_major_department_id` (`major_department_id`) USING BTREE,
  KEY `idx_major_name` (`major_name`) USING BTREE,
  CONSTRAINT `t_major_ibfk_1` FOREIGN KEY (`major_department_id`) REFERENCES `t_department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_major
-- ----------------------------
INSERT INTO `t_major` VALUES ('1', '1', '计算机科学与技术');
INSERT INTO `t_major` VALUES ('2', '1', '软件工程');
INSERT INTO `t_major` VALUES ('3', '1', '信息工程');
INSERT INTO `t_major` VALUES ('4', '2', '应用数学');
INSERT INTO `t_major` VALUES ('5', '2', '数学师范');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `student_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student_class_id` int(10) unsigned NOT NULL,
  `student_number` char(12) NOT NULL,
  `student_name` varchar(20) NOT NULL,
  `student_password` char(32) NOT NULL,
  `student_phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `student_email` varchar(64) DEFAULT NULL,
  `student_address` varchar(255) DEFAULT NULL COMMENT '地址',
  `student_birthday` datetime DEFAULT NULL,
  `student_sex` tinyint(3) unsigned NOT NULL,
  `student_last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `idx_student_number` (`student_number`) USING BTREE,
  KEY `fk_student_class_id` (`student_class_id`) USING BTREE,
  KEY `idx_student_name` (`student_name`) USING BTREE,
  CONSTRAINT `t_student_ibfk_1` FOREIGN KEY (`student_class_id`) REFERENCES `t_class` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('1', '1', '811010001', '小李', '123456', '15284875389', '3052720966@qq.com', 'home2', '2021-12-07 16:00:00', '0', '2021-12-07 15:06:49');
INSERT INTO `t_student` VALUES ('2', '1', '811010002', '小宋', '123456', null, null, null, null, '1', null);
INSERT INTO `t_student` VALUES ('3', '1', '811010003', '李同学1', '123456', null, null, null, null, '0', null);
INSERT INTO `t_student` VALUES ('4', '1', '811010004', '李同学2', '123456', null, null, null, null, '0', null);
INSERT INTO `t_student` VALUES ('5', '1', '811010005', '李同学3', '123456', null, null, null, null, '0', null);
INSERT INTO `t_student` VALUES ('6', '1', '811010006', '李同学4', '123456', null, null, null, null, '0', null);
INSERT INTO `t_student` VALUES ('7', '1', '811010007', '李同学5', '123456', null, null, null, null, '0', null);
INSERT INTO `t_student` VALUES ('8', '1', '811010008', '李同学6', '123456', null, null, null, null, '0', null);
INSERT INTO `t_student` VALUES ('9', '1', '811010009', '李同学7', '123456', null, null, null, null, '0', null);
INSERT INTO `t_student` VALUES ('10', '1', '811010010', '李同学8', '123456', null, null, null, null, '0', null);
INSERT INTO `t_student` VALUES ('11', '1', '811010011', '李同学9', '123456', null, null, null, null, '0', null);
INSERT INTO `t_student` VALUES ('12', '1', '811010012', '张同学1', '123456', null, null, null, null, '1', null);
INSERT INTO `t_student` VALUES ('13', '1', '811010013', '张同学2', '123456', null, null, null, null, '1', null);
INSERT INTO `t_student` VALUES ('14', '1', '811010014', '张同学3', '123456', null, null, null, null, '1', null);
INSERT INTO `t_student` VALUES ('15', '1', '811010015', '张同学4', '123456', null, null, null, null, '1', null);
INSERT INTO `t_student` VALUES ('16', '1', '811010016', '张同学5', '123456', null, null, null, null, '1', null);
INSERT INTO `t_student` VALUES ('17', '1', '811010017', '张同学6', '123456', null, null, null, null, '1', null);
INSERT INTO `t_student` VALUES ('18', '1', '811010018', '张同学7', '123456', null, null, null, null, '1', null);
INSERT INTO `t_student` VALUES ('19', '1', '123123123', '小白', '123', '15855555555', 'aaa@bbb.com', 'aa', '2021-11-08 16:00:00', '0', '2021-11-09 02:13:14');

-- ----------------------------
-- Table structure for t_student_course
-- ----------------------------
DROP TABLE IF EXISTS `t_student_course`;
CREATE TABLE `t_student_course` (
  `sc_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sc_student_id` int(10) unsigned NOT NULL,
  `sc_course_id` int(10) unsigned NOT NULL,
  `sc_daily_score` int(10) unsigned DEFAULT NULL,
  `sc_exam_score` int(10) unsigned DEFAULT NULL,
  `sc_score` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`sc_id`),
  KEY `fk_sc_course_id` (`sc_course_id`) USING BTREE,
  KEY `fk_sc_student_id` (`sc_student_id`) USING BTREE,
  CONSTRAINT `t_student_course_ibfk_1` FOREIGN KEY (`sc_course_id`) REFERENCES `t_course` (`course_id`),
  CONSTRAINT `t_student_course_ibfk_2` FOREIGN KEY (`sc_student_id`) REFERENCES `t_student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student_course
-- ----------------------------
INSERT INTO `t_student_course` VALUES ('2', '2', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('3', '3', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('4', '4', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('5', '5', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('6', '6', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('7', '7', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('8', '8', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('9', '9', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('10', '10', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('11', '11', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('12', '12', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('13', '13', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('14', '14', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('15', '15', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('16', '16', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('17', '17', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('18', '18', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('19', '1', '3', '98', '100', '99');
INSERT INTO `t_student_course` VALUES ('20', '1', '4', null, null, null);
INSERT INTO `t_student_course` VALUES ('21', '1', '6', null, null, null);
INSERT INTO `t_student_course` VALUES ('22', '1', '5', null, null, null);
INSERT INTO `t_student_course` VALUES ('23', '19', '1', null, null, null);
INSERT INTO `t_student_course` VALUES ('25', '19', '2', null, null, null);
INSERT INTO `t_student_course` VALUES ('26', '19', '5', null, null, null);
INSERT INTO `t_student_course` VALUES ('27', '19', '10', '2', '8', '8');
INSERT INTO `t_student_course` VALUES ('28', '19', '6', null, null, null);

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `teacher_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `teacher_department_id` int(10) unsigned NOT NULL,
  `teacher_number` char(12) NOT NULL,
  `teacher_name` varchar(20) NOT NULL,
  `teacher_password` char(32) NOT NULL,
  PRIMARY KEY (`teacher_id`),
  UNIQUE KEY `idx_teacher_number` (`teacher_number`) USING BTREE,
  KEY `fk_teacher_department_id` (`teacher_department_id`) USING BTREE,
  CONSTRAINT `t_teacher_ibfk_1` FOREIGN KEY (`teacher_department_id`) REFERENCES `t_department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('1', '1', '11010001', '张三', '123456');
INSERT INTO `t_teacher` VALUES ('2', '3', '11020001', '宋老师', '123456');
INSERT INTO `t_teacher` VALUES ('3', '1', '11010002', '宋老师', '123456');
INSERT INTO `t_teacher` VALUES ('4', '1', '11010003', '张老师', '123456');
INSERT INTO `t_teacher` VALUES ('5', '1', '11010004', '吕老师', '123456');
INSERT INTO `t_teacher` VALUES ('6', '1', '11010005', '王老师', '123456');
INSERT INTO `t_teacher` VALUES ('7', '1', '11010006', '丁老师', '123456');
INSERT INTO `t_teacher` VALUES ('8', '1', '11010007', '高老师', '123456');
INSERT INTO `t_teacher` VALUES ('9', '1', '11010008', '赵老师', '123456');
INSERT INTO `t_teacher` VALUES ('10', '1', '12312312', '李四', '123123');
