import React from 'react';

import { render } from '@testing-library/react';

import CategoriesContainer from './CategoriesContainer';

describe('CategoriesContainer', () => {
  it('renders categories', () => {
    const { container } = render(<CategoriesContainer />);

    expect(container).toHaveTextContent('Card wallet');
    expect(container).toHaveTextContent('Bag');
    expect(container).toHaveTextContent('Home deco');
  });
});
