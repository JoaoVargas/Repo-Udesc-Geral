const dotenv = require("dotenv").config();
const cors = require("cors");
const express = require("express");
const app = express();
const pool = require("./db");

app.use(cors());
app.use(express.json());

// FORNECEDORES
// Create
app.post("/fornecedores", async (req, res) => {
  try {
    const { cnpj, nome, cep, numero } = req.body;
    const newFornecedor = await pool.query(
      "INSERT INTO fornecedores (cnpj_fornecedor, nome_fornecedor, cep_fornecedor, numero_fornecedor) VALUES ($1, $2, $3, $4) RETURNING *",
      [cnpj, nome, cep, numero]
    );

    res.json(newFornecedor.rows[0]);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// Get all
app.get("/fornecedores", async (req, res) => {
  try {
    const allFornecedores = await pool.query("SELECT * FROM fornecedores");
    res.json(allFornecedores.rows);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// Get a
app.get("/fornecedores/:cnpj_fornecedor", async (req, res) => {
  try {
    const { cnpj_fornecedor } = req.params;

    if (cnpj_fornecedor == "undefined") {
      return
    }

    const aFornecedor = await pool.query(
      "SELECT * FROM fornecedores WHERE cnpj_fornecedor = $1",
      [cnpj_fornecedor]
    );

    res.json(aFornecedor.rows[0]);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// Update a
app.put("/fornecedores/:cnpj_fornecedor", async (req, res) => {
  try {
    const { cnpj_fornecedor } = req.params;
    const { cnpj, nome, cep, numero } = req.body;

    const updateFornecedor = await pool.query(
      `UPDATE fornecedores 
       SET cnpj_fornecedor = $1, 
           nome_fornecedor = $2, 
           cep_fornecedor = $3,
           numero_fornecedor = $4
       WHERE cnpj_fornecedor = $5 RETURNING *`,
      [cnpj, nome, cep, numero, cnpj_fornecedor]
    );

    res.json(updateFornecedor.rows[0]);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// Delete a
app.delete("/fornecedores/:cnpj_fornecedor", async (req, res) => {
  try {
    const { cnpj_fornecedor } = req.params;
    const deleteFornecedor = await pool.query(
      "DELETE FROM fornecedores WHERE cnpj_fornecedor = $1 RETURNING *",
      [cnpj_fornecedor]
    );

    res.json(deleteFornecedor.rows);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// PRODUTOS
// Create
app.post("/produtos", async (req, res) => {
  try {
    const { cnpjFornecedor: cnpj_fornecedor_produto, 
            nome: nome_produto, 
            marca: marca_produto, 
            tipoUnidade: tipo_unidade_produto,
            quantidade: quantidade_unidades_produto,
            preco: preco_produto } = req.body;

    const newProduto = await pool.query(
      `INSERT INTO produtos (
        cnpj_fornecedor_produto, 
        nome_produto, 
        marca_produto, 
        tipo_unidade_produto,
        quantidade_unidades_produto,
        preco_produto
        ) VALUES ($1, $2, $3, $4, $5, $6) RETURNING *`,
      [ cnpj_fornecedor_produto, 
        nome_produto, 
        marca_produto, 
        tipo_unidade_produto,
        quantidade_unidades_produto,
        preco_produto ]
    );

    res.json(newProduto.rows[0]);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// Get all
app.get("/produtos", async (req, res) => {
  try {
    const allProdutos = await pool.query("SELECT * FROM produtos");

    res.json(allProdutos.rows);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// Get a
app.get("/produtos/:id_produto", async (req, res) => {
  try {
    const { id_produto } = req.params;

    if (id_produto == "undefined") {
      return
    }

    const aProduto = await pool.query(
      "SELECT * FROM produtos WHERE id_produto = $1",
      [id_produto]
    );

    res.json(aProduto.rows[0]);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// Update a
app.put("/produtos/:id_produto", async (req, res) => {
  try {
    const { id_produto } = req.params;
    const { cnpj_fornecedor_produto, 
            nome_produto, 
            marca_produto, 
            tipo_unidade_produto,
            quantidade_unidades_produto,
            preco_produto } = req.body;

    const updateProduto = await pool.query(
      `UPDATE produtos 
       SET cnpj_fornecedor_produto = $1, 
           nome_produto = $2, 
           marca_produto = $3,
           tipo_unidade_produto = $4,
           quantidade_unidades_produto = $5,
           preco_produto = $6
       WHERE id_produto = $7 RETURNING *`,
      [ cnpj_fornecedor_produto, 
        nome_produto, 
        marca_produto, 
        tipo_unidade_produto,
        quantidade_unidades_produto,
        preco_produto,
        id_produto ]
    );

    res.json(updateProduto.rows[0]);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// Delete a
app.delete("/produtos/:id_produto", async (req, res) => {
  try {
    const { id_produto } = req.params;
    const deleteProduto = await pool.query(
      "DELETE FROM produtos WHERE id_produto = $1 RETURNING *",
      [id_produto]
    );

    res.json(deleteProduto.rows);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// HISTORICO
// Get a
app.get("/historicos/:id_produto", async (req, res) => {
  try { 
    const { id_produto } = req.params;

    if (id_produto == "undefined") {
      return
    }

    const aHistorico = await pool.query(
      "SELECT * FROM historico_preco WHERE id_produto_preco = $1 ORDER BY data_preco ASC",
      [id_produto]
    );

    res.json(aHistorico.rows);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// ESTOQUE
// Get a
app.get("/estoque/:id_produto", async (req, res) => {
  try {
    const { id_produto } = req.params;

    if (id_produto == "undefined") {
      return
    }

    const aEstoque = await pool.query(
      `SELECT * FROM estoque_produtos 
      WHERE id_produto_estoque = $1`,
      [id_produto]
    );

    res.json(aEstoque.rows[0]);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// Get all
app.get("/estoque/", async (req, res) => {
  try {
    const allEstoque = await pool.query(
      `SELECT * FROM estoque_produtos`
    );

    res.json(allEstoque.rows);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});

// Get history
app.get("/estoques/:id_produto", async (req, res) => {
  try {
    const { id_produto } = req.params;

    if (id_produto == "undefined") {
      return
    }

    const allEstoque = await pool.query(
      `SELECT * FROM historico_estoque 
      WHERE id_produto = $1`,
      [id_produto]
    );

    res.json(allEstoque.rows);
  } catch (error) {
    console.error(error.message);
    res.status(500).json({ message: error.message });
  }
});


module.exports = app;