/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lucas
 * Created: 17/10/2019
 */
CREATE DATABASE vendas;

CREATE TABLE IF NOT EXISTS `Estado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sigla` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Municipio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Municipio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NOT NULL,
  `Estado_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Estado_id`),
  INDEX `fk_Municipio_Estado_idx` (`Estado_id` ASC),
  CONSTRAINT `fk_Municipio_Estado`
    FOREIGN KEY (`Estado_id`)
    REFERENCES `Estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Proprietario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Proprietario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `rg` VARCHAR(45) NOT NULL,
  `nascimento` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Marca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Marca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Veiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Veiculo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `placa` VARCHAR(45) NOT NULL,
  `ano` DATE NOT NULL,
  `Categoria_id` INT NOT NULL,
  `Proprietario_id` INT NOT NULL,
  `Marca_id` INT NOT NULL,
  `Municipio_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Categoria_id`, `Proprietario_id`, `Marca_id`, `Municipio_id`),
  INDEX `fk_Veiculo_Categoria1_idx` (`Categoria_id` ASC),
  INDEX `fk_Veiculo_Proprietario1_idx` (`Proprietario_id` ASC),
  INDEX `fk_Veiculo_Marca1_idx` (`Marca_id` ASC),
  INDEX `fk_Veiculo_Municipio1_idx` (`Municipio_id` ASC),
  CONSTRAINT `fk_Veiculo_Categoria1`
    FOREIGN KEY (`Categoria_id`)
    REFERENCES `Categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Veiculo_Proprietario1`
    FOREIGN KEY (`Proprietario_id`)
    REFERENCES `Proprietario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Veiculo_Marca1`
    FOREIGN KEY (`Marca_id`)
    REFERENCES `Marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Veiculo_Municipio1`
    FOREIGN KEY (`Municipio_id`)
    REFERENCES `Municipio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;