CREATE DATABASE IF NOT EXISTS fumasdb;

CREATE TABLE IF NOT EXISTS fornecedores (
  cnpj_fornecedor VARCHAR(14) NOT NULL,
  nome_fornecedor VARCHAR(255) NOT NULL,
  cep_fornecedor  VARCHAR(8) NOT NULL,
  numero_fornecedor VARCHAR(10) NOT NULL,
  CONSTRAINT cnpj_fornecedor_pkey PRIMARY KEY (cnpj_fornecedor),
);

CREATE OR REPLACE FUNCTION verificaCnpj(new_cnpj VARCHAR(14),
old_cnpj VARCHAR(14)) 
RETURNS void AS 
$$
BEGIN 
  IF new_cnpj = old_cnpj THEN
	new_cnpj = old_cnpj;
  ELSIF EXISTS(
    SELECT 1 FROM fornecedores
    WHERE
      cnpj_fornecedor = new_cnpj
  ) THEN RAISE EXCEPTION 'CNPJ já cadastrado.';
  END IF;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION verificaCnpjInsert() 
RETURNS trigger AS 
$$
BEGIN 
  PERFORM verificaCnpj(NEW.cnpj_fornecedor, '0');
  RETURN NEW;
END;
$$
LANGUAGE plpgsql;
CREATE OR REPLACE TRIGGER verificaCnpjInsert 
BEFORE INSERT ON fornecedores 
FOR ROW EXECUTE PROCEDURE verificaCnpjInsert();

CREATE OR REPLACE FUNCTION verificaCnpjUpdate() 
RETURNS trigger AS 
$$
BEGIN 
  PERFORM verificaCnpj(NEW.cnpj_fornecedor, OLD.cnpj_fornecedor);
  RETURN NEW;
END;
$$
LANGUAGE plpgsql;
CREATE OR REPLACE TRIGGER verificaCnpjUpdate 
BEFORE UPDATE ON fornecedores 
FOR ROW EXECUTE PROCEDURE verificaCnpjUpdate();

CREATE OR REPLACE FUNCTION verificaFornecedorVazio() 
RETURNS trigger AS 
$$
BEGIN 
  IF NEW.cnpj_fornecedor = '' OR NEW.nome_fornecedor = '' OR NEW.cep_fornecedor = '' OR NEW.numero_fornecedor = ''
  THEN RAISE EXCEPTION 'Valores não podem ser vazios';
  END IF;
  RETURN new;
END;
$$
LANGUAGE plpgsql;
CREATE OR REPLACE TRIGGER verificaFornecedorVazio 
BEFORE INSERT OR UPDATE ON fornecedores 
FOR ROW EXECUTE PROCEDURE verificaFornecedorVazio();