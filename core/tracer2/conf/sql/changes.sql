ALTER TABLE `mile_stone` ADD COLUMN `updatedTime` TIMESTAMP NULL AFTER `project_id`; 
ALTER TABLE `mile_stone` CHANGE `updatedTime` `updatedTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL;


/*ALTER TABLE  `ticket` ADD COLUMN `progress` INT(3) DEFAULT 0 NULL AFTER `actul_hours`; */