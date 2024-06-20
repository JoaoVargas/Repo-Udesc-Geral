const express = require("express");
const app = express();
const dotenv = require("dotenv");
dotenv.config();

const port = process.env.SERVERPORT;

const { dbConnect, getSla } = require("./db");
const client = dbConnect();
getSla(client);

app.get("/api", (req, res) => {
  res.send({ message: "Hello from Express!" });
});

app.get("/api/sla", async (req, res) => {
  res.send({ message: getSla() });
});

app.listen(port, () => console.log(`Listening on port ${port}`));



// const client = pool.connect()
