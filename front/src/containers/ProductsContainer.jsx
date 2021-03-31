import React from 'react';
import styles from './ProductsContainer.module.css';
import Product from '../components/Product';

export default function ProductsContainer({ products }) {
  return (
    <ul className={styles.products}>
      {products.map((product) => (
        <Product key={product.id} product={product} />
      ))}
    </ul>
  );
}
