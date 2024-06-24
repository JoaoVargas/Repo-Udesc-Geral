const app = require("./index");

const PORT = process.env.SERVERPORT;

app.listen(PORT, () => {
  console.log(`Server initialized on port: ${PORT}`);
});
