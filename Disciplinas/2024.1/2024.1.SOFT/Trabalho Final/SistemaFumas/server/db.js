const dotenv = require("dotenv").config();
const Pool = require("pg").Pool;

const pool = new Pool({
  application_name: process.env.PGAPPLICATION
});

module.exports = pool;