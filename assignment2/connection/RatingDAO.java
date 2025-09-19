package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RatingDAO {

    // Insert or update a rating
    public RatingResponse rateMovie(int userId, int movieId, int ratingValue) {
        RatingResponse res = new RatingResponse();

        if (ratingValue < 1 || ratingValue > 5) {
            res.setStatus("FAILURE");
            res.setMessage("Rating must be between 1 and 5");
            return res;
        }

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO ratings (user_id, movie_id, rating) VALUES (?, ?, ?) "
                       + "ON DUPLICATE KEY UPDATE rating = VALUES(rating)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, movieId);
            ps.setInt(3, ratingValue);
            ps.executeUpdate();

            Rating rating = new Rating();
            rating.setUserId(userId);
            rating.setMovieId(movieId);
            rating.setRating(ratingValue);

            res.setStatus("SUCCESS");
            res.setMessage("Rating saved/updated successfully");
            res.setRating(rating);
        } catch (Exception e) {
            res.setStatus("FAILURE");
            res.setMessage("Error: " + e.getMessage());
        }

        return res;
    }

    // Get average rating of a movie
    public AverageRatingResponse getAverageMovieRating(int movieId) {
        AverageRatingResponse res = new AverageRatingResponse();

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT AVG(rating) as avgRating FROM ratings WHERE movie_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getDouble("avgRating") > 0) {
                res.setStatus("SUCCESS");
                res.setMessage("Average rating found");
                res.setAverageRating(rs.getDouble("avgRating"));
            } else {
                res.setStatus("FAILURE");
                res.setMessage("No ratings found for this movie");
                res.setAverageRating(null);
            }
        } catch (Exception e) {
            res.setStatus("FAILURE");
            res.setMessage("Error: " + e.getMessage());
        }

        return res;
    }
}
