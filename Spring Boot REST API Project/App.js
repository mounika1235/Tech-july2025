import React from 'react';
import { RouterProvider, createBrowserRouter, createRoutesFromElements, Route } from 'react-router';
import MovieList from './components/MovieList';
import BookingForm from './components/BookingForm';
import Navbar from './components/Navbar';
import './styles/App.css';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<Navbar />}>
      <Route index element={<MovieList />} />
      <Route path="book/:movieId" element={<BookingForm />} />
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
