const express = require("express");
const router = express.Router();

const {
  getAllProducts,
  getProductById,
  createProduct,
  updateProductById,
  deleteProductById,
} = require("../controllers/ProductController");

router.get("/products", getAllProducts);
router
  .route("/product/:id")
  .get(getProductById)
  .patch(updateProductById)
  .delete(deleteProductById);
router.post("/product", createProduct);

module.exports = router;
