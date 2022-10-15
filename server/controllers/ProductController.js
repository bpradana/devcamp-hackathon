const getAllProducts = (req, res) => {
  res.send("get all");
};

const getProductById = (req, res) => {
  res.send("get prod by id");
};

const createProduct = (req, res) => {
  res.send("create prod");
};

const updateProductById = (req, res) => {
  res.send("update prod");
};

const deleteProductById = (req, res) => {
  res.send("delete prod");
};

module.exports = {
  getAllProducts,
  getProductById,
  createProduct,
  updateProductById,
  deleteProductById,
};
