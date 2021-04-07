import React from 'react';

import { MemoryRouter } from 'react-router-dom';

import { render } from '@testing-library/react';

import ProductPage from './ProductPage';

test('ProductPage', () => {
  render(
    <MemoryRouter>
      <ProductPage />
    </MemoryRouter>,
  );
});
