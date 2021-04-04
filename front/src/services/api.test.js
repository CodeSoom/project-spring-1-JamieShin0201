import fetchProducts from './api';

import PRODUCTS from '../../fixtures/products';

describe('api', () => {
  const mockFetch = (data) => {
    global.fetch = jest.fn().mockResolvedValue({
      async json() { return data; },
    });
  };

  describe('fetchProducts', () => {
    beforeEach(() => {
      mockFetch(PRODUCTS);
    });

    it('returns products', async () => {
      const products = await fetchProducts();

      expect(products).toEqual(PRODUCTS);
    });
  });
});

