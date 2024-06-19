import dotenv from "dotenv";
import pg from "pg";
const { Client } = pg;

dotenv.config();

export const client = new Client({
  user: process.env.USER,
  password: process.env.PASSWORD,
  host: process.env.HOST,
  port: 5432,
  database: process.env.DATABASE,
})
