import React from 'react';

import { MemoryRouter } from 'react-router-dom';

import { render } from '@testing-library/react';

import UserPage from './UserPage';

test('UserPage', () => {
  render((
    <MemoryRouter>
      <UserPage />
    </MemoryRouter>
  ));
});
