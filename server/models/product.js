const mongoose = require('mongoose');

const productSchema = new mongoose.Schema({
  title: {
    type: String
  },
  price: {
    type: Number
  },
  description: {
    type: String
  },
  specification: {
    size: {
      width: {
        type: Number
      },
      height: {
        type: Number
      },
    },
    type: {
      type: String
    },
    material: {
      type: String
    },
  },
  tags: {
    type: [String]
  }
});

module.exports = mongoose.model('Product', productSchema);