package com.sat.tmf.movietkt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sat.tmf.movietkt.entities.Movie;

@Repository
public class MovieDao extends GenericDao<Movie, Integer> {

    public MovieDao() {
        super(Movie.class);
    }

    /**
     * Search movies by title or genre.
     */
    public List<Movie> searchMovies(String keyword) {
        Session session = getSession();
        Query<Movie> query = session.createQuery(
            "from Movie where lower(title) like :kw or lower(genre) like :kw order by title", Movie.class);
        query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
        return query.list();
    }

    /**
     * Find all distinct languages available.
     */
    public List<String> findAllLanguages() {
        return getSession().createQuery(
                "select distinct language from Movie where language is not null", String.class)
                .list();
    }
}
