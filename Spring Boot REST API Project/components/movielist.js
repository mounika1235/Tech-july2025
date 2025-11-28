import React, { useState, useEffect } from 'react';
import { Link } from 'react-router';
import { moviesAPI } from '../services/api';

const MovieList = () => {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchMovies();
  }, []);

  const fetchMovies = async () => {
    try {
      setLoading(true);
      setError('');
      console.log('🟡 Fetching movies from backend...');

      const response = await moviesAPI.getAll(0, 10);
      console.log('🟢 Backend response:', response);

      if (response.data && Array.isArray(response.data.content)) {
        setMovies(response.data.content);
        console.log(`🟢 Loaded ${response.data.content.length} movies`);
      } else {
        setError('Unexpected response format from server');
      }

      setLoading(false);
    } catch (error) {
      console.error('🔴 Error fetching movies:', error);
      setError('Failed to load movies. Make sure backend is running on http://localhost:8080');
      setLoading(false);
    }
  };

  if (loading) {
    return <div className="loading">Loading movies...</div>;
  }

  if (error) {
    return (
      <div className="error-message">
        {error}
        <br />
        <button onClick={fetchMovies} style={{ marginTop: '1rem', padding: '0.5rem 1rem' }}>
          Retry
        </button>
      </div>
    );
  }

  return (
    <div className="movie-list">
      <h2>Now Showing</h2>
      <div className="movies-grid">
        {movies.map(movie => (
          <div key={movie.id} className="movie-card">
            <h3>{movie.title}</h3>
            <p><strong>Genre:</strong> {movie.genre}</p>
            <p><strong>Duration:</strong> {movie.duration} minutes</p>
            <p><strong>Rating:</strong> ⭐ {movie.rating}/10</p>
            <p><strong>Language:</strong> {movie.language}</p>
            <p><strong>Director:</strong> {movie.director}</p>
            <p className="description">{movie.description}</p>
            <Link to={`/book/${movie.id}`} className="book-btn">
              Book Tickets
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default movielist;