-- --------------------------------------------------------------------
-- Assignment 2 uses the same Schema and Table as Lab1 and Assignment 1
-- refer back to those versions of 'databank.sql' for setup instructions
-- --------------------------------------------------------------------

-- Assignment 2: add two new columns and ensure all student rows have CREATED, UPDATED and VERSION columns set
USE `databank`;
ALTER TABLE `databank`.`student` ADD COLUMN `updated` DATETIME NULL;
ALTER TABLE `databank`.`student` ADD COLUMN `version` BIGINT NOT NULL default 1;
UPDATE `databank`.`student` SET created=now(), updated=now(), version=1;