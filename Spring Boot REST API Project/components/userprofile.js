import React, { useState, useEffect } from 'react';
import { mockAPI, mockMovies, mockTheaters, mockShows } from '../data/mockData';

const UserProfile = () => {
  const [bookings, setBookings] = useState([]);
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchUserData();
  }, []);

  const fetchUserData = async () => {
    try {
      setLoading(true);
      const [userResponse, bookingsResponse] = await Promise.all([
        mockAPI.users.getById(1),
        mockAPI.bookings.getByUser(1)
      ]);

      setUser(userResponse.data);
      setBookings(bookingsResponse.data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching user data:', error);
      setLoading(false);
    }
  };

  const getMovieDetails = (movieId) => {
    return mockMovies.find(movie => movie.id === movieId) || {};
  };

  const getShowDetails = (showId) => {
    return mockShows.find(show => show.id === showId) || {};
  };

  const getTheaterDetails = (theaterId) => {
    return mockTheaters.find(theater => theater.id === theaterId) || {};
  };

  if (loading) return <div className="loading">Loading profile...</div>;
  if (!user) return <div className="error">User not found.</div>;

  return (
    <div className="user-profile">
      <h2>User Profile</h2>

      <div className="user-info">
        <h3>Personal Information</h3>
        <div className="info-grid">
          <div><strong>Username:</strong> {user.username}</div>
          <div><strong>Email:</strong> {user.email}</div>
          <div><strong>First Name:</strong> {user.firstName}</div>
          <div><strong>Last Name:</strong> {user.lastName}</div>
          <div><strong>Phone:</strong> {user.phoneNumber}</div>
        </div>
      </div>

      <div className="bookings-section">
        <h3>Your Bookings</h3>

        {bookings.length === 0 ? (
          <p className="no-bookings">No bookings found.</p>
        ) : (
          <div className="bookings-list">
            {bookings.map(booking => {
              const movie = getMovieDetails(booking.movieId);
              const show = getShowDetails(booking.showId);
              const theater = getTheaterDetails(show.theaterId);

              return (
                <div key={booking.id} className="booking-item">
                  <div className="booking-header">
                    <h4>{movie.title}</h4>
                    <span className="booking-id">Booking #: {booking.id}</span>
                  </div>

                  <div className="booking-details">
                    <div className="detail-row">
                      <span><strong>Theater:</strong> {theater.name} ({theater.location})</span>
                      <span><strong>Screen:</strong> {theater.screenType}</span>
                    </div>
                    <div className="detail-row">
                      <span><strong>Show Time:</strong> {new Date(show.showTime).toLocaleString()}</span>
                      <span><strong>Tickets:</strong> {booking.numberOfTickets}</span>
                    </div>
                    <div className="detail-row">
                      <span><strong>Total Price:</strong> ${booking.totalPrice}</span>
                      <span><strong>Booking Date:</strong> {new Date(booking.bookingDate).toLocaleDateString()}</span>
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
        )}
      </div>
    </div>
  );
};

export default userprofile;