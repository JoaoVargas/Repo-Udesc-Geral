const dotenv = require("dotenv").config();
const { Pool } = require('pg');

const pool = new Pool({
  user: process.env.PGUSER,
  host: process.env.PGHOST,
  database: process.env.NODE_ENV === 'test' ? process.env.PGDATABASE_TEST : process.env.PGDATABASE,
  password: process.env.PGPASSWORD,
  port: process.env.PGPORT,

});

module.exports = pool;
