-- USERS TABLE
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    email varchar(20) not null unique,
    password varchar(20) not null
);
-- Insert Users
INSERT INTO users (name) VALUES ('Alice');
INSERT INTO users (name) VALUES ('Bob');
-- MOVIES TABLE
CREATE TABLE movies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL
);
-- Insert Movies
INSERT INTO movies (title) VALUES ('Inception');
INSERT INTO movies (title) VALUES ('Interstellar');
-- RATINGS TABLE
CREATE TABLE ratings (
    user_id INT NOT NULL,
    movie_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    PRIMARY KEY (user_id, movie_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);
-- Insert Ratings
INSERT INTO ratings (user_id, movie_id, rating) VALUES (1, 1, 5); INSERT INTO ratings (user_id, movie_id, rating) VALUES (2, 1, 4);
INSERT INTO ratings (user_id, movie_id, rating) VALUES (1, 2, 3); 

SELECT AVG(rating) AS avg_rating
FROM ratings
WHERE movie_id = 1;

