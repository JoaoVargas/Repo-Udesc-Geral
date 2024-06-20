const dotenv = require("dotenv");
dotenv.config();
const { Pool } = require('pg');


async function dbConnect() {
  // if (global.connection) {
  //   console.log("Ja conectado ao DB")
  //   return global.connection.connect();
  // }

  const pool = new Pool({
    application_name: process.env.PGAPPLICATION
  });

  //apenas testando a conexão
  const client = await pool.connect();
  console.log("Pool de conexões conectada");

  const res = await client.query('SELECT NOW()');
  console.log("Teste de horario local DB: " + res.rows[0]["now"]);

  const a = await client.query("SELECT * FROM public.teste");
  console.log("aaaaaaaaaaa" + a);

  client.release();

  //guardando para usar sempre o mesmo
  // global.connection = pool;

  return await pool.connect();
}

async function getSla(client) {
  try {
    const res = await client.query("SELECT * FROM public.teste");
    console.log("aaaaaaaaaaa" + res);
    return(res.rows);
  } catch (error) {
    console.log(error);
    return(error);
  }
}

module.exports = { dbConnect, getSla }