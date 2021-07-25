-- MySQL Script generated by MySQL Workbench
-- Sun Jul 18 17:58:52 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema YouTube
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `YouTube` ;

-- -----------------------------------------------------
-- Schema YouTube
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `YouTube` DEFAULT CHARACTER SET utf8 ;
USE `YouTube` ;

-- -----------------------------------------------------
-- Table `YouTube`.`Usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `YouTube`.`Usuarios` ;

CREATE TABLE IF NOT EXISTS `YouTube`.`Usuarios` (
  `Id_Usuario` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `Nombre` VARCHAR(15) NOT NULL,
  `FechaNac` DATE NOT NULL,
  `Pais` VARCHAR(15) NOT NULL,
  `CP` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`Id_Usuario`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `YouTube`.`Videos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `YouTube`.`Videos` ;

CREATE TABLE IF NOT EXISTS `YouTube`.`Videos` (
  `Id_Video` INT NOT NULL AUTO_INCREMENT,
  `Titulo` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(45) NULL,
  `Tamaño` TINYINT NOT NULL COMMENT 'Tamaño del archivo de vídeo en MB',
  `Nombre_Archivo` VARCHAR(45) NOT NULL,
  `Duración` TIME NOT NULL COMMENT 'Duración del vídeo en hh:mm:ss',
  `Thumbnail` VARCHAR(45) NOT NULL,
  `Estado` ENUM('publico', 'privado', 'oculto') NOT NULL COMMENT 'Visibilidad del vídeo',
  `Id_Usuario` INT NOT NULL COMMENT 'Usuario que publica el video',
  `Fecha_Public` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Reproducciones` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`Id_Video`),
  INDEX `fk_Videos_Usuarios1_idx` (`Id_Usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Videos_Usuarios1`
    FOREIGN KEY (`Id_Usuario`)
    REFERENCES `YouTube`.`Usuarios` (`Id_Usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `YouTube`.`Canales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `YouTube`.`Canales` ;

CREATE TABLE IF NOT EXISTS `YouTube`.`Canales` (
  `Id_Canal` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(15) NOT NULL,
  `Descripcion` VARCHAR(45) NULL,
  `TIMESTAMP` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora de creación del canal',
  `Id_Propietario` INT NOT NULL COMMENT 'Usuario que crea el canal',
  PRIMARY KEY (`Id_Canal`),
  INDEX `fk_Canales_Usuarios1_idx` (`Id_Propietario` ASC) VISIBLE,
  CONSTRAINT `fk_Canales_Usuarios1`
    FOREIGN KEY (`Id_Propietario`)
    REFERENCES `YouTube`.`Usuarios` (`Id_Usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `YouTube`.`Etiquetas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `YouTube`.`Etiquetas` ;

CREATE TABLE IF NOT EXISTS `YouTube`.`Etiquetas` (
  `Id_Etiqueta` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Id_Etiqueta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `YouTube`.`Comentarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `YouTube`.`Comentarios` ;

CREATE TABLE IF NOT EXISTS `YouTube`.`Comentarios` (
  `Id_Comentario` INT NOT NULL AUTO_INCREMENT,
  `Id_Usuario` INT NOT NULL,
  `Id_Video` INT NOT NULL,
  `Fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Texto` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id_Comentario`),
  INDEX `fk_Usuarios_has_Videos_Videos2_idx` (`Id_Video` ASC) VISIBLE,
  INDEX `fk_Usuarios_has_Videos_Usuarios2_idx` (`Id_Usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuarios_has_Videos_Usuarios2`
    FOREIGN KEY (`Id_Usuario`)
    REFERENCES `YouTube`.`Usuarios` (`Id_Usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Usuarios_has_Videos_Videos2`
    FOREIGN KEY (`Id_Video`)
    REFERENCES `YouTube`.`Videos` (`Id_Video`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `YouTube`.`Like_Dislikes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `YouTube`.`Like_Dislikes` ;

CREATE TABLE IF NOT EXISTS `YouTube`.`Like_Dislikes` (
  `Id_LikeDislike` INT NOT NULL AUTO_INCREMENT,
  `Usuarios_Id_Usuario` INT NOT NULL,
  `Videos_Id_Video` INT NOT NULL,
  `Like_Dislike` ENUM('like', 'dislike') NOT NULL,
  `Fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Comentarios_Id_Comentario` INT NULL,
  PRIMARY KEY (`Id_LikeDislike`),
  INDEX `fk_Usuarios_has_Videos_Videos1_idx` (`Videos_Id_Video` ASC) VISIBLE,
  INDEX `fk_Usuarios_has_Videos_Usuarios1_idx` (`Usuarios_Id_Usuario` ASC) VISIBLE,
  UNIQUE INDEX `Like_Dislike_UNIQUE` (`Like_Dislike` ASC) VISIBLE,
  INDEX `fk_Like_Dislikes_Comentarios1_idx` (`Comentarios_Id_Comentario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuarios_has_Videos_Usuarios1`
    FOREIGN KEY (`Usuarios_Id_Usuario`)
    REFERENCES `YouTube`.`Usuarios` (`Id_Usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Usuarios_has_Videos_Videos1`
    FOREIGN KEY (`Videos_Id_Video`)
    REFERENCES `YouTube`.`Videos` (`Id_Video`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Like_Dislikes_Comentarios1`
    FOREIGN KEY (`Comentarios_Id_Comentario`)
    REFERENCES `YouTube`.`Comentarios` (`Id_Comentario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `YouTube`.`Videos_has_Etiquetas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `YouTube`.`Videos_has_Etiquetas` ;

CREATE TABLE IF NOT EXISTS `YouTube`.`Videos_has_Etiquetas` (
  `Id_Video` INT NOT NULL,
  `Id_Etiqueta` INT NOT NULL,
  PRIMARY KEY (`Id_Video`, `Id_Etiqueta`),
  INDEX `fk_Videos_has_Etiquetas_Etiquetas1_idx` (`Id_Etiqueta` ASC) VISIBLE,
  INDEX `fk_Videos_has_Etiquetas_Videos1_idx` (`Id_Video` ASC) VISIBLE,
  CONSTRAINT `fk_Videos_has_Etiquetas_Videos1`
    FOREIGN KEY (`Id_Video`)
    REFERENCES `YouTube`.`Videos` (`Id_Video`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Videos_has_Etiquetas_Etiquetas1`
    FOREIGN KEY (`Id_Etiqueta`)
    REFERENCES `YouTube`.`Etiquetas` (`Id_Etiqueta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `YouTube`.`Subscriptores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `YouTube`.`Subscriptores` ;

CREATE TABLE IF NOT EXISTS `YouTube`.`Subscriptores` (
  `Id_subscriptores` INT NOT NULL AUTO_INCREMENT,
  `Id_Canal` INT NOT NULL,
  `Id_Usuario` INT NOT NULL,
  PRIMARY KEY (`Id_subscriptores`),
  INDEX `fk_Canales_has_Usuarios_Usuarios1_idx` (`Id_Usuario` ASC) VISIBLE,
  INDEX `fk_Canales_has_Usuarios_Canales1_idx` (`Id_Canal` ASC) VISIBLE,
  CONSTRAINT `fk_Canales_has_Usuarios_Canales1`
    FOREIGN KEY (`Id_Canal`)
    REFERENCES `YouTube`.`Canales` (`Id_Canal`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Canales_has_Usuarios_Usuarios1`
    FOREIGN KEY (`Id_Usuario`)
    REFERENCES `YouTube`.`Usuarios` (`Id_Usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `YouTube`.`Playlists`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `YouTube`.`Playlists` ;

CREATE TABLE IF NOT EXISTS `YouTube`.`Playlists` (
  `Id_Playlist` INT NOT NULL AUTO_INCREMENT,
  `Id_Usuario` INT NOT NULL,
  `Id_Video` INT NOT NULL,
  `Nombre` VARCHAR(15) NOT NULL,
  `Fecha_Creacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Estado` ENUM('publico', 'privada') NOT NULL,
  PRIMARY KEY (`Id_Playlist`),
  INDEX `fk_Usuarios_has_Videos_Videos3_idx` (`Id_Video` ASC) VISIBLE,
  INDEX `fk_Usuarios_has_Videos_Usuarios3_idx` (`Id_Usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuarios_has_Videos_Usuarios3`
    FOREIGN KEY (`Id_Usuario`)
    REFERENCES `YouTube`.`Usuarios` (`Id_Usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Usuarios_has_Videos_Videos3`
    FOREIGN KEY (`Id_Video`)
    REFERENCES `YouTube`.`Videos` (`Id_Video`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;