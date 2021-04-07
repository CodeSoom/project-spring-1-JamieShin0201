import React, { useState, useRef } from 'react';

import styles from './ProductCreateContainer.module.css';

import { postProduct } from '../../services/api';

export default function ProductCreateContainer({ FileInput }) {
  const formRef = useRef();
  const nameRef = useRef();
  const originalPriceRef = useRef();
  const discountedPriceRef = useRef();
  const categoryRef = useRef();
  const descriptionRef = useRef();

  const [files, setFiles] = useState([]);

  const onFileChange = (file) => {
    const newFiles = [...files];
    newFiles[file.index] = { url: file.url };
    setFiles(newFiles);
  };

  const onSubmit = (event) => {
    event.preventDefault();

    const product = {
      name: nameRef.current.value || '',
      originalPrice: originalPriceRef.current.value || '',
      discountedPrice: discountedPriceRef.current.value,
      category: categoryRef.current.value || '',
      description: descriptionRef.current.value || '',
      images: files,
    };

    postProduct(product);
    formRef.current.reset();
  };

  return (
    <section className={styles.container}>
      <form ref={formRef}>
        <div className={styles.subContainer}>
          <div className={styles.images}>
            <div className={styles.main}>
              <div className={styles.mainImage}>
                <FileInput
                  index={0}
                  url={!files[0] ? null : files[0].url}
                  onFileChange={onFileChange}
                />
              </div>
            </div>
            <div className={styles.sub}>
              {[1, 2, 3, 4].map((i) => (
                <div key={i} className={styles.subImage}>
                  <FileInput
                    index={i}
                    url={!files[i] ? null : files[i].url}
                    onFileChange={onFileChange}
                  />
                </div>
              ))}
            </div>
          </div>
          <div className={styles.info}>
            <table>
              <tbody>
                <tr witdh="100%">
                  <td width="40%">작품명</td>
                  <td width="60%">
                    <input type="text" className={styles.title} ref={nameRef} />
                  </td>
                </tr>
                <tr>
                  <td>가격</td>
                  <td>
                    <input
                      type="text"
                      className={styles.originalPrice}
                      ref={originalPriceRef}
                    />
                  </td>
                </tr>
                <tr>
                  <td>할인된 가격</td>
                  <td>
                    <input
                      type="text"
                      className={styles.discountedPrice}
                      ref={discountedPriceRef}
                    />
                  </td>
                </tr>
                <tr>
                  <td>카테고리</td>
                  <td>
                    <select className={styles.category} ref={categoryRef}>
                      <option>선택하세요.</option>
                      <option value="CARD_WALLET">Card Wallet</option>
                      <option value="Bag">Bag</option>
                      <option value="Home deco">Home deco</option>
                    </select>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div className={styles.content}>
          <p>작품 설명</p>
          <textarea ref={descriptionRef} cols="40" wrap="hard" />
        </div>
        <div className={styles.buttons}>
          <button type="button" className={styles.tempBtn}>
            임시저장
          </button>
          <button type="button" className={styles.saveBtn} onClick={onSubmit}>
            등록하기
          </button>
        </div>
      </form>
    </section>
  );
}
