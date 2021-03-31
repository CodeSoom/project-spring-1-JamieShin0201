import React, { useEffect, useState } from 'react';
import styles from './App.module.css';
import fetchProducts from './services/api';
import Header from './components/Header';
import ProductsPage from './pages/ProductsPage';
import SliderContainer from './containers/SliderContainer';
import CustomOrder from './components/CustomOrder';

function App() {
  const [products, setProducts] = useState([]);

  useEffect(async () => {
    const data = await fetchProducts();
    setProducts(data);
  }, []);

  return (
    <>
      <Header />
      <SliderContainer />
      <ProductsPage className={styles.products} products={products} />
      <CustomOrder />
    </>
  );
}

export default App;
