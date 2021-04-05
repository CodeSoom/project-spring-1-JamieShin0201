import React from 'react';

import styles from './AboutPage.module.css';

export default function AboutPage() {
  return (
    <section className={styles.container}>
      <ul className={styles.content}>
        <li className={styles.idea}>
          <img src="/images/about3.jpg" alt="img" />
          <br />
          <br />
          <h1>I&nbsp;D&nbsp;E&nbsp;A</h1>
          <p>작은 특징하나가 큰 차이를 만듭니다</p>
          <p>독특한 디자인과 포인트컬러 스티치로</p>
          <p>나만의 개성과 스타일을 표현해보세요 💛</p>
        </li>
        <li className={styles.ideal}>
          <img src="/images/about2.jpg" alt="img" />
          <br />
          <br />
          <h1>I&nbsp;D&nbsp;E&nbsp;A&nbsp;L</h1>
          <p>100%핸드메이드 제품으로 직접 디자인하고 제작하며,</p>
          <p>내가 사용한다는 마음으로</p>
          <p>정성을 다해 정직하게 작업합니다</p>
          <br />
          <p>개성있는 제품에 고객님의 흔적이 묻어나</p>
          <p>오래도록 간직하고 싶은</p>
          <p>유일한, 단 하나의[SOLE] 제품이 되길 바랍니다</p>
          <br />
          <br />
          <br />
          <p className={styles.writer}>SOLE by SOLE</p>
        </li>
      </ul>
    </section>
  );
}
