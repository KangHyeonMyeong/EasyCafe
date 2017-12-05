CREATE TABLE cafe_info (
  `total_seat` smallint(6) NOT NULL,
  `current_seat` smallint(6) NOT NULL,
  `temp_level` smallint(6) NOT NULL,
  `humidity_level` smallint(6) NOT NULL,
  `noise_level` smallint(6) NOT NULL,
  `tissue_state` varchar(5) NOT NULL,
  `trash_can_state` varchar(5) NOT NULL,
  `toilet_state` varchar(5) NOT NULL,
  `regdate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 MAX_ROWS=1 AVG_ROW_LENGTH=30


CREATE TABLE `help` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `notice_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8


CREATE TABLE `member2` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `e_mail` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(10) NOT NULL,
  `birth` date NOT NULL,
  `phone` varchar(13) NOT NULL,
  `manager_flag` varchar(1) NOT NULL,
  PRIMARY KEY (`num`,`e_mail`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8]


select * from member2;



CREATE TABLE `menu` (
  `menu_name` varchar(20) NOT NULL,
  `menu_info` text,
  `menu_image` varchar(20) DEFAULT 'nothing.jpg',
  `menu_category` varchar(10) DEFAULT NULL,
  `price_HS` int(11) NOT NULL,
  `price_HL` int(11) NOT NULL,
  `price_IS` int(11) NOT NULL,
  `price_IL` int(11) NOT NULL,
  PRIMARY KEY (`menu_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE `notice` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `notice_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8



CREATE TABLE `push_list` (
  `e_mail` varchar(50) NOT NULL,
  `manager_flag` varchar(1) NOT NULL,
  `push_token` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8



CREATE TABLE `sensor` (
  `sensor_id` varchar(10) NOT NULL,
  `category` varchar(10) NOT NULL,
  `sensor_value` varchar(10) NOT NULL,
  `sensor_date` datetime DEFAULT NULL,
  PRIMARY KEY (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



CREATE TABLE `table_info` (
  `table_number` int(11) NOT NULL,
  `blanket` varchar(1) NOT NULL,
  `consent` varchar(1) NOT NULL,
  `cushion` varchar(1) NOT NULL,
  `seat_image` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8



CREATE TABLE `orderlist` (
  `order_id` varchar(50) NOT NULL,
  `order_name` varchar(20) NOT NULL,
  `order_date` datetime NOT NULL,
  `order_content` text NOT NULL,
  `order_status` varchar(45) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8 

select * from menu;

drop table cart;





