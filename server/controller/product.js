class ProductController {
  constructor(productService) {
    this.productService = productService;
  }

  async getProducts(req, res) {
    const products = await this.productService.getProducts();
    res.send(products);
  }

  async getProductById(req, res) {
    const product = await this.productService.getProductById(req.params.id);
    res.send(product);
  }

  async createProduct(req, res) {
    const product = await this.productService.createProduct(req.body);
    res.send(product);
  }
}

module.exports = ProductController;