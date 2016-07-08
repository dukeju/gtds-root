INSERT INTO `gtds`.`departments` (`id`, `name`) VALUES ('02', '管理学院'); 
INSERT INTO `gtds`.`departments` (`id`, `name`) VALUES ('07', '经济与贸易学院'); 
INSERT INTO `gtds`.`departments` (`id`, `name`) VALUES ('05', '应用数学学院'); 

INSERT INTO `gtds`.`students` (`id`, `name`, `password`, `email`, `phone`, `major`, `class`, `dept_id`) VALUES ('201202050125', '小标', '123', 'xiaohao@163.com', '321', '信息管理与信息系统专业', '1', '02'); 
INSERT INTO `gtds`.`students` (`id`, `name`, `password`, `email`, `phone`, `major`, `class`, `dept_id`) VALUES ('201202070316', '小明', '456', 'xiaoming@163.com', '654', '会计学', '3', '02'); 
INSERT INTO `gtds`.`students` (`id`, `name`, `password`, `email`, `phone`, `major`, `class`, `dept_id`) VALUES ('201204021110', '小豪', '789', 'xiaohao@163.com', '987', '国际金融', '1', '07'); 

INSERT INTO `gtds`.`teachers` (`id`, `name`, `password`, `email`, `phone`, `dept_id`) VALUES ('2008020001', '丁小翔', '147', 'xiaoxiang@163.com', '741', '02'); 
INSERT INTO `gtds`.`teachers` (`id`, `name`, `password`, `email`, `phone`, `dept_id`) VALUES ('2006070002', '苏大马', '258', 'dama@163.om', '852', '07');
