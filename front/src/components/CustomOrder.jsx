import React from 'react';
import styles from './CustomOrder.module.css';

export default function CustomOrder() {
  return (
    <div className={styles.customOrder}>
      <div className={styles.content}>
        <p>찾으시는 작품이 없으신가요?</p>
        <p>그렇다면 주문 제작을 이용해보세요.</p>
      </div>
      <button type="button">주문 제작 요청</button>
    </div>
  );
}
