CREATE DATABASE fumasdb;

CREATE TABLE IF NOT EXISTS fornecedores (
  id_fornecedor SERIAL PRIMARY KEY,
  cnpj_fornecedor VARCHAR(14),
  nome_fornecedor VARCHAR(255),
  cep_fornecedor  VARCHAR(8),
  numero_fornecedor VARCHAR(10)
);

