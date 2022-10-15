class ProductService {
  constructor(
    productRepository
  ) {
    this.productRepository = productRepository;
  }

  async getProducts() {
    return this.productRepository.getProducts();
  }

  async getProductById(id) {
    return this.productRepository.getProductById(id);
  }

  async createProduct(product) {
    return this.productRepository.createProduct(product);
  }
}

module.exports = ProductService;