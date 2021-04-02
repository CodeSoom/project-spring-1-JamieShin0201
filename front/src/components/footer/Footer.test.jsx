import React from 'react';

import { render } from '@testing-library/react';

import { MemoryRouter } from 'react-router';

import Footer from './Footer';

describe('Footer', () => {
  it('renders links and business information', () => {
    const { container } = render(
      <MemoryRouter>
        <Footer />
      </MemoryRouter>,
    );

    expect(container).toHaveTextContent('HOME');
    expect(container).toHaveTextContent('AGREEMENT');
  });
});
