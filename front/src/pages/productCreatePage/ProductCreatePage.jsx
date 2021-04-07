import React from 'react';

import styles from './ProductCreatePage.module.css';

import ProductCreateContainer from '../../containers/productCreateContainer/ProductCreateContainer';

export default function ProductCreatePage({ FileInput }) {
  return (
    <section className={styles.container}>
      <ProductCreateContainer FileInput={FileInput} />
    </section>
  );
}
