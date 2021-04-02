import React from 'react';
import HeaderContent from '../../components/header/HeaderContent';
import SliderContainer from '../../containers/sliderContainer/SliderContainer';
import styles from './HomePage.module.css';

export default function HomePage() {
  return (
    <>
      <HeaderContent />
      <div className={styles.slider}>
        <SliderContainer />
      </div>
      <div className={styles.content}>
        <ul className={styles.images}>
          {[1, 2, 3, 4, 5, 6].map((i) => (
            <li key={i} className={styles.container}>
              <img src={`/images/product${i}.jpg`} alt="img" />
            </li>
          ))}
        </ul>
      </div>
    </>
  );
}
