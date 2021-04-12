import React from 'react';

import { render } from '@testing-library/react';

import SignUpFormContainer from './SignUpFormContainer';

describe('SignUpFormContainer', () => {
  function rendersSignUpFormContainer() {
    return render(<SignUpFormContainer />);
  }

  it('renders sign up, back buttons', () => {
    const { getByText } = rendersSignUpFormContainer();

    expect(getByText('Back')).not.toBeNull();
    expect(getByText('Sign up')).not.toBeNull();
  });

  it('renders inputs', () => {
    const { getByPlaceholderText } = rendersSignUpFormContainer();

    expect(getByPlaceholderText('email')).not.toBeNull();
    expect(getByPlaceholderText('name')).not.toBeNull();
    expect(getByPlaceholderText('password')).not.toBeNull();
    expect(getByPlaceholderText('confirm password')).not.toBeNull();
  });
});
