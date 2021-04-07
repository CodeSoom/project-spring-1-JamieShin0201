import React from 'react';

import { MemoryRouter } from 'react-router-dom';

import { render } from '@testing-library/react';

import ProductsPage from './ProductsPage';

describe('ProductsPage', () => {
  function renderProductsPage() {
    return render(
      <MemoryRouter>
        <ProductsPage />
      </MemoryRouter>,
    );
  }

  it('renders catetories', () => {
    const { queryByText } = renderProductsPage();

    expect(queryByText('Card wallet')).not.toBeNull();
  });
});
