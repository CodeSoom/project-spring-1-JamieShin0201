import React from 'react';

import { render } from '@testing-library/react';

import LoginFormContainer from './LoginFormContainer';

describe('ProductCreateContainer', () => {
  function rendersLoginFormContainer() {
    return render(<LoginFormContainer />);
  }

  it('renders login, sign up buttons', () => {
    const { getByText } = rendersLoginFormContainer();

    expect(getByText('Login')).not.toBeNull();
    expect(getByText('Sign up')).not.toBeNull();
  });
});
