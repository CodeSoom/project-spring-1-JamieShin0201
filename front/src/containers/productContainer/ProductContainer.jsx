/* eslint-disable jsx-a11y/anchor-is-valid */
/* eslint-disable react/no-array-index-key */
/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable react/jsx-props-no-spreading */

import React, { useState, useEffect } from 'react';

import Slider from 'react-slick';

import styles from './ProductContainer.module.css';

import { fetchProduct } from '../../services/api';

import ProductDetail from '../../components/productDetail/ProductDetail';

export default function ProductContainer({ id }) {
  const [product, setProduct] = useState([]);

  useEffect(async () => {
    const data = await fetchProduct(id);
    setProduct(data);
  }, []);

  const { images, description } = product;

  const settings = {
    customPaging(i) {
      return (
        <a className={styles.imageLink}>
          <img src={images[i].url} width="110" height="110" />
        </a>
      );
    },
    dots: true,
    dotsClass: 'slick-dots slick-thumb',
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  };

  return (
    <>
      <div className={styles.container}>
        <Slider {...settings} className={styles.slider}>
          {images &&
            images.map((image) => (
              <div key={image.id}>
                <img className={styles.sliderImg} src={image.url} alt="img" />
              </div>
            ))}
        </Slider>
        <ProductDetail product={product} />
      </div>
      <hr />
      <div className={styles.content}>
        {description &&
          description.split('\n').map((line, index) => (
            <span key={index}>
              {line}
              <br />
            </span>
          ))}
      </div>
    </>
  );
}
