import React from 'react';

import styles from './LoginPage.module.css';

import LoginFormContainer from '../../containers/loginFormContainer/LoginFormContainer';

export default function LoginPage() {
  return (
    <section className={styles.container}>
      <h2>Login</h2>
      <LoginFormContainer />
    </section>
  );
}
