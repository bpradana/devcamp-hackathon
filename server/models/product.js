const mongoose = require("mongoose");

const productSchema = new mongoose.Schema({
  title: {
    type: String,
    required: true,
  },
  image: {
    type: String,
  },
  price: {
    type: Number,
  },
  description: {
    type: String,
  },
  specification: {
    sizes: [
      {
        name: {
          type: String,
        },
        waist: {
          type: Number,
        },
        chest: {
          type: Number,
        },
        neck: {
          type: Number,
        },
      },
    ],
    type: {
      type: String,
    },
    material: {
      type: String,
    },
  },
  tags: {
    type: [String],
  },
  exif: {
    type: Boolean,
  },
});

module.exports = mongoose.model("Product", productSchema);
