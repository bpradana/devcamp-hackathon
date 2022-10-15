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

  async updateProduct(id, product) {
    return this.productModel.findByIdAndUpdate(id, product);
  }

  async deleteProduct(id) {
    return this.productModel.findByIdAndDelete(id);
  }
}

module.exports = ProductRepository;