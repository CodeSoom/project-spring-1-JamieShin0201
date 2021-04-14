import React, { useRef } from 'react';
import { useHistory } from 'react-router';
import { postLogin } from '../../services/api';
import { saveItem } from '../../services/storage';
import styles from './LoginFormContainer.module.css';

export default function LoginFormContainer() {
  const formRef = useRef();
  const emailRef = useRef();
  const passwordRef = useRef();
  const history = useHistory();

  function onSignUp() {
    history.push('signUp');
  }

  async function onLogin() {
    const LoginForm = {
      email: emailRef.current.value,
      password: passwordRef.current.value,
    };

    const accessToken = await postLogin(LoginForm);
    saveItem('accessToken', accessToken);

    history.push('/');
  }

  return (
    <form className={styles.loginForm} ref={formRef}>
      <input
        className={styles.email}
        ref={emailRef}
        type="text"
        placeholder="email"
      />
      <input
        className={styles.password}
        ref={passwordRef}
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
      <button type="button" className={styles.loginBtn} onClick={onLogin}>
        Login
      </button>
      <button type="button" className={styles.signUpBtn} onClick={onSignUp}>
        Sign up
      </button>
    </form>
  );
}
