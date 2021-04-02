import React from 'react';
import { Route, Switch } from 'react-router-dom';
// import styles from './App.module.css';
import ProductsPage from './pages/productsPage/ProductsPage';

import Header from './components/header/Header';
import Footer from './components/footer/Footer';
import HomePage from './pages/homePage/HomePage';

function App() {
  return (
    <>
      <Switch>
        <Route exact path={['/', '/home']}>
          <Header />
          <HomePage />
        </Route>
        <Route exact path={['/products']}>
          <Header color="dark" />
          <ProductsPage />
        </Route>
      </Switch>
      <Footer />
    </>
  );
}

export default App;
