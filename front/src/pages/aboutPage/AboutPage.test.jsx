import React from 'react';

import { MemoryRouter } from 'react-router-dom';

import { render } from '@testing-library/react';

import AboutPage from './AboutPage';

test('HomePage', () => {
  render((
    <MemoryRouter>
      <AboutPage />
    </MemoryRouter>
  ));
});
