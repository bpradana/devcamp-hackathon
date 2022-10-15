class ProductRepository {
  constructor(
    productModel
  ) {
    this.productModel = productModel;
  }

  async getProducts() {
    return this.productModel.find();
  }
  
  async getProductById(id) {
    return this.productModel.findById(id);
  }

  async createProduct(product) {
    const newProduct = new this.productModel(product);
    return newProduct.save();
  }
}

module.exports = ProductRepository;