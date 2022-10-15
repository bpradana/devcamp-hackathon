const express = require("express");
const app = express();

const productRoutes = require("./routes/ProductRoute");

app.get("/", (req, res) => {
  res.send("Hello World!");
});

app.use("/api", productRoutes);

app.listen(3000, () => {
  console.log("Listening on port 3000");
});
