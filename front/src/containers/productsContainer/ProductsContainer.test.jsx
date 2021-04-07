import React from 'react';

import { render, unmountComponentAtNode } from 'react-dom';

import { act } from 'react-dom/test-utils';

import ProductsContainer from './ProductsContainer';

import PRODUCTS from '../../../fixtures/products';

let container = null;
beforeEach(() => {
  container = document.createElement('div');
  document.body.appendChild(container);
});

afterEach(() => {
  unmountComponentAtNode(container);
  container.remove();
  container = null;
});

it('renders products', async () => {
  jest.spyOn(global, 'fetch').mockImplementation(() =>
    Promise.resolve({
      json: () => Promise.resolve(PRODUCTS),
    }),
  );

  await act(async () => {
    render(<ProductsContainer />, container);
  });

  PRODUCTS.forEach((product) => {
    expect(container).toHaveTextContent(product.name);
  });

  global.fetch.mockRestore();
});
