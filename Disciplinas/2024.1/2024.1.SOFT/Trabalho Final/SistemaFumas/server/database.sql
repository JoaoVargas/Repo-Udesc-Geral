CREATE DATABASE IF NOT EXISTS fumasdb;



-- TABELA E TRIGGERS DE FORNECEDORES
-- Tabela Fornecedores
CREATE TABLE IF NOT EXISTS fornecedores (
  cnpj_fornecedor VARCHAR(14) NOT NULL,
  nome_fornecedor VARCHAR(255) NOT NULL,
  cep_fornecedor  VARCHAR(8) NOT NULL,
  numero_fornecedor VARCHAR(10) NOT NULL,
  CONSTRAINT cnpj_fornecedor_pkey 
    PRIMARY KEY (cnpj_fornecedor),
);

-- Funcao AUX pro trigger CNPJ único
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

-- Trigger CNPJ único insert
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

-- Trigger CNPJ único update
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

-- Trigger fornecedor vazio
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



-- TABELA E TRIGGERS DE PRODUTOS
-- Tabela produtos
CREATE TABLE public.produtos (
  id_produto serial NOT NULL,
  cnpj_fornecedor_produto character varying(14) NOT NULL,
  nome_produto character varying(100) NOT NULL,
  marca_produto character varying(100) NOT NULL,
  tipo_unidade_produto character varying(2) NOT NULL,
  quantidade_unidades_produto real NOT NULL,
  preco_produto real NOT NULL,
  CONSTRAINT id_produto_pkey 
    PRIMARY KEY (id_produto),
    UNIQUE (id_produto),
  CONSTRAINT cnpj_fornecedor_produto_fkey 
    FOREIGN KEY (cnpj_fornecedor_produto)
    REFERENCES public.fornecedores (cnpj_fornecedor) 
    MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID
);



-- TABELA E TRIGGERS DE PRECOS
-- Tabela precos
CREATE TABLE public.historico_preco (
  id_historico serial NOT NULL,
  id_produto_preco integer NOT NULL,
  preco_produto_preco real NOT NULL,
  data_preco timestamp without time zone NOT NULL DEFAULT NOW(),
  CONSTRAINT id_historico_pkey 
    PRIMARY KEY (id_historico),
    UNIQUE (id_historico),
  CONSTRAINT id_produto_preco_fkey FOREIGN KEY (id_produto_preco)
    REFERENCES public.produtos (id_produto) 
    MATCH SIMPLE 
    ON UPDATE CASCADE 
    ON DELETE CASCADE 
    NOT VALID
);

-- Trigger adicionar historico
CREATE OR REPLACE FUNCTION addHistoricoPreco()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO historico_preco (id_produto_preco, preco_produto_preco, data_preco)
    VALUES (NEW.id_produto, NEW.preco_produto, NOW());
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER addHistoricoPrecoUpdate
AFTER UPDATE OF preco_produto ON produtos
FOR EACH ROW
WHEN (OLD.preco_produto IS DISTINCT FROM NEW.preco_produto)
EXECUTE FUNCTION addHistoricoPreco();
CREATE TRIGGER addHistoricoPrecoInsert
AFTER INSERT ON produtos
FOR EACH ROW
EXECUTE FUNCTION addHistoricoPreco();