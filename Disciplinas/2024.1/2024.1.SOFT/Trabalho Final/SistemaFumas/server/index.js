const dotenv = require("dotenv").config();
const cors = require("cors");
const express = require("express");
const app = express();
const pool = require("./db")

app.use(cors())
app.use(express.json())

// Create
app.post("/fornecedores", async (req, res) => {
  try {
    const { cnpj, nome, cep, numero } = req.body;
    const newFornecedor = await pool.query(
      "INSERT INTO fornecedores (cnpj_fornecedor, nome_fornecedor, cep_fornecedor, numero_fornecedor) VALUES ($1, $2, $3, $4) RETURNING *",
      [ cnpj, nome, cep, numero ]
    );

    res.json(newFornecedor.rows[0]);
  } catch (error) {
    console.error(error.message);
  }
})

// Get all
app.get("/fornecedores", async (req, res) => {
  try {
    const allFornecedores = await pool.query(
      "SELECT * FROM fornecedores"
    );

    res.json(allFornecedores.rows);
  } catch (error) {
    console.error(error.message);
  }
})

// Get a
app.get("/fornecedores/:id_fornecedor", async (req, res) => {
  try {
    const { id_fornecedor } = req.params;
    const aFornecedor = await pool.query(
      "SELECT * FROM fornecedores WHERE id_fornecedor = $1", 
      [id_fornecedor]
    )

    res.json(aFornecedor.rows[0]);
  } catch (error) {
    console.error(error.message);
  }
})

// Update a
app.put("/fornecedores/:id_fornecedor", async (req, res) => {
  try {
    const { id_fornecedor } = req.params;
    const { cnpj, nome, cep, numero } = req.body;

    const updateFornecedor = await pool.query(
      "UPDATE fornecedores SET cnpj_fornecedor = $1, nome_fornecedor = $2, cep_fornecedor = $3, numero_fornecedor = $4 WHERE id_fornecedor = $5 RETURNING *",
      [ cnpj, nome, cep, numero, id_fornecedor ]
    )

    res.json(fornecedor.rows[0]);
  } catch (error) {
    console.error(error.message);
  }
})

// Delete a
app.delete("/fornecedores/:id_fornecedor", async (req, res) => {
  try {
    const { id_fornecedor } = req.params;
    const deleteFornecedor = await pool.query(
      "DELETE * FROM fornecedores WHERE id_fornecedor = $1", 
      [id_fornecedor]
    )

    res.json(deleteFornecedor.rows);
  } catch (error) {
    console.error(error.message);
  }
})


app.listen( process.env.SERVERPORT, () => {
    console.log(`Server Initialized on port: ${process.env.SERVERPORT}`);
  }
)
