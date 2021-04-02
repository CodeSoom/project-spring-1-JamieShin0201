import React from 'react';

import { render } from '@testing-library/react';

import HeaderContent from './HeaderContent';

describe('HeaderContent', () => {
  it('renders content', () => {
    const { container } = render(<HeaderContent />);

    expect(container).toHaveTextContent('유일한, 단 하나의 : SOLE');
  });
});
