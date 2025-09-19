package DTOS;

public class RatingResponse {
    private String status;
    private String message;
    private Rating rating;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Rating getRating() { return rating; }
    public void setRating(Rating rating) { this.rating = rating; }
}
