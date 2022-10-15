class ProductRepository {
  constructor(productModel) {
    this.productModel = productModel;
  }

  async getProducts() {
    try {
      return await this.productModel.find();
    } catch (err) {
      return {
        message: err.message || "Internal Server Error. Please try again.",
      };
    }
  }

  async getProductById(id) {
    try {
      return await this.productModel.findById(id);
    } catch (err) {
      return {
        message: err.message || "Internal Server Error. Please try again.",
      };
    }
  }

  async createProduct(product) {
    try {
      const newProduct = new this.productModel(product);
      return newProduct.save();
    } catch (err) {
      return {
        message: err.message || "Internal Server Error. Please try again.",
      };
    }
  }

  async updateProduct(id, product) {
    try {
      return await this.productModel.findByIdAndUpdate(id, product, {
        returnDocument: "after",
      });
    } catch (err) {
      return {
        message: err.message || "Internal Server Error. Please try again.",
      };
    }
  }

  async deleteProduct(id) {
    try {
      return await this.productModel.findByIdAndDelete(id);
    } catch (err) {
      return {
        message: err.message || "Internal Server Error. Please try again.",
      };
    }
  }
}

module.exports = ProductRepository;
