import React from 'react';
import styles from './HeaderContent.module.css';

export default function HeaderContent() {
  return (
    <section
      className={styles.content}
      style={{
        background: 'url(/images/product.jpg) center/cover no-repeat',
      }}
    >
      <p className={styles.descrption}>
        &lsquo; 유일한, 단 하나의 : SOLE &rsquo;
      </p>
    </section>
  );
}
