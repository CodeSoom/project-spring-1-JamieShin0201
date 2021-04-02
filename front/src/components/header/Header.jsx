import React, { useState, useRef, useEffect } from 'react';
import { Link } from 'react-router-dom';
import styles from './Header.module.css';

export default function Header({ color }) {
  const [scrolled, setScrolled] = useState(true);
  const documentRef = useRef(document);

  const handleScroll = () => {
    const { pageYOffset } = window;
    const top = pageYOffset === 0;
    setScrolled(top);
  };

  useEffect(() => {
    documentRef.current.addEventListener('scroll', handleScroll);
    return () =>
      documentRef.current.removeEventListener('scroll', handleScroll);
  });

  const scrollStyle = scrolled ? '' : styles.scroll;
  const darkStyle = color === 'dark' ? styles.dark : '';

  return (
    <>
      <nav className={`${styles.header} ${darkStyle} ${scrollStyle}`}>
        <ul className={styles.leftNavigation}>
          <li className={styles.home}>
            <Link to="/home">home</Link>
          </li>
          <li className={styles.about}>
            <Link to="/about">about</Link>
          </li>
          <li className={styles.shop}>
            <Link to="/products">shop</Link>
          </li>
        </ul>
        <p className={styles.title}>S&nbsp;O&nbsp;L&nbsp;E</p>
        <ul className={styles.rightNavigation}>
          <li className={styles.search}>
            <i className={`fas fa-search ${styles.searchLogo}`} />
          </li>
          <li className={styles.login}>
            <i className={`fas fa-user ${styles.loginLogo}`} />
          </li>
          <li className={styles.cart}>
            <i className={`fas fa-shopping-cart ${styles.cartLogo}`} />
          </li>
        </ul>
      </nav>
    </>
  );
}
