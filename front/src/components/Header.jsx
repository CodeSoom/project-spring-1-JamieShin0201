import React from 'react';
import styles from './Header.module.css';

export default function Header() {
  return (
    <header className={styles.header}>
      <img className={styles.logo} src="/images/logo.png" alt="logo" />
      <form className={styles.search}>
        <input
          className={styles.input}
          type="text"
          placeholder="찾으시는 작품이 있으신가요?"
        />
        <button className={styles.button} type="submit">
          <i className="fas fa-search" />
        </button>
      </form>
      <ul className={styles.navigation}>
        <li className={styles.cart}>
          <i className="fas fa-shopping-cart" />
          <a href="/">Cart</a>
        </li>
        <li className={styles.login}>
          <i className="fas fa-sign-in-alt" />
          <a href="/">Login</a>
        </li>
      </ul>
    </header>
  );
}
