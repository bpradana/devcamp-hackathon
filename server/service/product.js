class ProductService {
  constructor(
    productRepository
  ) {
    this.productRepository = productRepository;
  }

  async getProducts() {
    const products = await this.productRepository.getProducts();
    const productsWithCompletion = await Promise.all(products.map(async (product) => {
      const completion = await this.checkCompletion(product._id);
      return { ...product.toObject(), completion };
    }));
    return productsWithCompletion;
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
    if (!product.image) emptyFields.push('image');
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