SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS `db_controle_servico`.`cliente` (
  `cliente_id` INT(11) NOT NULL,
  `text_nome` VARCHAR(200) NULL DEFAULT NULL,
  `text_cpf_cnpj` VARCHAR(20) NULL DEFAULT NULL,
  `text_rg` VARCHAR(45) NULL DEFAULT NULL,
  `text_endereco` VARCHAR(100) NULL DEFAULT NULL,
  `text_cep` VARCHAR(45) NULL DEFAULT NULL,
  `intg_estado` INT(11) NULL DEFAULT NULL,
  `text_bairro` VARCHAR(45) NULL DEFAULT NULL,
  `text_email` VARCHAR(200) NULL DEFAULT NULL,
  `intg_tipodepessoa` INT(11) NOT NULL,
  `dataDeCadastro` DATETIME NOT NULL,
  `dataDeAlteracao` DATETIME NOT NULL,
  `dataDeNascimento` DATE NULL DEFAULT NULL,
  `observacoes` VARCHAR(200) NULL DEFAULT NULL,
  `text_telefone` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`cliente_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `db_controle_servico`.`funcionario` (
  `funcionario_id` INT(11) NOT NULL,
  `text_nome` VARCHAR(200) NULL DEFAULT NULL,
  `text_cpf_cnpj` VARCHAR(20) NULL DEFAULT NULL,
  `text_rg` VARCHAR(45) NULL DEFAULT NULL,
  `text_endereco` VARCHAR(100) NULL DEFAULT NULL,
  `text_cep` VARCHAR(45) NULL DEFAULT NULL,
  `intg_estado` INT(11) NULL DEFAULT NULL,
  `text_bairro` VARCHAR(45) NULL DEFAULT NULL,
  `text_email` VARCHAR(200) NULL DEFAULT NULL,
  `dataDeCadastro` DATETIME NOT NULL,
  `dataDeAlteracao` DATETIME NOT NULL,
  `dataDeNascimento` DATE NULL DEFAULT NULL,
  `text_observacoes` VARCHAR(300) NULL DEFAULT NULL,
  `text_telefone` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`funcionario_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `db_controle_servico`.`servico` (
  `servico_id` INT(11) NOT NULL,
  `doub_preco` DOUBLE NULL DEFAULT NULL,
  `text_nome` VARCHAR(200) NULL DEFAULT NULL,
  `text_descricao` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`servico_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `db_controle_servico`.`ordemDeServico` (
  `ordemdeservico_id` INT(11) NOT NULL,
  `funcionario_id` INT(11) NOT NULL,
  `servico_id` INT(11) NOT NULL,
  `cliente_id` INT(11) NOT NULL,
  `dataDeCadastro` DATETIME NOT NULL,
  `dataDeAlteracao` DATETIME NOT NULL,
  `dataDeAgendamento` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`ordemdeservico_id`),
  INDEX `fk_ordemDeServico_funcionario1_idx` (`funcionario_id` ASC),
  INDEX `fk_ordemDeServico_servico1_idx` (`servico_id` ASC),
  INDEX `fk_ordemDeServico_cliente1_idx` (`cliente_id` ASC),
  CONSTRAINT `fk_ordemDeServico_funcionario1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `db_controle_servico`.`funcionario` (`funcionario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordemDeServico_servico1`
    FOREIGN KEY (`servico_id`)
    REFERENCES `db_controle_servico`.`servico` (`servico_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordemDeServico_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `db_controle_servico`.`cliente` (`cliente_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `db_controle_servico`.`historicoOS` (
  `historicoOS_id` INT(11) NOT NULL,
  `dataDeCadastro` DATETIME NOT NULL,
  `ordemDeServico_ordemdeservico_id` INT(11) NOT NULL,
  PRIMARY KEY (`historicoOS_id`),
  INDEX `fk_historicoOS_ordemDeServico1_idx` (`ordemDeServico_ordemdeservico_id` ASC),
  CONSTRAINT `fk_historicoOS_ordemDeServico1`
    FOREIGN KEY (`ordemDeServico_ordemdeservico_id`)
    REFERENCES `db_controle_servico`.`ordemDeServico` (`ordemdeservico_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `db_controle_servico`.`funcionario_servico` (
  `funcionario_id` INT(11) NOT NULL,
  `servico_id` INT(11) NOT NULL,
  INDEX `fk_funcionario_servico_funcionario1_idx` (`funcionario_id` ASC),
  INDEX `fk_funcionario_servico_servico1_idx` (`servico_id` ASC),
  CONSTRAINT `fk_funcionario_servico_funcionario1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `db_controle_servico`.`funcionario` (`funcionario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_servico_servico1`
    FOREIGN KEY (`servico_id`)
    REFERENCES `db_controle_servico`.`servico` (`servico_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `db_controle_servico`.`usuario` (
  `usuario_id` INT(11) NOT NULL,
  `text_email` VARCHAR(200) NOT NULL,
  `text_senha` VARCHAR(12) NOT NULL,
  `bool_ativo` TINYINT(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`usuario_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

--SET FOREIGN_KEY_CHECKS = 0;
--SET FOREIGN_KEY_CHECKS = 1;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;