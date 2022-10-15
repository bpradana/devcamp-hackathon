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

  async updateProduct(id, product) {
    return this.productRepository.updateProduct(id, product);
  }

  async deleteProduct(id) {
    return this.productRepository.deleteProduct(id);
  }

  async checkCompletion(id) {
    const product = await this.productRepository.getProductById(id);

    const emptyFields = [];
    if (!product.title) emptyFields.push('title');
    if (!product.image.data) emptyFields.push('image');
    if (!product.price) emptyFields.push('price');
    if (!product.description) emptyFields.push('description');
    if (!product.specification.size.width) emptyFields.push('size width');
    if (!product.specification.size.height) emptyFields.push('size height');
    if (!product.specification.type) emptyFields.push('type');
    if (!product.specification.material) emptyFields.push('material');
    if (!product.tags) emptyFields.push('tags');

    const score = (1 - emptyFields.length / 9) * 100;
    return { score, emptyFields };
  }

}

module.exports = ProductService;