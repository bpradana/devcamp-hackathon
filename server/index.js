const bodyParser = require('body-parser');
const express = require('express');
const mongoose = require('mongoose');
const morgan = require('morgan');
require('dotenv').config();

const app = express();
const port = process.env.PORT || 3000;
const mongo_url = process.env.MONGO_URL

mongoose.connect(
  `mongodb+srv://${process.env.MONGO_USERNAME}:${process.env.MONGO_PASSWORD}@${process.env.MONGO_HOST}/${process.env.MONGO_DATABASE}`,
  { useNewUrlParser: true, useUnifiedTopology: true },
  () => {
    console.log('Connected to MongoDB')
  }
)

app.use(bodyParser.json())
app.use(express.urlencoded({ extended: true }));
app.use(morgan('dev'));

const routes = require('./route');
app.use('/product', routes);

app.listen(port, () => {
  console.log('Listening on port 3000');
})