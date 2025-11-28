package com.sat.tmf.movietkt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sat.tmf.movietkt.entities.Movie;
import com.sat.tmf.movietkt.entities.MovieReview;

@Repository
public class MovieReviewDao extends GenericDao<MovieReview, Integer> {

    public MovieReviewDao() {
        super(MovieReview.class);
    }

    public List<MovieReview> findByMovie(Movie movie) {
        Session session = getSession();
        Query<MovieReview> q = session.createQuery(
                "from MovieReview where movie.id = :mid order by createdAt desc", MovieReview.class);
        q.setParameter("mid", movie.getId());
        return q.list();
    }

    public Double findAverageRating(Movie movie) {
        Double avg = (Double) getSession()
                .createQuery("select avg(r.rating) from MovieReview r where r.movie.id = :mid")
                .setParameter("mid", movie.getId())
                .uniqueResult();
        return avg != null ? avg : 0.0;
    }
}
