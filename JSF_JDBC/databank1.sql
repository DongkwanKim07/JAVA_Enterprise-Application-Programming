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

CREATE TABLE IF NOT EXISTS `databank`.`program`(
  `name` VARCHAR(60) NULL)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `databank`.`level`(
  `name` VARCHAR(10) NULL)
ENGINE = InnoDB;

INSERT INTO `databank`.`program`(`name`) VALUES ('Accounting');
INSERT INTO `databank`.`program`(`name`) VALUES ('Addictions and Mental Health');
INSERT INTO `databank`.`program`(`name`) VALUES ('Applied Management');
INSERT INTO `databank`.`program`(`name`) VALUES ('Autism and Behavioural Science');
INSERT INTO `databank`.`program`(`name`) VALUES ('Autism Studies');
INSERT INTO `databank`.`program`(`name`) VALUES ('Bachelor of Business Administration');
INSERT INTO `databank`.`program`(`name`) VALUES ('Bookkeeping');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business – Accounting');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business – Marketing (Year 1)');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business – Trades');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business Administration – Accounting');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business Administration – Core');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business Administration – General Business');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business Administration – Human Resources');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business Administration – General Business');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business Analysis Studies');
INSERT INTO `databank`.`program`(`name`) VALUES ('Business Fundamentals');
INSERT INTO `databank`.`program`(`name`) VALUES ('Child and Youth Care (Year 1)');
INSERT INTO `databank`.`program`(`name`) VALUES ('Client Relations');
INSERT INTO `databank`.`program`(`name`) VALUES ('Commercial Estimating');
INSERT INTO `databank`.`program`(`name`) VALUES ('Community and Justice Services');
INSERT INTO `databank`.`program`(`name`) VALUES ('Computer Programming');
INSERT INTO `databank`.`program`(`name`) VALUES ('Computer Programming – Part Time');
INSERT INTO `databank`.`program`(`name`) VALUES ('Conflict Management');
INSERT INTO `databank`.`program`(`name`) VALUES ('Construction Management');
INSERT INTO `databank`.`program`(`name`) VALUES ('Construction Project Management');
INSERT INTO `databank`.`program`(`name`) VALUES ('Contemporary Educational Practices');
INSERT INTO `databank`.`program`(`name`) VALUES ('Creative Writing');
INSERT INTO `databank`.`program`(`name`) VALUES ('Digital Analytics');
INSERT INTO `databank`.`program`(`name`) VALUES ('Early Childhood Education');
INSERT INTO `databank`.`program`(`name`) VALUES ('Early Childhood Education Administration');
INSERT INTO `databank`.`program`(`name`) VALUES ('Emergency Management');
INSERT INTO `databank`.`program`(`name`) VALUES ('Fitness and Health Promotion');
INSERT INTO `databank`.`program`(`name`) VALUES ('Fitness and Lifestyle Management');
INSERT INTO `databank`.`program`(`name`) VALUES ('Forensic Accounting and Fraud Investigations');
INSERT INTO `databank`.`program`(`name`) VALUES ('Fundraising Management');
INSERT INTO `databank`.`program`(`name`) VALUES ('Home Inspection');
INSERT INTO `databank`.`program`(`name`) VALUES ('Human Resources Management Studies');
INSERT INTO `databank`.`program`(`name`) VALUES ('Introduction to Canadian Healthcare Studies');
INSERT INTO `databank`.`program`(`name`) VALUES ('Labour Relations');
INSERT INTO `databank`.`program`(`name`) VALUES ('Leadership Development Series');
INSERT INTO `databank`.`program`(`name`) VALUES ('Learning Analytics');
INSERT INTO `databank`.`program`(`name`) VALUES ('Medical Transcription');
INSERT INTO `databank`.`program`(`name`) VALUES ('Military Arts and Science');
INSERT INTO `databank`.`program`(`name`) VALUES ('Mobile and Web User Experience Design');
INSERT INTO `databank`.`program`(`name`) VALUES ('Nursing Unit Clerk');
INSERT INTO `databank`.`program`(`name`) VALUES ('Occupational Safety and Health');
INSERT INTO `databank`.`program`(`name`) VALUES ('Office Administration – Executive');
INSERT INTO `databank`.`program`(`name`) VALUES ('Office Administration – General');
INSERT INTO `databank`.`program`(`name`) VALUES ('Online Learning Design and Delivery');
INSERT INTO `databank`.`program`(`name`) VALUES ('Police Foundations');
INSERT INTO `databank`.`program`(`name`) VALUES ('Project Management Studies');
INSERT INTO `databank`.`program`(`name`) VALUES ('Property Management Foundations');
INSERT INTO `databank`.`program`(`name`) VALUES ('Regulatory Affairs – Sciences');
INSERT INTO `databank`.`program`(`name`) VALUES ('Small Business Accounting');
INSERT INTO `databank`.`program`(`name`) VALUES ('Social Media');
INSERT INTO `databank`.`program`(`name`) VALUES ('Strength and Conditioning Training');
INSERT INTO `databank`.`program`(`name`) VALUES ('Teachers and Trainers of Adults');
INSERT INTO `databank`.`program`(`name`) VALUES ('Technical Writer');
INSERT INTO `databank`.`program`(`name`) VALUES ('Trades Entrepreneurship');
INSERT INTO `databank`.`program`(`name`) VALUES ('Trades Leadership');
INSERT INTO `databank`.`program`(`name`) VALUES ('Trades Management');
INSERT INTO `databank`.`program`(`name`) VALUES ('Victimology');
INSERT INTO `databank`.`program`(`name`) VALUES ('Web Development and Internet Applications');
INSERT INTO `databank`.`program`(`name`) VALUES ('Web Development and Internet Applications (Part-Time)');
INSERT INTO `databank`.`program`(`name`) VALUES ('Working with Dementia Clients – Multidiscipline');

INSERT INTO `databank`.`level`(`name`) VALUES ('FRESHMAN');
INSERT INTO `databank`.`level`(`name`) VALUES ('SOPHOMORE');
INSERT INTO `databank`.`level`(`name`) VALUES ('JUNIOR');
INSERT INTO `databank`.`level`(`name`) VALUES ('SENIOR');
INSERT INTO `databank`.`level`(`name`) VALUES ('TERMINAL');

