import React from 'react';
import styles from './Product.module.css';

import formatMoney from '../../utils/util';

export default function Product({ product, onClickProduct }) {
  const { imageUrl, name, originalPrice, discountedPrice } = product;

  const discountPercent =
    discountedPrice < originalPrice
      ? ((originalPrice - discountedPrice) / originalPrice) * 100
      : null;

  function handleClick() {
    return (event) => {
      event.preventDefault();
      onClickProduct(product);
    };
  }

  return (
    <li className={styles.container}>
      <a href={`/products/${product.id}`} onClick={handleClick}>
        <div
          className={styles.product}
          style={{
            background: `url(${imageUrl}) center/cover no-repeat`,
          }}
        />
        <div className={styles.info}>
          <p>{name}</p>
          {discountPercent && (
            <>
              <p className={styles.original}>
                <span className={styles.originalPrice}>
                  {formatMoney(originalPrice)}
                </span>
                <span className={styles.discountPercent}>
                  {discountPercent}%
                </span>
              </p>
              <p className={styles.discounted}>
                <span className={styles.discountedPrice}>
                  {formatMoney(discountedPrice)}
                </span>
              </p>
            </>
          )}
          {!discountPercent && (
            <p className={styles.discounted}>
              <span className={styles.discountedPrice}>
                {formatMoney(originalPrice)}
              </span>
            </p>
          )}
        </div>
      </a>
    </li>
  );
}
