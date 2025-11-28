import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router';
import { moviesAPI, showsAPI, bookingsAPI } from '../services/api';

const BookingForm = () => {
  const { movieId } = useParams();
  const navigate = useNavigate();
  const [movie, setMovie] = useState(null);
  const [shows, setShows] = useState([]);
  const [selectedShow, setSelectedShow] = useState('');
  const [numberOfTickets, setNumberOfTickets] = useState(1);
  const [userDetails, setUserDetails] = useState({
    name: '',
    email: '',
    phone: ''
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    if (movieId) {
      fetchMovieAndShows();
    }
  }, [movieId]);

  const fetchMovieAndShows = async () => {
    try {
      setLoading(true);
      setError('');

      console.log('🟡 Fetching movie and shows for movie ID:', movieId);
      const [movieResponse, showsResponse] = await Promise.all([
        moviesAPI.getById(movieId),
        showsAPI.getByMovie(movieId)
      ]);

      console.log('🟢 Movie data:', movieResponse.data);
      console.log('🟢 Shows data:', showsResponse.data);

      setMovie(movieResponse.data);
      setShows(showsResponse.data);

      setLoading(false);
    } catch (error) {
      console.error('🔴 Error fetching data:', error);
      setError('Failed to load movie details. Please try again.');
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!selectedShow) {
      alert('Please select a show time.');
      return;
    }

    try {
      const selectedShowData = shows.find(s => s.id === parseInt(selectedShow));
      const bookingData = {
        userId: 1, // In real app, get from auth context
        movieId: parseInt(movieId),
        showId: parseInt(selectedShow),
        numberOfTickets: numberOfTickets,
        totalPrice: numberOfTickets * (selectedShowData?.pricePerTicket || 15),
        bookingDate: new Date().toISOString()
      };

      console.log('🟡 Submitting booking:', bookingData);
      const response = await bookingsAPI.create(bookingData);
      console.log('🟢 Booking response:', response);

      alert('🎉 Booking confirmed successfully!');
      // Navigate back to movies list
      navigate('/');
    } catch (error) {
      console.error('🔴 Error creating booking:', error);
      alert('Error creating booking. Please try again.');
    }
  };

  if (loading) return <div className="loading">Loading...</div>;
  if (!movie) return <div className="error">Movie not found.</div>;

  return (
    <div className="booking-form">
      <h2>Book Tickets for {movie.title}</h2>

      {error && <div className="error-message">{error}</div>}

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Select Show:</label>
          <select
            value={selectedShow}
            onChange={(e) => setSelectedShow(e.target.value)}
            required
          >
            <option value="">Choose a show time</option>
            {shows.map(show => (
              <option key={show.id} value={show.id}>
                {new Date(show.showTime).toLocaleString()} -
                {show.availableSeats} seats available -
                ${show.pricePerTicket}/ticket
              </option>
            ))}
          </select>
          {shows.length === 0 && !loading && (
            <p className="no-shows">No show times available for this movie.</p>
          )}
        </div>

        <div className="form-group">
          <label>Number of Tickets:</label>
          <input
            type="number"
            min="1"
            max="10"
            value={numberOfTickets}
            onChange={(e) => setNumberOfTickets(parseInt(e.target.value))}
            required
          />
        </div>

        <div className="form-section">
          <h3>Your Details</h3>

          <div className="form-group">
            <label>Full Name:</label>
            <input
              type="text"
              value={userDetails.name}
              onChange={(e) => setUserDetails({ ...userDetails, name: e.target.value })}
              required
            />
          </div>

          <div className="form-group">
            <label>Email:</label>
            <input
              type="email"
              value={userDetails.email}
              onChange={(e) => setUserDetails({ ...userDetails, email: e.target.value })}
              required
            />
          </div>

          <div className="form-group">
            <label>Phone Number:</label>
            <input
              type="tel"
              value={userDetails.phone}
              onChange={(e) => setUserDetails({ ...userDetails, phone: e.target.value })}
              required
            />
          </div>
        </div>

        <button type="submit" className="submit-btn" disabled={shows.length === 0}>
          {shows.length === 0 ? 'No Shows Available' : 'Confirm Booking'}
        </button>

        <button
          type="button"
          onClick={() => navigate('/')}
          style={{
            width: '100%',
            marginTop: '1rem',
            padding: '0.75rem',
            backgroundColor: '#95a5a6',
            color: 'white',
            border: 'none',
            borderRadius: '4px',
            cursor: 'pointer'
          }}
        >
          Back to Movies
        </button>
      </form>
    </div>
  );
};

export default bookingform;