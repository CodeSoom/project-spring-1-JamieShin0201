import React from 'react';

import { render } from '@testing-library/react';

import SliderContainer from './SliderContainer';

describe('SliderContainer', () => {
  function rendersSliderContainer() {
    return render(<SliderContainer />);
  }

  it('renders images', () => {
    const { container } = rendersSliderContainer();

    expect(container.innerHTML).toContain('<img');
  });
});
