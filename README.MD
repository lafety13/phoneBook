
Json storage is a default so you can not to load property file.

For running app with MySql you have to load property file with next settings:<br>
profile=mysql<br>
username=yourUsername<br>
password=yourPassword<br>

Also you can load property file for json storage. Use this settings.<br>
profile=default<br>
jsonpath=yourPath<br>



DROP SCHEMA IF EXISTS `phonebook`;<br>

CREATE SCHEMA IF NOT EXISTS `phonebook`<br>
DEFAULT CHARACTER SET utf8;<br>
USE `phonebook`;<br>

CREATE TABLE IF NOT EXISTS `phonebook`.`users` (<br>
  `id`       SERIAL PRIMARY KEY,<br>
  `login`    VARCHAR(45) NOT NULL,<br>
  `password` VARCHAR(45) NOT NULL,<br>
  `name`     VARCHAR(45) NULL<br>
)<br>
  ENGINE = InnoDB<br>
  CHARACTER SET = utf8;<br>


CREATE TABLE IF NOT EXISTS `phonebook`.`contact` (<br>
  `id`          SERIAL PRIMARY KEY,<br>
  `lastname`    VARCHAR(45) NOT NULL,<br>
  `firstname`   VARCHAR(45) NOT NULL,<br>
  `middlename`  VARCHAR(45) NOT NULL,<br>
  `mobilephone` VARCHAR(45) NOT NULL,<br>
  `homephone`   VARCHAR(45) NULL,<br>
  `address`     VARCHAR(45) NULL,<br>
  `email`       VARCHAR(45) NULL,<br>
  `user_id`     INT REFERENCES users (id)<br>
)<br>
  ENGINE = InnoDB<br>
  CHARACTER SET = utf8;<br>