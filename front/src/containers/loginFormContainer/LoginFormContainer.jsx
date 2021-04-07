import React from 'react';
import styles from './LoginFormContainer.module.css';

export default function LoginFormContainer() {
  return (
    <section className={styles.loginForm}>
      <input className={styles.email} type="text" placeholder="email" />
      <input
        className={styles.password}
        type="password"
        placeholder="password"
      />
      <div className={styles.container}>
        <div className={styles.emailsave}>
          <input type="checkbox" id="emailsave" />
          <label htmlFor="emailsave">이메일 저장하기</label>
        </div>
        <span>아이디 / 비밀번호를 잊어버리셨나요?</span>
      </div>
      <button type="button" className={styles.loginBtn}>
        Login
      </button>
      <button type="button" className={styles.signUpBtn}>
        Sign up
      </button>
    </section>
  );
}
