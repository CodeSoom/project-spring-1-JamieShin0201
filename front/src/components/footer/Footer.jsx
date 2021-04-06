import React from 'react';
import { Link } from 'react-router-dom';
import styles from './Footer.module.css';

export default function Footer() {
  return (
    <footer className={styles.footer}>
      <div className={styles.outLinks}>
        <a
          target="_blank"
          rel="noopener noreferrer"
          href="https://www.instagram.com/sole_bysole/"
        >
          <i className="fab fa-instagram" />
        </a>
        <a
          target="_blank"
          rel="noopener noreferrer"
          href="https://blog.naver.com/bumbumi1"
        >
          <i className="fab fa-blogger-b" />
        </a>
        <a
          target="_blank"
          rel="noopener noreferrer"
          href="https://www.idus.com/w/artist/33ff73f6-fbbb-4b88-9e3b-73b9a4a8bd3f/product"
        >
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
        <p>
          Company 솔바이솔레 &nbsp;|&nbsp; Owner 정선 &nbsp;|&nbsp; Email
          bumbumi1@naver.com
        </p>
        <p>
          Business-Number 146-17-01448 &nbsp;|&nbsp; Mail-Order Licence
          2021-서울구로-0739
        </p>
        <p>Copyright © Sole by Sole All rights reserved.</p>
      </div>
    </footer>
  );
}
