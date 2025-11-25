
import React, { useState, useMemo } from "react";
import { createRoot } from "react-dom/client";


function h(type, props, ...children) {
  const flat = children.flat(Infinity).map(c => (typeof c === "number" ? String(c) : c));
  return React.createElement(type, props, ...flat);
}


const MOVIES = [
  {
    id: "m1",
    title: "Phantom Night",
    genre: "Thriller",
    rating: 4.4,
    length: "2h 10m",
    image: "https://images.unsplash.com/photo-1534777414234-8ec3f7f0c5de?auto=format&fit=crop&w=1000&q=60",
    description: "A dark, gripping thriller about secrets, lies and one long night."
  },
  {
    id: "m2",
    title: "Haunted Echoes",
    genre: "Horror",
    rating: 4.0,
    length: "1h 50m",
    image: "https://images.unsplash.com/photo-1517604931442-4b8e3a1d8f6c?auto=format&fit=crop&w=1000&q=60",
    description: "When the abandoned theatre re-opens, strange echoes begin to haunt the town."
  },
  {
    id: "m3",
    title: "City of Lights",
    genre: "Drama",
    rating: 4.7,
    length: "2h 20m",
    image: "https://images.unsplash.com/photo-1524985069026-dd778a71c7b4?auto=format&fit=crop&w=1000&q=60",
    description: "A moving story of ambition, music and second chances."
  },
  {
    id: "m4",
    title: "Space Run",
    genre: "Sci-Fi",
    rating: 4.2,
    length: "2h 5m",
    image: "https://images.unsplash.com/photo-1446776811953-b23d57bd21aa?auto=format&fit=crop&w=1000&q=60",
    description: "Fast-paced space chase as colonists fight to survive across the void."
  }
];

// --- Header/Nav component
function Header({ page, setPage }) {
  return h("header", { className: "app-header" },
    h("div", { className: "brand" },
      h("div", { className: "logo" }, "MB"),
      h("div", null, h("div", { style: { fontWeight: 800 } }, "MovieBook"), h("div", { style: { fontSize: 12, color: "var(--muted)" } }, "Book your seats"))
    ),
    h("nav", { className: "main-nav" },
      ["Home", "Movies", "About", "Contact"].map(name =>
        h("a", {
          key: name,
          className: page === name ? "nav-active" : "",
          onClick: () => setPage(name)
        }, name)
      )
    )
  );
}

// --- Search + Filters UI
function SearchFilters({ search, setSearch, genre, setGenre, minRating, setMinRating }) {
  return h("div", { className: "hero" },
    h("h1", null, "Book Your Favorite Movie"),
    h("div", { className: "search-bar" },
      h("input", {
        placeholder: "Search movies by title or genre...",
        value: search,
        onInput: (e) => setSearch(e.target.value)
      }),
      h("button", { onClick: () => {} }, "Search")
    ),
    h("div", { style: { marginTop: 12, display: "flex", gap: 8, alignItems: "center" } },
      h("div", { className: "select" }, "Genre:"),
      h("select", {
        value: genre,
        onChange: (e) => setGenre(e.target.value),
        className: "select"
      },
        h("option", { value: "" }, "All"),
        h("option", { value: "Thriller" }, "Thriller"),
        h("option", { value: "Horror" }, "Horror"),
        h("option", { value: "Drama" }, "Drama"),
        h("option", { value: "Sci-Fi" }, "Sci-Fi")
      ),
      h("div", { className: "select" }, "Min Rating:"),
      h("select", {
        value: minRating,
        onChange: (e) => setMinRating(Number(e.target.value)),
        className: "select"
      },
        h("option", { value: 0 }, "Any"),
        h("option", { value: 4 }, "4+"),
        h("option", { value: 4.5 }, "4.5+")
      )
    )
  );
}

// --- Movie Card
function MovieCard({ movie, onOpen }) {
  return h("div", { className: "movie-card" },
    h("div", { className: "movie-thumb", style: { backgroundImage: `url(${movie.image})` } }),
    h("div", { className: "movie-body" },
      h("h3", null, movie.title),
      h("p", null, `${movie.genre} • ${movie.length} • ${movie.rating} ★`),
      h("p", null, movie.description),
      h("div", { className: "btn-row" },
        h("button", { className: "btn primary", onClick: () => onOpen(movie) }, "View & Book"),
        h("button", { className: "btn ghost", onClick: () => alert("Added to favorites (demo)") }, "❤ Favorite")
      )
    )
  );
}

// --- List of Movies
function MovieList({ movies, onOpen }) {
  return h("div", { className: "movies-grid" },
    movies.map(m => h(MovieCard, { key: m.id, movie: m, onOpen }))
  );
}

// --- Movie Detail + booking initiation
function MovieDetail({ movie, onBack, onStartBooking }) {
  const showtimes = ["10:00 AM", "1:30 PM", "4:45 PM", "8:00 PM"];
  return h("div", { className: "page" },
    h("div", { className: "movie-detail" },
      h("div", { className: "detail-card" },
        h("div", { className: "movie-thumb", style: { height: 340, backgroundImage: `url(${movie.image})` } }),
        h("div", { style: { marginTop: 12 } },
          h("h3", null, movie.title),
          h("p", null, `${movie.genre} • ${movie.length} • ${movie.rating} ★`),
          h("p", null, movie.description),
        ),
        h("div", null,
          h("div", { style: { marginTop: 12, fontWeight: 700 } }, "Select Showtime"),
          h("div", { className: "showtimes" },
            showtimes.map(st =>
              h("button", { className: "btn ghost", onClick: () => onStartBooking(movie, st) }, st)
            )
          )
        ),
        h("div", { style: { marginTop: 12 } },
          h("button", { className: "btn primary", onClick: onBack }, "Back to list")
        )
      ),
      h("div", { className: "detail-card" },
        h("h3", null, "About the film"),
        h("p", null, "Detailed synopsis, cast & crew (demo text)."),
        h("ul", null,
          h("li", null, "Director: Jane Doe"),
          h("li", null, "Cast: A, B, C"),
          h("li", null, "Language: English")
        )
      )
    )
  );
}

function SeatSelector({ taken = [], selected = [], onToggle }) {
  
  const seats = [];
  const rows = 6, cols = 8;
  for (let r = 0; r < rows; r++) {
    for (let c = 0; c < cols; c++) {
      const id = `${String.fromCharCode(65 + r)}${c + 1}`; // A1..F8
      seats.push(id);
    }
  }

  return h("div", null,
    h("div", { style: { marginBottom: 8, color: "var(--muted)" } }, "Click seats to select. Grey = taken, red = selected."),
    h("div", { className: "seat-grid" },
      seats.map(s => {
        const isTaken = taken.includes(s);
        const isSelected = selected.includes(s);
        const cls = "seat" + (isTaken ? " taken" : "") + (isSelected ? " selected" : "");
        return h("div", {
          key: s,
          className: cls,
          onClick: () => { if (!isTaken) onToggle(s); }
        }, s);
      })
    )
  );
}


function BookingPage({ movie, showtime, onCancel, onConfirm }) {
  const [selectedSeats, setSelectedSeats] = React.useState([]);
  // sample taken seats
  const taken = ["A1", "A2", "B3", "C5"];

  const toggleSeat = (s) => {
    setSelectedSeats(prev => prev.includes(s) ? prev.filter(x => x !== s) : [...prev, s]);
  };

  const pricePerSeat = 200;
  const total = selectedSeats.length * pricePerSeat;

  return h("div", { className: "page" },
    h("div", { className: "movie-detail" },
      h("div", { className: "detail-card" },
        h("h3", null, `Booking - ${movie.title}`),
        h("p", null, `${showtime} • ${movie.length}`),
        h("div", null,
          h(SeatSelector, { taken, selected: selectedSeats, onToggle: toggleSeat })
        ),
        h("div", { style: { marginTop: 12 } },
          h("p", null, `Selected seats: ${selectedSeats.join(", ") || "None"}`),
          h("p", null, `Total: ₹${total}`)
        ),
        h("div", { style: { marginTop: 12, display: "flex", gap: 10 } },
          h("button", { className: "btn ghost", onClick: onCancel }, "Cancel"),
          h("button", { className: "btn primary", onClick: () => onConfirm({ seats: selectedSeats, total }) }, "Proceed to Payment")
        )
      ),
      h("div", { className: "detail-card" },
        h("h4", null, "Payment summary"),
        h("p", null, movie.title),
        h("p", null, `Showtime: ${showtime}`)
      )
    )
  );
}

function PaymentPage({ booking, onBack, onDone }) {
  const [name, setName] = React.useState("");
  const [card, setCard] = React.useState("");

  const handlePay = () => {
    if (!name || !card || booking.seats.length === 0) {
      alert("Enter name, card and select seats.");
      return;
    }

    onDone({ success: true, orderId: "ORD" + Math.floor(Math.random() * 90000 + 10000) });
  };

  return h("div", { className: "page" },
    h("div", { className: "detail-card" },
      h("h3", null, "Payment"),
      h("div", { className: "payment-form" },
        h("input", { className: "control", placeholder: "Full name", value: name, onInput: e => setName(e.target.value) }),
        h("input", { className: "control", placeholder: "Card number (demo)", value: card, onInput: e => setCard(e.target.value) }),
        h("p", null, `Seats: ${booking.seats.join(", ")}`),
        h("p", null, `Amount: ₹${booking.total}`),
        h("div", { style: { display: "flex", gap: 8 } },
          h("button", { className: "btn ghost", onClick: onBack }, "Back"),
          h("button", { className: "btn primary", onClick: handlePay }, "Pay Now")
        )
      )
    )
  );
}

function Confirmation({ result, onHome }) {
  return h("div", { className: "page" },
    h("div", { className: "detail-card" },
      result.success ? h("div", null,
        h("h3", null, "Payment Successful ✅"),
        h("p", null, `Order ID: ${result.orderId}`),
        h("p", null, "Your booking is confirmed (demo)."),
        h("button", { className: "btn primary", onClick: onHome }, "Back to Home")
      ) : h("div", null,
        h("h3", null, "Payment Failed"),
        h("button", { className: "btn ghost", onClick: onHome }, "Back to Home")
      )
    )
  );
}


// --- Main App
function AppRoot() {
  const [page, setPage] = useState("Home"); // Home, Movies, About, Contact
  const [search, setSearch] = useState("");
  const [genre, setGenre] = useState("");
  const [minRating, setMinRating] = useState(0);

  const [selectedMovie, setSelectedMovie] = useState(null);
  const [bookingInfo, setBookingInfo] = useState(null);
  const [paymentResult, setPaymentResult] = useState(null);
  const [currentShowtime, setCurrentShowtime] = useState(null);

  // filtered movies
  const filtered = useMemo(() => {
    return MOVIES.filter(m => {
      if (genre && m.genre !== genre) return false;
      if (m.rating < minRating) return false;
      if (search && !(`${m.title} ${m.genre}`).toLowerCase().includes(search.toLowerCase())) return false;
      return true;
    });
  }, [search, genre, minRating]);

  // Page navigation internal; when user clicks nav, reset details if needed
  const openMovie = (movie) => {
    setSelectedMovie(movie);
    setPage("Movies");
    // show detail view
    setPage("MovieDetail");
  };

  // start booking flow
  const startBooking = (movie, showtime) => {
    setSelectedMovie(movie);
    setCurrentShowtime(showtime);
    setPage("Booking");
  };

  const handleConfirmBooking = (payPayload) => {
    setBookingInfo(payPayload);
    setPage("Payment");
  };

  const handlePaymentDone = (res) => {
    setPaymentResult(res);
    setPage("Confirmation");
  };

  const goHome = () => {
    setPage("Home");
    setSelectedMovie(null);
    setBookingInfo(null);
    setPaymentResult(null);
    setCurrentShowtime(null);
    setSearch("");
    setGenre("");
    setMinRating(0);
  };

  return h("div", { style: { display: "flex", flexDirection: "column", minHeight: "100vh" } },
    h(Header, { page, setPage }),
    // hero or small header area
    h("div", { className: "container" },
      // show home hero when home
      page === "Home" && h(SearchFilters, { search, setSearch, genre, setGenre, minRating, setMinRating }),

      // Movies listing
      (page === "Movies" || page === "Home") && h("div", null,
        h("div", { className: "filters" },
          h("div", { className: "chip" }, `${filtered.length} movies found`),
          h("div", { style: { marginLeft: "auto" } }, // duplicate search small
            h("input", {
              placeholder: "quick search...",
              value: search,
              onInput: (e) => setSearch(e.target.value),
              style: { padding: "8px 10px", borderRadius: 8, border: "1px solid rgba(255,255,255,0.03)", background: "transparent", color: "inherit" }
            })
          )
        ),
        h(MovieList, { movies: filtered, onOpen: openMovie })
      ),

      // Movie detail page
      page === "MovieDetail" && selectedMovie && h(MovieDetail, {
        movie: selectedMovie,
        onBack: () => setPage("Movies"),
        onStartBooking: startBooking
      }),

      // Booking page
      page === "Booking" && selectedMovie && h(BookingPage, {
        movie: selectedMovie,
        showtime: currentShowtime,
        onCancel: () => setPage("MovieDetail"),
        onConfirm: handleConfirmBooking
      }),

      // Payment page
      page === "Payment" && bookingInfo && h(PaymentPage, {
        booking: bookingInfo,
        onBack: () => setPage("Booking"),
        onDone: handlePaymentDone
      }),

      // Confirmation
      page === "Confirmation" && paymentResult && h(Confirmation, { result: paymentResult, onHome: goHome }),

      // About/Contact pages
      page === "About" && h("div", { className: "page" }, h("div", { className: "detail-card" }, h("h3", null, "About"), h("p", null, "Demo movie booking SPA built with React (no JSX)."))),
      page === "Contact" && h("div", { className: "page" }, h("div", { className: "detail-card" }, h("h3", null, "Contact"), h("p", null, "contact@moviebook.demo")))
    ),

    h("footer", { className: "app-footer" }, "© 2025 MovieBook — demo SPA")
  );
}

// mount
const root = createRoot(document.getElementById("root"));
root.render(h(AppRoot));
