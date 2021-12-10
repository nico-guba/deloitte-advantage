import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';

describe('The homepage', () => {

  test('renders learn react link', () => {
    render(<App />);
    const linkElement = screen.getAllByText(/Buy online/i);
    expect(linkElement.length).toStrictEqual(4);
  });

});
