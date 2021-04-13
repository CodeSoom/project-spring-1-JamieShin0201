/* eslint-disable jsx-a11y/no-noninteractive-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */

import React, { useState, useRef, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { loadItem } from '../../services/storage';
import styles from './Header.module.css';

export default function Header({ color }) {
  const [scrolled, setScrolled] = useState(true);
  const accessToken = loadItem('accessToken');

  const documentRef = useRef(document);

  const history = useHistory();

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

  function handleClick(link) {
    const url = `/${link}`;
    history.push(url);
  }

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
          <li
            className={styles.login}
            onClick={() => {
              const path = accessToken ? 'me' : 'login';
              return handleClick(path);
            }}
          >
            <i className={`fas fa-user ${styles.loginLogo}`} />
          </li>
          <li className={styles.cart} onClick={() => handleClick('cart')}>
            <i className={`fas fa-shopping-cart ${styles.cartLogo}`} />
          </li>
        </ul>
      </nav>
    </>
  );
}
