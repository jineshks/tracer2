ALTER TABLE  `ticket` ADD COLUMN `progress` INT(3) DEFAULT 0 NULL AFTER `actul_hours`; 

CREATE TABLE  `test_case`( `id` BIGINT(20) NOT NULL AUTO_INCREMENT, `project_id` BIGINT(20) NOT NULL, `ticket_id` BIGINT(20), `t_case` TEXT NOT NULL, `exp_result` VARCHAR(255), `act_result` TEXT, `is_passed` TINYINT(1) DEFAULT TRUE, `created_by_id` BIGINT(20), `created` DATETIME, PRIMARY KEY (`id`), CONSTRAINT `fk_project_id` FOREIGN KEY (`project_id`) REFERENCES `tracer`.`project`(`id`), CONSTRAINT `fk_ticket_id` FOREIGN KEY (`ticket_id`) REFERENCES `tracer`.`ticket`(`id`), CONSTRAINT `fk_created_by_id` FOREIGN KEY (`created_by_id`) REFERENCES `tracer`.`user`(`id`) );

INSERT INTO  `test_case`(`id`,`project_id`,`ticket_id`,`t_case`,`exp_result`,`act_result`,`is_passed`,`created_by_id`,`created`) VALUES ( NULL,'1','3','when user try to login with correct credentials , he should got home page as response.','home page','got home page','1','1','2013-12-23 11:11:05'); 

INSERT INTO  `test_case`(`id`,`project_id`,`ticket_id`,`t_case`,`exp_result`,`act_result`,`is_passed`,`created_by_id`,`created`) VALUES ( NULL,'1','3','when user try with invalid credentials then he should got proper error message.','error message','invalid credentials.','1','2','2013-12-23 11:12:44'); 

CREATE TABLE  `test_phase`( `id` BIGINT(20) NOT NULL, `phase` INT(3), `status` TINYINT(1) DEFAULT TRUE, `test_case_id` BIGINT(20), PRIMARY KEY (`id`), CONSTRAINT `fk_test_case_id` FOREIGN KEY (`test_case_id`) REFERENCES `tracer`.`test_case`(`id`) );

ALTER TABLE `test_phase` CHANGE `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT; 

/*2013-12-30*/
ALTER TABLE  `test_case` ADD COLUMN `milestone_id` BIGINT(20) DEFAULT 0 NULL AFTER `created`; 
ALTER TABLE  `test_case` CHANGE `ticket_id` `ticket_id` BIGINT(20) NOT NULL; 
ALTER TABLE  `test_phase` CHANGE `test_case_id` `test_case_id` BIGINT(20) NOT NULL; 