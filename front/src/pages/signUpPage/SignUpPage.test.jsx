import React from 'react';

import { MemoryRouter } from 'react-router-dom';

import { render } from '@testing-library/react';

import SignUpPage from './SignUpPage';

test('SignUpPage', () => {
  render((
    <MemoryRouter>
      <SignUpPage />
    </MemoryRouter>
  ));
});
