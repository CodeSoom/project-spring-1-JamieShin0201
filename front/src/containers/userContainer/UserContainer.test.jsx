import React from 'react';

import { render } from '@testing-library/react';

import UserContainer from './UserContainer';

describe('UserContainer', () => {
  function rendersUserContainer() {
    return render(<UserContainer />);
  }

  it('renders edit, back buttons', () => {
    const { getByText } = rendersUserContainer();

    expect(getByText('Back')).not.toBeNull();
    expect(getByText('Edit')).not.toBeNull();
  });

  it('renders inputs', () => {
    const { getByPlaceholderText } = rendersUserContainer();

    expect(getByPlaceholderText('email')).not.toBeNull();
    expect(getByPlaceholderText('name')).not.toBeNull();
    expect(getByPlaceholderText('password')).not.toBeNull();
    expect(getByPlaceholderText('confirm password')).not.toBeNull();
  });
});
