import React from 'react';

import { Route, Switch } from 'react-router-dom';

import Header from './components/header/Header';
import Footer from './components/footer/Footer';

import HomePage from './pages/homePage/HomePage';
import AboutPage from './pages/aboutPage/AboutPage';
import LoginPage from './pages/loginPage/LoginPage';
import ProductsPage from './pages/productsPage/ProductsPage';
import ProductPage from './pages/productPage/ProductPage';
import ProductCreatePage from './pages/productCreatePage/ProductCreatePage';

import styles from './App.module.css';

function App({ FileInput }) {
  return (
    <div className={styles.container}>
      <Switch>
        <Route exact path={['/', '/home']}>
          <Header />
          <HomePage />
        </Route>
        <Route exact path="/about">
          <Header color="dark" />
          <AboutPage />
        </Route>
        <Route exact path="/login">
          <Header color="dark" />
          <LoginPage />
        </Route>
        <Route exact path="/products">
          <Header color="dark" />
          <ProductsPage />
        </Route>
        <Route exact path="/products/create">
          <Header color="dark" />
          <ProductCreatePage FileInput={FileInput} />
        </Route>
        <Route path="/products/:id">
          <Header color="dark" />
          <ProductPage />
        </Route>
      </Switch>
      <Footer />
    </div>
  );
}

export default App;
