import React from 'react';

import { render } from '@testing-library/react';

import Product from './Product';

import formatMoney from '../../utils/util';

describe('Product', () => {
  function rendersProduct(product) {
    return render(<Product product={product} />);
  }

  it('renders product', () => {
    const product = {
      name: '만두 지갑',
      originalPrice: 50000,
      discountedPrice: 40000,
      imageUrl: 'url',
    };

    const { container } = rendersProduct(product);

    expect(container).toHaveTextContent(product.name);
    expect(container).toHaveTextContent(formatMoney(product.originalPrice));
    expect(container).toHaveTextContent(formatMoney(product.discountedPrice));
  });
});
