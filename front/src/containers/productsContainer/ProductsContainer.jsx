import React, { useState, useEffect } from 'react';

import styles from './ProductsContainer.module.css';

import Product from '../../components/product/Product';

import { fetchProducts } from '../../services/api';

export default function ProductsContainer({ onClickProduct }) {
  const [products, setProducts] = useState([]);

  useEffect(async () => {
    const data = await fetchProducts();
    setProducts(data);
  }, []);

  return (
    <ul className={styles.products}>
      {products.map((product) => (
        <Product
          key={product.id}
          product={product}
          onClickProduct={onClickProduct}
        />
      ))}
    </ul>
  );
}
