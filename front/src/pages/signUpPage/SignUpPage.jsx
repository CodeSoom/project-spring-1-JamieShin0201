import React from 'react';

import styles from './SignUpPage.module.css';

import SignUpFormContainer from '../../containers/signUpFormContainer/SignUpFormContainer';

export default function SignUpPage() {
  return (
    <section className={styles.container}>
      <h2>Sign Up</h2>
      <SignUpFormContainer />
    </section>
  );
}
