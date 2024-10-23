# Controle de Estoque

Este é um sistema simples de controle de estoque desenvolvido em Java usando JavaFX e PostgreSQL. O objetivo é gerenciar produtos, incluindo adição, listagem e controle de entrada e saída.

## Requisitos
- Java JDK 11 ou superior
- PostgreSQL 12 ou superior
- Maven (opcional, se você estiver usando)

## Configuração do Banco de Dados
### 1. Criação do Banco de Dados
Crie um banco de dados no PostgreSQL chamado `EstoqueBD`. Você pode fazer isso através de uma ferramenta de gerenciamento de banco de dados ou pelo terminal:

## Códigos de Criação/Configuração do banco de dados:
-- Criação do banco de dados
CREATE DATABASE EstoqueBD;

-- Conecte-se ao banco de dados
\c EstoqueBD;

-- Criação da tabela de produtos:

CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL
);

-- Criação da tabela de fornecedores:

CREATE TABLE fornecedores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    contato VARCHAR(255)
);

-- Criação da tabela de categorias:

CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Alterar a tabela de produtos para adicionar chave estrangeira para categorias e fornecedores:

ALTER TABLE produtos
ADD COLUMN categoria_id INT REFERENCES categorias(id),
ADD COLUMN fornecedor_id INT REFERENCES fornecedores(id);



