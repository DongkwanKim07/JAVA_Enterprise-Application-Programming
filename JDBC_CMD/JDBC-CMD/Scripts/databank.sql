-- ----------------------------------------------------------------
-- Create Schema and User
--
-- Uncomment following statements only if you did NOT use the 'Step0.sql'
--   file during your MySQL Setup.  You MUST be logged in as MySQL superuser
--   account 'root@'localhost' for these statements to work:
-- DROP SCHEMA IF EXISTS `databank`;
-- CREATE SCHEMA IF NOT EXISTS `databank` DEFAULT CHARACTER SET utf8mb4;
-- DROP USER IF EXISTS `cst8277`@`localhost`;
-- CREATE USER IF NOT EXISTS 'cst8277'@'localhost' IDENTIFIED BY '8277';
-- GRANT ALL ON `databank`.* TO 'cst8277'@'localhost';
--
-- ----------------------------------------------------------------

-- -----------------------------------------------------
-- Create Table `databank`.`student`
-- -----------------------------------------------------
USE `databank`;
CREATE TABLE IF NOT EXISTS `databank`.`student`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NULL,
  `phone` VARCHAR(10) NULL,
  `level` VARCHAR(10) NULL,
  `program` VARCHAR(60) NULL,
  `created` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
