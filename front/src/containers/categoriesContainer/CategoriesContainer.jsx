import React from 'react';
import styles from './CategoriesContainer.module.css';

export default function CategoriesContainer() {
  const categories = [
    { id: 1, name: 'Card wallet' },
    { id: 2, name: 'Bag' },
    { id: 3, name: 'Home deco' },
  ];
  return (
    <ul className={styles.categories}>
      {categories.map(({ id, name }) => (
        <li key={id}>
          <a className={styles.category} href="/">
            {name}
          </a>
        </li>
      ))}
    </ul>
  );
}
