const dotenv = require("dotenv").config();
const cors = require("cors");
const express = require("express");
const app = express();
const pool = require("./db");

app.use(cors());
app.use(express.json());

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

module.exports = app; // Exportando apenas a aplicação
