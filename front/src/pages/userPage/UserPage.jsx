import React from 'react';

import styles from './UserPage.module.css';

import UserContainer from '../../containers/userContainer/UserContainer';
import { loadItem } from '../../services/storage';

export default function UserPage() {
  const accessToken = loadItem('accessToken');

  return (
    <section className={styles.container}>
      <h2>My Infomation</h2>
      <UserContainer accessToken={accessToken} />
    </section>
  );
}
