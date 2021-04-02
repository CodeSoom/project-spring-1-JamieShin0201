import React from 'react';
import { Link } from 'react-router-dom';
import styles from './Footer.module.css';

export default function Footer() {
  return (
    <footer className={styles.footer}>
      <div className={styles.outLinks}>
        <a href="/">
          <i className="fab fa-instagram" />
        </a>
        <a href="/">
          <i className="fab fa-blogger-b" />
        </a>
        <a href="/">
          <i className="fas fa-store" />
        </a>
      </div>

      <div className={styles.links}>
        <Link to="/home">HOME</Link>
        <Link to="/agreement">AGREEMENT</Link>
        <Link to="/guide">GUIDE</Link>
        <Link to="/privacy-policy">PRIVACY POLICY</Link>
      </div>

      <div className={styles.info}>
        <p>aaaa</p>
        <p>aaaa</p>
        <p>aaaa</p>
        <p>aaaa</p>
      </div>
    </footer>
  );
}
