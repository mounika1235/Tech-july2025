import React from 'react';
import { Link, Outlet } from 'react-router';

const Navbar = () => {
  return (
    <>
      <nav className="navbar">
        <div className="nav-container">
          <Link to="/" className="nav-logo">🎬 MovieTicket</Link>
          <div className="nav-menu">
            <Link to="/" className="nav-link">Movies</Link>
            <Link to="/profile" className="nav-link">Profile</Link>
          </div>
        </div>
      </nav>
      <div className="container">
        <Outlet />
      </div>
    </>
  );
};

export default navbar;