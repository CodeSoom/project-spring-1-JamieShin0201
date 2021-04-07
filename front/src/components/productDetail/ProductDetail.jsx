/* eslint-disable react/no-array-index-key */
import React, { useState, useRef } from 'react';

import styles from './ProductDetail.module.css';

import formatMoney from '../../utils/util';

export default function ProductDetail({ product }) {
  const [itemId, setItemId] = useState(0);
  const [items, setItems] = useState([]);

  const selectRef = useRef();

  const { name, originalPrice, discountedPrice, options } = product;

  function onChangeOption() {
    const nextId = itemId + 1;
    const optionId = selectRef.current.value;

    if (optionId === '0') {
      return;
    }

    const foundOption = options.map(({ children }) =>
      children.filter((c) => c.id === Number(optionId)),
    )[0][0];

    setItemId(nextId);
    setItems([
      ...items,
      {
        id: nextId,
        name: foundOption.name,
        count: 1,
        price: discountedPrice + foundOption.additionalPrice,
        totalPrice: discountedPrice + foundOption.additionalPrice,
      },
    ]);
  }

  function onPlus(obj) {
    const newItems = items.map((item) => {
      if (item.id === obj.id) {
        const count = item.count + 1 > 99 ? 99 : item.count + 1;
        const totalPrice = item.price * count;

        return { ...item, count, totalPrice };
      }

      return item;
    });

    setItems(newItems);
  }

  function onMinus(obj) {
    const newItems = items.map((item) => {
      if (item.id === obj.id) {
        const count = item.count - 1 < 1 ? 1 : item.count - 1;
        const totalPrice = item.price * count;

        return { ...item, count, totalPrice };
      }

      return item;
    });

    setItems(newItems);
  }

  function onCancel(obj) {
    const newItems = items.filter((item) => item !== obj);

    setItems(newItems);
  }

  return (
    <>
      <div className={styles.info}>
        <div className={styles.productInfo}>
          <p className={styles.name}>{name}</p>
          <table>
            <tbody>
              <tr witdh="100%">
                <td width="30%">판매가</td>
                <td width="70%">
                  <p className={styles.original}>
                    <span className={styles.originalPrice}>
                      {originalPrice && formatMoney(originalPrice)}
                    </span>
                    <span className={styles.discountPercent}>10%</span>
                  </p>
                  <p className={styles.discounted}>
                    <span className={styles.discountedPrice}>
                      {discountedPrice && formatMoney(discountedPrice)}
                    </span>
                    <span className={styles.suffix}>원</span>
                  </p>
                </td>
              </tr>
              <tr>
                <td>배송비</td>
                <td className={styles.delivery}>
                  <p>2,500원 </p>
                  <p>제주 및 도서 산간 3,500원 추가</p>
                </td>
              </tr>
              <tr>
                <td>제작정보</td>
                <td className={styles.makePeriod}>
                  주문시 제작 (평균 3 ~ 5일)
                </td>
              </tr>
            </tbody>
          </table>
          {options &&
            options.map((option) => (
              <div key={option.id}>
                <p>{option.name}</p>
                <select
                  name={option.name}
                  key={option.id}
                  className={styles.option}
                  onChange={onChangeOption}
                  ref={selectRef}
                >
                  <option value="0">선택하세요.</option>
                  {option.children.map((child) => (
                    <option key={child.id} value={child.id}>
                      {child.name} &nbsp;&nbsp;({child.additionalPrice} 원 추가)
                    </option>
                  ))}
                </select>
              </div>
            ))}
          {items &&
            items.map((item) => (
              <section className={styles.item} key={item.id}>
                <div className={styles.top}>
                  <span>{item.name}</span>
                  <button
                    type="button"
                    className={styles.cancel}
                    onClick={() => onCancel(item)}
                  >
                    <i className="fas fa-times" />
                  </button>
                </div>
                <div className={styles.bottom}>
                  <div className={styles.buttons}>
                    <button
                      type="button"
                      className={styles.minus}
                      onClick={() => onMinus(item)}
                    >
                      -
                    </button>
                    <input
                      type="text"
                      className={styles.count}
                      value={item.count}
                      readOnly
                    />
                    <button
                      type="button"
                      className={styles.plus}
                      onClick={() => onPlus(item)}
                    >
                      +
                    </button>
                  </div>
                  <span>{formatMoney(item.totalPrice)}원</span>
                </div>
              </section>
            ))}
        </div>
        <div className={styles.buttonContainer}>
          <button type="button" className={styles.buyBtn}>
            BUY IT NOW
          </button>
          <button type="button" className={styles.cartBtn}>
            ADD TO CART
          </button>
        </div>
      </div>
    </>
  );
}
