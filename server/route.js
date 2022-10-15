const express = require('express');
const router = express.Router();
const Product = require('./models/product');
const ProductController = require('./controller/product');
const ProductService = require('./service/product');
const ProductRepository = require('./repository/product');

const productRepository = new ProductRepository(Product);
const productService = new ProductService(productRepository);
const productController = new ProductController(productService);

router.get('/', productController.getProducts.bind(productController));
router.get('/:id', productController.getProductById.bind(productController));
router.post('/', productController.createProduct.bind(productController));
router.put('/:id', productController.updateProduct.bind(productController));
router.delete('/:id', productController.deleteProduct.bind(productController));

module.exports = router;