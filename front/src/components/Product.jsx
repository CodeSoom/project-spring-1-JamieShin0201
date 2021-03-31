import React from 'react';
import styles from './Product.module.css';

export default function Product({ product }) {
  const { name, imageUrl, originalPrice, discountedPrice } = product;

  return (
    <li className={styles.container}>
      <div className={styles.product}>
        <img className={styles.logo} src={imageUrl} alt="product" />
        <p className={styles.maker}>solebysole</p>
        <p className={styles.name}>{name}</p>
        <p className={styles.original}>
          <span className={styles.originalPrice}>{originalPrice}원</span>
          <span className={styles.discountPercent}>10%</span>
        </p>
        <p className={styles.discounted}>
          <span className={styles.discountedPrice}>{discountedPrice}</span>
          <span className={styles.suffix}>원</span>
        </p>
      </div>
    </li>
  );
}
