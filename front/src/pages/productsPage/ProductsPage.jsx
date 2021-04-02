import React from 'react';

import { useHistory } from 'react-router';

import CategoriesContainer from '../../containers/categoriesContainer/CategoriesContainer';

import ProductsContainer from '../../containers/productsContainer/ProductsContainer';

import styles from './ProductsPage.module.css';

export default function ProductsPage() {
  const history = useHistory();

  function handleClickProduct(product) {
    const url = `/products/${product.id}`;
    history.push(url);
  }

  return (
    <>
      <div className={styles.container}>
        <CategoriesContainer />
        <ProductsContainer onClickProduct={handleClickProduct} />
      </div>
    </>
  );
}
