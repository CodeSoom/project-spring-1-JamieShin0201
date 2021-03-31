import React from 'react';
import CategoriesContainer from '../containers/CategoriesContainer';
import ProductsContainer from '../containers/ProductsContainer';
import styles from './ProductsPage.module.css';

export default function ProductsPage({ products }) {
  return (
    <div className={styles.container}>
      <CategoriesContainer />
      <ProductsContainer products={products} />
    </div>
  );
}
