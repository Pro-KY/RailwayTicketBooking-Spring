-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema railway_ticket_booking_spring
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `railway_ticket_booking_spring` ;

-- -----------------------------------------------------
-- Schema railway_ticket_booking_spring
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `railway_ticket_booking_spring` DEFAULT CHARACTER SET utf8mb4 ;
USE `railway_ticket_booking_spring` ;

-- -----------------------------------------------------
-- Table `railway_ticket_booking_spring`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking_spring`.`role` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking_spring`.`role` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking_spring`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking_spring`.`user` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking_spring`.`user` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(90) NULL,
  `last_name` VARCHAR(90) NULL,
  `email` VARCHAR(80) NULL,
  `password` VARCHAR(80) NULL,
  `user_type_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_users_role1_idx` (`user_type_id` ASC),
  CONSTRAINT `fk_users_role1`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `railway_ticket_booking_spring`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking_spring`.`station`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking_spring`.`station` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking_spring`.`station` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking_spring`.`route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking_spring`.`route` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking_spring`.`route` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `departure_station_id` BIGINT(10) NOT NULL,
  `arrival_station_id` BIGINT(10) NOT NULL,
  `departure_date` DATE NULL,
  `arrival_date` DATE NULL,
  `departure_time` TIME NULL,
  `arrival_time` TIME NULL,
  `route_length_factor` DOUBLE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_route_station1_idx` (`departure_station_id` ASC),
  INDEX `fk_route_station2_idx` (`arrival_station_id` ASC),
  CONSTRAINT `fk_route_station1`
    FOREIGN KEY (`departure_station_id`)
    REFERENCES `railway_ticket_booking_spring`.`station` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_station2`
    FOREIGN KEY (`arrival_station_id`)
    REFERENCES `railway_ticket_booking_spring`.`station` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking_spring`.`train_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking_spring`.`train_type` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking_spring`.`train_type` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(60) NOT NULL,
  `seat_price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking_spring`.`train`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking_spring`.`train` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking_spring`.`train` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `train_type_id` BIGINT(10) NOT NULL,
  `route_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_train_train_type1_idx` (`train_type_id` ASC),
  INDEX `fk_train_route1_idx` (`route_id` ASC),
  CONSTRAINT `fk_train_train_type1`
    FOREIGN KEY (`train_type_id`)
    REFERENCES `railway_ticket_booking_spring`.`train_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_train_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `railway_ticket_booking_spring`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking_spring`.`invoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking_spring`.`invoice` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking_spring`.`invoice` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10) NOT NULL,
  `train_id` BIGINT(10) NOT NULL,
  `seats_amount` INT NOT NULL,
  `price` DECIMAL(10,2) NULL,
  `date_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payment_invoice_user1_idx` (`user_id` ASC),
  INDEX `fk_payment_invoice_train1_idx` (`train_id` ASC),
  CONSTRAINT `fk_payment_invoice_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `railway_ticket_booking_spring`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_invoice_train1`
    FOREIGN KEY (`train_id`)
    REFERENCES `railway_ticket_booking_spring`.`train` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `railway_ticket_booking_spring`.`route_station`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railway_ticket_booking_spring`.`route_station` ;

CREATE TABLE IF NOT EXISTS `railway_ticket_booking_spring`.`route_station` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `route_id` BIGINT(10) NOT NULL,
  `station_id` BIGINT(10) NOT NULL,
  INDEX `fk_route_has_station_station1_idx` (`station_id` ASC),
  INDEX `fk_route_has_station_route1_idx` (`route_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_route_has_station_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `railway_ticket_booking_spring`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_has_station_station1`
    FOREIGN KEY (`station_id`)
    REFERENCES `railway_ticket_booking_spring`.`station` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- -----------------------------------------------------
-- INSERT DATA
-- -----------------------------------------------------
# first route stations (Киев - Сарны)
insert into station(name) values('Киев');
insert into station(name) values('Фастов');
insert into station(name) values('Житомир');
insert into station(name) values('Коростень');
insert into station(name) values('Олевск');
insert into station(name) values('Остки');
insert into station(name) values('Клесов');
insert into station(name) values('Сарны');

#second route stations(Ровно- Киев)
insert into station(name) values('Ровно');
insert into station(name) values('Здолбунов');
insert into station(name) values('Острог');
insert into station(name) values('Полонное');
insert into station(name) values('Мирополь');
insert into station(name) values('Фастов 1');
-- insert into station(name) values('Киев');

#third route stations(Львов-Киев)
insert into station(name) values('Львов');
insert into station(name) values('Тернополь');
insert into station(name) values('Хмельницкий');
insert into station(name) values('Винница');
-- insert into station(name) values('Фастов 1');
-- insert into station(name) values('Киев');

#fourth route stations(Ровно-Львов)
-- insert into station(name) values('Ровно');
-- insert into station(name) values('Здолбунов');
insert into station(name) values('Дубно');
insert into station(name) values('Подзамче');
-- insert into station(name) values('Львов');

#fifth route stations(Киев-Одесса)
-- insert into station(name) values('Киев');
-- insert into station(name) values('Винница');
insert into station(name) values('Подольск');
insert into station(name) values('Одесса');

#sixth oute stations(Тернополь-Киев)
insert into station(name) values('Подволочиск');
insert into station(name) values('Волочиск');

#seventh route stations(Харьков-Киев)
insert into station(name) values('Харьков');
insert into station(name) values('Сумы');
insert into station(name) values('Ворожба');
insert into station(name) values('Путивль');
insert into station(name) values('Конотоп');
insert into station(name) values('Нежин');

#eights route stations(Черновцы-Киев)
insert into station(name) values('Черновцы');
insert into station(name) values('Ивано-Франковск');

#ROUTE
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(1, 8, '2019.10.26', '2019.10.26', '13:36:00', '21:46:00', 0.8);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(9, 1, '2019.10.31', '2019.11.01', '21:15:00', '05:58:00', 0.7);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(15, 1,'2019.10.31', '2019.11.01', '17:40:00', '03:38:00', 0.6);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(9, 15,'2019.10.31', '2019.10.31', '06:45:00', '09:35:00', 0.5);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(1, 22,'2019.10.31', '2019.11.01', '21:15:00', '05:58:00', 0.8);

insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(16, 1,'2019.10.31', '2019.11.01', '20:15:00', '04:58:00', 0.5);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(25, 1,'2019.10.31', '2019.11.01', '19:15:00', '07:58:00', 0.6);
insert into route(departure_station_id, arrival_station_id, departure_date, arrival_date, departure_time, arrival_time, route_length_factor)
values(31, 1,'2019.10.31', '2019.11.01', '22:15:00', '09:58:00', 0.9);


#ROUTE_STATION
#(Киев - Сарны)
insert into route_station(route_id, station_id) values(1, 1);
insert into route_station(route_id, station_id) values(1, 2);
insert into route_station(route_id, station_id) values(1, 3);
insert into route_station(route_id, station_id) values(1, 4);
insert into route_station(route_id, station_id) values(1, 5);
insert into route_station(route_id, station_id) values(1, 6);
insert into route_station(route_id, station_id) values(1, 7);
insert into route_station(route_id, station_id) values(1, 8);
#(Харьков-Киев)
insert into route_station(route_id, station_id) values(2, 9);
insert into route_station(route_id, station_id) values(2, 10);
insert into route_station(route_id, station_id) values(2, 11);
insert into route_station(route_id, station_id) values(2, 12);
insert into route_station(route_id, station_id) values(2, 13);
insert into route_station(route_id, station_id) values(2, 14);
insert into route_station(route_id, station_id) values(2, 1);
#(Львов-Киев)
insert into route_station(route_id, station_id) values(3, 15);
insert into route_station(route_id, station_id) values(3, 16);
insert into route_station(route_id, station_id) values(3, 17);
insert into route_station(route_id, station_id) values(3, 18);
insert into route_station(route_id, station_id) values(3, 14);
insert into route_station(route_id, station_id) values(3, 1);
#(Ровно-Львов)
insert into route_station(route_id, station_id) values(4, 19);
insert into route_station(route_id, station_id) values(4, 10);
insert into route_station(route_id, station_id) values(4, 20);
insert into route_station(route_id, station_id) values(4, 21);
insert into route_station(route_id, station_id) values(4, 22);
#(Киев-Одесса)
insert into route_station(route_id, station_id) values(5, 1);
insert into route_station(route_id, station_id) values(5, 18);
insert into route_station(route_id, station_id) values(5, 21);
insert into route_station(route_id, station_id) values(5, 22);

#(Тернополь-Киев)
insert into route_station(route_id, station_id) values(6, 16);
insert into route_station(route_id, station_id) values(6, 23);
insert into route_station(route_id, station_id) values(6, 24);
insert into route_station(route_id, station_id) values(6, 17);
insert into route_station(route_id, station_id) values(6, 18);
insert into route_station(route_id, station_id) values(6, 14);
insert into route_station(route_id, station_id) values(6, 1);
#(Харьков-Киев)
insert into route_station(route_id, station_id) values(7, 25);
insert into route_station(route_id, station_id) values(7, 26);
insert into route_station(route_id, station_id) values(7, 27);
insert into route_station(route_id, station_id) values(7, 28);
insert into route_station(route_id, station_id) values(7, 29);
insert into route_station(route_id, station_id) values(7, 30);
insert into route_station(route_id, station_id) values(7, 1);
#(Черновцы-Киев)
insert into route_station(route_id, station_id) values(8, 31);
insert into route_station(route_id, station_id) values(8, 32);
insert into route_station(route_id, station_id) values(8, 15);
insert into route_station(route_id, station_id) values(8, 1);

#TRAIN_TYPE
insert into train_type(type, seat_price) values('Люкс', 100.00);
insert into train_type(type, seat_price) values('Среднего класса', 50.00);
insert into train_type(type, seat_price) values('Эконом', 30.00);
insert into train_type(type, seat_price) values('Электричка', 15.00);

#USER_TYPE
insert into role(name) values('anonymous');
insert into role(name) values('administrator');
insert into role(name) values('user');

#USER
insert into user(first_name, last_name, email, password, user_type_id) values('Василий', 'Админский', 'vas.admin@gmail.com', '$2a$10$sY8mZ0tasboUUiafKIZdEOBWBba9xOwHMAWzRQynNiKZJTPf4K7eK', 2);
insert into user(first_name, last_name, email, password, user_type_id) values('Геннадий', 'Пасажирский', 'gen.omel@gmail.com', '$2a$10$BPRhg7i4GbELxJvgaSU8iela6k80Y/7GO1LBDr7gk4PXe9k6q1ndq', 3);
insert into user(first_name, last_name, email, password, user_type_id) values('Валентина', 'Пиронова', 'val.pir@gmail.com', '$2a$10$H.SQCDP/UJSiFxpg1CMwI.SzsujrUDQSBYiIyCGZwc0A6uLDmS8Du', 3);
insert into user(first_name, last_name, email, password, user_type_id) values('Офанасий', 'Билетский', 'ofan.bill@gmail.com', '$2a$10$Z/x83tjnL55Mc4X2mNqLpOtn3AppELL0gugSiUe9DUOFp7Ez.QLiC', 3);
insert into user(first_name, last_name, email, password, user_type_id) values('Анастасия', 'Проводницкая', 'anas.prov@gmail.com', '$2a$10$WNFH2dS/fMhD7agD.rien.jwGqDYHpGiFWYCgntLoBvoBfvpnDcGC', 3);

#TRAIN
insert into train(train_type_id, route_id) values(1, 5);
insert into train(train_type_id, route_id) values(2, 1);
insert into train(train_type_id, route_id) values(1, 2);
insert into train(train_type_id, route_id) values(4, 4);
insert into train(train_type_id, route_id) values(3, 3);
insert into train(train_type_id, route_id) values(1, 6);
insert into train(train_type_id, route_id) values(2, 7);
insert into train(train_type_id, route_id) values(3, 8);





