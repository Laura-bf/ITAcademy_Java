-- MySQL Script generated by MySQL Workbench
-- Tue Jul 13 17:53:58 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Óptica
-- -----------------------------------------------------
-- Informatización de la gestión de clientes y venta de gafas
DROP SCHEMA IF EXISTS `Óptica` ;

-- -----------------------------------------------------
-- Schema Óptica
--
-- Informatización de la gestión de clientes y venta de gafas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Óptica` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `Óptica` ;

-- -----------------------------------------------------
-- Table `Óptica`.`Direcciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Óptica`.`Direcciones` ;

CREATE TABLE IF NOT EXISTS `Óptica`.`Direcciones` (
  `Direcciones_id` INT NOT NULL AUTO_INCREMENT,
  `Calle` VARCHAR(45) NOT NULL,
  `Numero` VARCHAR(5) NOT NULL,
  `Piso` VARCHAR(15) NULL,
  `Puerta` VARCHAR(3) NULL,
  `Ciudad` VARCHAR(15) NOT NULL,
  `CP` VARCHAR(5) NOT NULL,
  `Pais` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Direcciones_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Óptica`.`Clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Óptica`.`Clientes` ;

CREATE TABLE IF NOT EXISTS `Óptica`.`Clientes` (
  `Clientes_id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(15) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `Direcciones_id` INT NOT NULL,
  `Telefono` VARCHAR(11) NOT NULL,
  `Email` VARCHAR(45) NULL,
  `Fecha_Alta` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de registro inicial del cliente',
  `RecomendadoPor` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Clientes_id`),
  INDEX `fk_Clientes_Direcciones1_idx` (`Direcciones_id` ASC) VISIBLE,
  INDEX `fk_Clientes_Clientes1_idx` (`RecomendadoPor` ASC) VISIBLE,
  CONSTRAINT `fk_Clientes_Direcciones1`
    FOREIGN KEY (`Direcciones_id`)
    REFERENCES `Óptica`.`Direcciones` (`Direcciones_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Clientes_Clientes1`
    FOREIGN KEY (`RecomendadoPor`)
    REFERENCES `Óptica`.`Clientes` (`Clientes_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Óptica`.`Proveedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Óptica`.`Proveedor` ;

CREATE TABLE IF NOT EXISTS `Óptica`.`Proveedor` (
  `Proveedor_id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Direcciones_id` INT NOT NULL,
  `Telefono` VARCHAR(11) NOT NULL,
  `Fax` VARCHAR(11) NULL,
  `NIF` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`Proveedor_id`),
  INDEX `fk_Proveedor_Direcciones1_idx` (`Direcciones_id` ASC) VISIBLE,
  CONSTRAINT `fk_Proveedor_Direcciones1`
    FOREIGN KEY (`Direcciones_id`)
    REFERENCES `Óptica`.`Direcciones` (`Direcciones_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Óptica`.`Marca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Óptica`.`Marca` ;

CREATE TABLE IF NOT EXISTS `Óptica`.`Marca` (
  `Marca_id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Proveedor_id` INT NOT NULL,
  PRIMARY KEY (`Marca_id`, `Proveedor_id`),
  INDEX `fk_Marca_Proveedor1_idx` (`Proveedor_id` ASC) VISIBLE,
  CONSTRAINT `fk_Marca_Proveedor1`
    FOREIGN KEY (`Proveedor_id`)
    REFERENCES `Óptica`.`Proveedor` (`Proveedor_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Óptica`.`Gafas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Óptica`.`Gafas` ;

CREATE TABLE IF NOT EXISTS `Óptica`.`Gafas` (
  `Gafas_id` INT NOT NULL AUTO_INCREMENT,
  `Marca_id` INT NOT NULL,
  `Graduacion_Izq` FLOAT NOT NULL COMMENT 'Graduación cristal izquierdo',
  `Graduacion_Dch` FLOAT NOT NULL COMMENT 'Graduación cristal derecho',
  `Color_Izq` VARCHAR(15) NULL DEFAULT NULL COMMENT 'Color cristal izquierdo\nPor defecto: NULL - Transparente',
  `Color_Dch` VARCHAR(15) NULL DEFAULT NULL COMMENT 'Color cristal derecho.\nPor defecto: NULL - Transparente',
  `Montura_Tipo` ENUM('Flotante', 'Pasta', 'Metálica') NOT NULL COMMENT 'Tipo de Montura de la gafa:\nFlotante\nPasta\nMetálica',
  `Montura_Color` VARCHAR(15) NOT NULL,
  `Precio` DECIMAL NOT NULL,
  PRIMARY KEY (`Gafas_id`, `Marca_id`),
  INDEX `fk_Gafas_Marca1_idx` (`Marca_id` ASC) VISIBLE,
  CONSTRAINT `fk_Gafas_Marca1`
    FOREIGN KEY (`Marca_id`)
    REFERENCES `Óptica`.`Marca` (`Marca_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Óptica`.`Empleados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Óptica`.`Empleados` ;

CREATE TABLE IF NOT EXISTS `Óptica`.`Empleados` (
  `Empleados_id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(15) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Empleados_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Óptica`.`Ventas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Óptica`.`Ventas` ;

CREATE TABLE IF NOT EXISTS `Óptica`.`Ventas` (
  `Ventas_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Ticket de venta',
  `Clientes_id` INT NOT NULL,
  `Gafas_id` INT NOT NULL,
  `Empleados_id` INT NOT NULL COMMENT 'Empleado que realizó la venta',
  `Fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Ventas_id`, `Clientes_id`, `Gafas_id`, `Empleados_id`),
  INDEX `fk_Clientes_has_Gafas_Gafas1_idx` (`Gafas_id` ASC) VISIBLE,
  INDEX `fk_Clientes_has_Gafas_Clientes_idx` (`Clientes_id` ASC) VISIBLE,
  INDEX `fk_Clientes_has_Gafas_Empleados1_idx` (`Empleados_id` ASC) VISIBLE,
  CONSTRAINT `fk_Clientes_has_Gafas_Clientes`
    FOREIGN KEY (`Clientes_id`)
    REFERENCES `Óptica`.`Clientes` (`Clientes_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Clientes_has_Gafas_Gafas1`
    FOREIGN KEY (`Gafas_id`)
    REFERENCES `Óptica`.`Gafas` (`Gafas_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Clientes_has_Gafas_Empleados1`
    FOREIGN KEY (`Empleados_id`)
    REFERENCES `Óptica`.`Empleados` (`Empleados_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
