import React from 'react';

import { render } from '@testing-library/react';

import { MemoryRouter } from 'react-router-dom';

import context from 'jest-plugin-context';

import App from './App';

describe('App', () => {
  function renderApp({ path }) {
    return render(
      <MemoryRouter initialEntries={[path]}>
        <App />
      </MemoryRouter>,
    );
  }

  context('with path /', () => {
    it('renders the home page', () => {
      const { container } = renderApp({ path: '/' });

      expect(container).toHaveTextContent('유일한, 단 하나의 : SOLE');
    });
  });

  context('with path /products', () => {
    it('renders the products page', () => {
      const { container } = renderApp({ path: '/products' });

      expect(container).toHaveTextContent('wallet');
    });
  });
});
