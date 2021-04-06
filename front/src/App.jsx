import React from 'react';

import { Route, Switch } from 'react-router-dom';

import Header from './components/header/Header';
import Footer from './components/footer/Footer';

import HomePage from './pages/homePage/HomePage';
import ProductsPage from './pages/productsPage/ProductsPage';
import AboutPage from './pages/aboutPage/AboutPage';

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
        <Route exact path={['/about']}>
          <Header color="dark" />
          <AboutPage />
        </Route>
      </Switch>
      <Footer />
    </>
  );
}

export default App;
