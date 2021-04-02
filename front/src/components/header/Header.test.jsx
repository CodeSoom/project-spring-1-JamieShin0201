import React from 'react';

import { render } from '@testing-library/react';

import { MemoryRouter } from 'react-router';

import Header from './Header';

describe('Header', () => {
  it('renders navagations', () => {
    const { container } = render(
      <MemoryRouter>
        <Header />
      </MemoryRouter>,
    );

    expect(container).toHaveTextContent('home');
    expect(container).toHaveTextContent('about');
    expect(container).toHaveTextContent('shop');
  });
});
