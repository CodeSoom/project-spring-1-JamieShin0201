import React from 'react';

import { useParams } from 'react-router-dom';

import styles from './ProductPage.module.css';

import ProductContainer from '../../containers/productContainer/ProductContainer';

export default function ProductsPage({ params }) {
  const { id } = params || useParams();

  return (
    <section className={styles.container}>
      <ProductContainer id={id} />
    </section>
  );
}
