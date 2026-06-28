CREATE TABLE IF NOT EXISTS users (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100)  NOT NULL,
    email        VARCHAR(150)  NOT NULL UNIQUE,
    senha        VARCHAR(255)  NOT NULL,
    telefone     VARCHAR(11),
    cpf          VARCHAR(11)   UNIQUE,
    cnpj         VARCHAR(14)   UNIQUE,
    tipo_conta   VARCHAR(20)   NOT NULL,
    role         VARCHAR(20)   NOT NULL DEFAULT 'COMPRADOR',
    data_cadastro DATETIME     NOT NULL
    );