-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema thomahadb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `thomahadb` ;

-- -----------------------------------------------------
-- Schema thomahadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `thomahadb` DEFAULT CHARACTER SET utf8 ;
USE `thomahadb` ;

-- -----------------------------------------------------
-- Table `event_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_type` ;

CREATE TABLE IF NOT EXISTS `event_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rating` ;

CREATE TABLE IF NOT EXISTS `rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event` ;

CREATE TABLE IF NOT EXISTS `event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `location` VARCHAR(100) NULL,
  `date` DATE NULL,
  `description` TEXT NULL,
  `image` VARCHAR(250) NULL,
  `event_type_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `rating_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_event_type_idx` (`event_type_id` ASC),
  INDEX `fk_event_category1_idx` (`category_id` ASC),
  INDEX `fk_event_rating1_idx` (`rating_id` ASC),
  CONSTRAINT `fk_event_event_type`
    FOREIGN KEY (`event_type_id`)
    REFERENCES `event_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_rating1`
    FOREIGN KEY (`rating_id`)
    REFERENCES `rating` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `food_type` ;

CREATE TABLE IF NOT EXISTS `food_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Restaurant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Restaurant` ;

CREATE TABLE IF NOT EXISTS `Restaurant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `rating_id` INT NOT NULL,
  `food_type_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `location` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Restaraunts_rating1_idx` (`rating_id` ASC),
  INDEX `fk_Restaraunts_food_type1_idx` (`food_type_id` ASC),
  INDEX `fk_Restaraunts_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_Restaraunts_rating1`
    FOREIGN KEY (`rating_id`)
    REFERENCES `rating` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Restaraunts_food_type1`
    FOREIGN KEY (`food_type_id`)
    REFERENCES `food_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Restaraunts_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category_has_Restaraunts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category_has_Restaraunts` ;

CREATE TABLE IF NOT EXISTS `category_has_Restaraunts` (
  `category_id` INT NOT NULL,
  `Restaraunts_id` INT NOT NULL,
  PRIMARY KEY (`category_id`, `Restaraunts_id`),
  INDEX `fk_category_has_Restaraunts_Restaraunts1_idx` (`Restaraunts_id` ASC),
  INDEX `fk_category_has_Restaraunts_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_category_has_Restaraunts_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_has_Restaraunts_Restaraunts1`
    FOREIGN KEY (`Restaraunts_id`)
    REFERENCES `Restaurant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS thomaha@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'thomaha'@'localhost' IDENTIFIED BY 'thomaha';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'thomaha'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `event_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `thomahadb`;
INSERT INTO `event_type` (`id`, `name`, `description`) VALUES (1, 'Educational', NULL);
INSERT INTO `event_type` (`id`, `name`, `description`) VALUES (2, 'Entertainment', NULL);
INSERT INTO `event_type` (`id`, `name`, `description`) VALUES (3, 'Arts & Crafts', NULL);
INSERT INTO `event_type` (`id`, `name`, `description`) VALUES (4, 'Holiday', NULL);
INSERT INTO `event_type` (`id`, `name`, `description`) VALUES (5, 'Get Active', NULL);
INSERT INTO `event_type` (`id`, `name`, `description`) VALUES (6, 'Classes', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `thomahadb`;
INSERT INTO `category` (`id`, `name`, `description`) VALUES (1, 'Family friendly', NULL);
INSERT INTO `category` (`id`, `name`, `description`) VALUES (2, 'Infant', NULL);
INSERT INTO `category` (`id`, `name`, `description`) VALUES (3, 'Toddler', NULL);
INSERT INTO `category` (`id`, `name`, `description`) VALUES (6, 'Teen', NULL);
INSERT INTO `category` (`id`, `name`, `description`) VALUES (4, 'Young Adult', NULL);
INSERT INTO `category` (`id`, `name`, `description`) VALUES (5, 'Whole family', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `thomahadb`;
INSERT INTO `rating` (`id`, `name`, `description`) VALUES (1, 'Free', NULL);
INSERT INTO `rating` (`id`, `name`, `description`) VALUES (2, '$', NULL);
INSERT INTO `rating` (`id`, `name`, `description`) VALUES (3, '$$', NULL);
INSERT INTO `rating` (`id`, `name`, `description`) VALUES (4, '$$$', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event`
-- -----------------------------------------------------
START TRANSACTION;
USE `thomahadb`;
INSERT INTO `event` (`id`, `name`, `location`, `date`, `description`, `image`, `event_type_id`, `category_id`, `rating_id`) VALUES (1, 'Casa Madrigal', 'Childrens Museum', '2023-01-10', 'Enjoy a weekend inspired by the fantastical Madrigal family\'s Casita! Activities will include make-your-own Magic Candles, Encanto Science Shows, design-your-own family tree, engineer your own Casita', NULL, 2, 3, 2);
INSERT INTO `event` (`id`, `name`, `location`, `date`, `description`, `image`, `event_type_id`, `category_id`, `rating_id`) VALUES (2, 'Railroad and Sun Valley', 'Sun Valley', '2023-02-10', 'Union Pacific developed, owned, and operated America’s first ski destination at Sun Valley, Idaho, from 1935 to 1964 as part of a 20th Century program to increase destination travel on Union Pacific', NULL, 5, 5, 3);
INSERT INTO `event` (`id`, `name`, `location`, `date`, `description`, `image`, `event_type_id`, `category_id`, `rating_id`) VALUES (3, 'Aquariaumn Backstage', 'Omaha Zoo', '2023-01-22', 'Have you ever wondered how much water is above your head as you walk through the tunnel of the Walter Scott Aquarium, or how we feed the sharks and rays who call the aquarium home?', NULL, 2, 5, 2);
INSERT INTO `event` (`id`, `name`, `location`, `date`, `description`, `image`, `event_type_id`, `category_id`, `rating_id`) VALUES (4, 'Washed Ashore', 'Lauritzen Gardens', '2023-01-23', 'Giant sculptures of sea life, made from marine debris collected on Oregon\'s beaches, graphically illustrate the tragedy of plastic pollution in our ocean and waterways. The epic exhibit includes a titanic triggerfish, a pair of grand penguins, and an 11-foot-long shark named Chompers.', NULL, 3, 5, 2);
INSERT INTO `event` (`id`, `name`, `location`, `date`, `description`, `image`, `event_type_id`, `category_id`, `rating_id`) VALUES (5, 'Mac & Cheese Binge', '31st Avenue', '2023-01-22', 'The Mac & Cheese Binge celebrates one of food\'s greatest marriages: Mac and Cheese. ', NULL, 2, 6, 3);
INSERT INTO `event` (`id`, `name`, `location`, `date`, `description`, `image`, `event_type_id`, `category_id`, `rating_id`) VALUES (6, 'Hall of Heroes', 'Durham Museum', '2023-01-28', 'Ever wonder how your favorite super-powered characters were created? Hall of Heroes provides an immersive experience for visitors to understand the world of heroes, crime-fighters and gadgets.', NULL, 2, 3, 2);
INSERT INTO `event` (`id`, `name`, `location`, `date`, `description`, `image`, `event_type_id`, `category_id`, `rating_id`) VALUES (7, 'Yoga in the Park', 'Midtown Crossing', '2023-01-30', 'Yoga in the park.', NULL, 5, 5, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `food_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `thomahadb`;
INSERT INTO `food_type` (`id`, `name`, `description`) VALUES (1, 'Mexican', NULL);
INSERT INTO `food_type` (`id`, `name`, `description`) VALUES (2, 'American', NULL);
INSERT INTO `food_type` (`id`, `name`, `description`) VALUES (3, 'Chinese', NULL);
INSERT INTO `food_type` (`id`, `name`, `description`) VALUES (4, 'Japanese', NULL);
INSERT INTO `food_type` (`id`, `name`, `description`) VALUES (5, 'Indian', NULL);
INSERT INTO `food_type` (`id`, `name`, `description`) VALUES (6, 'Thai', NULL);
INSERT INTO `food_type` (`id`, `name`, `description`) VALUES (7, 'Peruvian', NULL);
INSERT INTO `food_type` (`id`, `name`, `description`) VALUES (8, 'Ethiopian', NULL);
INSERT INTO `food_type` (`id`, `name`, `description`) VALUES (9, 'Pizza', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Restaurant`
-- -----------------------------------------------------
START TRANSACTION;
USE `thomahadb`;
INSERT INTO `Restaurant` (`id`, `name`, `description`, `rating_id`, `food_type_id`, `category_id`, `location`) VALUES (1, 'Mulas', 'Great atmospher! If you are bringing kids go early. ', 2, 1, 5, NULL);
INSERT INTO `Restaurant` (`id`, `name`, `description`, `rating_id`, `food_type_id`, `category_id`, `location`) VALUES (2, 'Ollie and Hobbes', 'Modern. Has kid menu. May not be suitable for infants.', 3, 2, 3, NULL);
INSERT INTO `Restaurant` (`id`, `name`, `description`, `rating_id`, `food_type_id`, `category_id`, `location`) VALUES (3, 'Spin', 'Pizza. Often times loud so kids are welcome!', 3, 9, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category_has_Restaraunts`
-- -----------------------------------------------------
START TRANSACTION;
USE `thomahadb`;
INSERT INTO `category_has_Restaraunts` (`category_id`, `Restaraunts_id`) VALUES (5, 1);
INSERT INTO `category_has_Restaraunts` (`category_id`, `Restaraunts_id`) VALUES (3, 2);
INSERT INTO `category_has_Restaraunts` (`category_id`, `Restaraunts_id`) VALUES (4, 2);
INSERT INTO `category_has_Restaraunts` (`category_id`, `Restaraunts_id`) VALUES (6, 2);
INSERT INTO `category_has_Restaraunts` (`category_id`, `Restaraunts_id`) VALUES (1, 3);

COMMIT;

