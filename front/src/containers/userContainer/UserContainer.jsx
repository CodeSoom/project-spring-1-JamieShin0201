import React, { useEffect, useRef, useState } from 'react';

import { useHistory } from 'react-router';

import styles from './UserContainer.module.css';

import { fetchUser, editUser, deleteUser } from '../../services/api';
import { removeItem } from '../../services/storage';

export default function UserContainer({ accessToken }) {
  const [user, setUser] = useState({});

  const formRef = useRef();
  const emailRef = useRef();
  const nameRef = useRef();
  const passwordRef = useRef();
  const confirmRef = useRef();

  const history = useHistory();

  useEffect(async () => {
    const data = await fetchUser(accessToken);
    setUser({ ...data, confirm: data.password });
  }, []);

  function onEdit() {
    const editForm = {
      accessToken,
      name: nameRef.current.value,
    };

    const result = window.confirm('변경 사항을 적용하시겠습니까?');
    if (!result) {
      return;
    }

    editUser(editForm);
  }

  function onBack() {
    history.push('/');
  }

  function onDelete() {
    const result = window.confirm('정말로 삭제하시겠습니까?');
    if (!result) {
      return;
    }

    deleteUser(accessToken);
    removeItem('accessToken');
    history.push('/');
  }

  function onChange(event) {
    const {
      target: { name, value },
    } = event;

    setUser({
      ...user,
      [name]: value,
    });
  }

  return (
    <form className={styles.editForm} ref={formRef}>
      <input
        className={styles.email}
        ref={emailRef}
        type="email"
        placeholder="email"
        value={user.email || ''}
        readOnly
      />
      <input
        className={styles.name}
        ref={nameRef}
        type="text"
        placeholder="name"
        name="name"
        value={user.name || ''}
        onChange={onChange}
      />
      <input
        className={styles.password}
        ref={passwordRef}
        type="password"
        placeholder="password"
        name="password"
        value={user.password || ''}
        readOnly
      />
      <input
        className={styles.password}
        ref={confirmRef}
        type="password"
        value={user.confirm || ''}
        placeholder="confirm password"
        name="confirm"
        readOnly
      />
      <button type="button" className={styles.editBtn} onClick={onEdit}>
        Edit
      </button>
      <button type="button" className={styles.backBtn} onClick={onBack}>
        Back
      </button>
      <button type="button" className={styles.deleteBtn} onClick={onDelete}>
        Delete
      </button>
    </form>
  );
}
