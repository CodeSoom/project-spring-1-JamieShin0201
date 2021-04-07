import React from 'react';

import { render } from '@testing-library/react';

import ProductDetail from './ProductDetail';

import formatMoney from '../../utils/util';

describe('ProductDetail', () => {
  function rendersProductDetail(product) {
    return render(<ProductDetail product={product} />);
  }

  it('renders product', () => {
    const product = {
      name: '만두 지갑',
      originalPrice: 50000,
      discountedPrice: 40000,
    };

    const { container } = rendersProductDetail(product);

    expect(container).toHaveTextContent(product.name);
    expect(container).toHaveTextContent(formatMoney(product.originalPrice));
    expect(container).toHaveTextContent(formatMoney(product.discountedPrice));
  });
});
