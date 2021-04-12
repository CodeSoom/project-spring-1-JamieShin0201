import React, { useRef } from 'react';

import { useHistory } from 'react-router';

import styles from './SignUpFormContainer.module.css';

import { postSignUp } from '../../services/api';

export default function SignUpFormContainer() {
  const formRef = useRef();
  const emailRef = useRef();
  const nameRef = useRef();
  const passwordRef = useRef();
  const confirmRef = useRef();

  const history = useHistory();

  function onSignUp() {
    const signUpForm = {
      email: emailRef.current.value,
      name: nameRef.current.value,
      password: passwordRef.current.value,
    };

    postSignUp(signUpForm);
    history.push('/login');
  }

  function onBack() {
    history.push('/login');
  }

  return (
    <form className={styles.signUpForm} ref={formRef}>
      <input
        className={styles.email}
        ref={emailRef}
        type="email"
        placeholder="email"
      />
      <input
        className={styles.name}
        ref={nameRef}
        type="text"
        placeholder="name"
      />
      <input
        className={styles.password}
        ref={passwordRef}
        type="password"
        placeholder="password"
      />
      <input
        className={styles.password}
        ref={confirmRef}
        type="password"
        placeholder="confirm password"
      />
      <button type="button" className={styles.signUpBtn} onClick={onSignUp}>
        Sign up
      </button>
      <button type="button" className={styles.backBtn} onClick={onBack}>
        Back
      </button>
    </form>
  );
}
