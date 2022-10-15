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

  async updateProduct(req, res) {
    const product = await this.productService.updateProduct(req.params.id, req.body);
    res.send(product);
  }

  async deleteProduct(req, res) {
    const product = await this.productService.deleteProduct(req.params.id);
    res.send(product);
  }
}

module.exports = ProductController;