import React from 'react';

import { render, unmountComponentAtNode } from 'react-dom';

import { act } from 'react-dom/test-utils';

import ProductContainer from './ProductContainer';

import PRODUCT from '../../../fixtures/product';

import formatMoney from '../../utils/util';

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
      json: () => Promise.resolve(PRODUCT),
    }),
  );

  await act(async () => {
    render(<ProductContainer />, container);
  });

  expect(container).toHaveTextContent(PRODUCT.name);
  expect(container).toHaveTextContent(formatMoney(PRODUCT.originalPrice));
  expect(container).toHaveTextContent(formatMoney(PRODUCT.discountedPrice));

  global.fetch.mockRestore();
});
