import React from 'react';
import { RouterProvider, createBrowserRouter, createRoutesFromElements, Route } from 'react-router';
import movielist from './components/movielist';
import bookingform from './components/bookingform';
import navbar from './components/navbar';
import './styles/App.css';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<navbar />}>
      <Route index element={<movielist />} />
      <Route path="book/:movieId" element={<bookingform />} />
    </Route>
  )
);

function App() {
  return (
    <div className="App">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;

