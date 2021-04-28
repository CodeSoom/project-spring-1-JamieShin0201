# :briefcase: [Sole by Sole](https://solebysole.com/)

<p align="center">
<img src="https://user-images.githubusercontent.com/65442049/116219913-1e8e5500-a787-11eb-902c-912f0784b1f9.png" width="300" />
</p>

> 유일한, 단 하나의 제품

<hr>
<br>

## :sunny: 서비스 소개

나만의 지갑, 나만의 소품들이 필요하지 않으신가요?<br>
개성 있는 가죽 작품들을 이제 **솔바이솔레**에서 만나보세요.

<hr>
<br>

## 🛠 핵심 기능

- 판매자는 작품들을 등록, 수정, 삭제할 수 있습니다.
- 사용자는 작품들을 장바구니에 담거나 구매할 수 있습니다.
- 사용자는 검색과 카테고리를 통한 필터링 기능으로 원하는 상품만 제한해서 볼 수 있습니다.

<hr>
<br>

## :computer: 기술 스택

### Back-end

- Java 11
- Spring Boot / Spring Data JPA / Spring Security / Spring Rest Docs
- Junit5 / Mockito / JaCoCo
- AWS (EC2, RDS, Route53) / MariaDB

### Front-end

- React / React Hooks / React Router Dom
- PostCSS
- Jest, React Testing Library

<hr>
<br>

## :factory: 테스트

### Spring Test

```bash
$ ./gradlew test
```

### 커버리지 확인

테스트 실행 후 `app/build/reports` 폴더에 커버리지 정보가 저장됩니다.
`app/build/reports/jacoco/test/html/index.html` 파일을 브라우저에서 열면 확인 할 수 있습니다.

### React Test

#### 설치

```bash
$ cd front
$ npm install
```

#### 실행

```bash
$ npm test
```

<hr>
<br>

## :books: 문서

### JavaDoc

```bash
$ ./gradlew javadoc
```

`app/build/docs/javadoc/index.html` 파일을 브라우저에서 열면 확인 할 수 있습니다.

### Spring Rest Docs

```bash
$ ./gradlew asciidoc
```

`app/build/asciidoc/html5/index.html` 파일을 브라우저에서 열면 확인 할 수 있습니다.

<hr>
<br>

## :page_with_curl: 참고

- [API 문서](https://solebysole.com/docs/index.html)
- [DB 명세서](https://www.notion.so/DB-421425ca814a462a86159a0c1172223d)
- [초기 페이지 기획서](https://whimsical.com/9dJwah9meqpQp8YgJgNAhu)

<hr>
<br>
