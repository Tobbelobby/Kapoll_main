import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './Components/App';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});

/*
* <BrowserRouter>
    <Routers>
    * <Router path="/" element={<HomePage/>}/>
    * <Router path="/login" element={<LoginPage/>}/>
    </Routers>
   </BrowserRouter>
*/