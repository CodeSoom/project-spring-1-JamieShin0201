import React from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import styles from './SliderContainer.module.css';

export default function SliderContainer() {
  const settings = {
    dots: true,
    infinite: true,
    speed: 1000,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
  };

  return (
    // eslint-disable-next-line react/jsx-props-no-spreading
    <Slider {...settings} className={styles.slider}>
      {[1, 2, 3, 4, 5].map((i) => (
        <div>
          <img
            className={styles.sliderImg}
            src={`/images/slider${i}.jpg`}
            alt="slider"
          />
        </div>
      ))}
    </Slider>
  );
}
