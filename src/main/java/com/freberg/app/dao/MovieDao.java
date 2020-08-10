package com.freberg.app.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.freberg.app.entity.Movie;
import org.springframework.stereotype.Component;

import static com.freberg.app.entity.FieldNameConstants.ACTORS;
import static com.freberg.app.entity.FieldNameConstants.DIRECTOR;
import static com.freberg.app.entity.FieldNameConstants.GENRES;
import static com.freberg.app.entity.FieldNameConstants.IMDB_ID;
import static com.freberg.app.entity.FieldNameConstants.LANGUAGES;
import static com.freberg.app.entity.FieldNameConstants.PLOT;
import static com.freberg.app.entity.FieldNameConstants.TITLE;

@Component
public class MovieDao extends AbstractDao<Movie> {

    private static final String[] SEARCHABLE_FIELDS = {ACTORS, DIRECTOR, PLOT, TITLE};

    public MovieDao() {
        super(Movie.class);
    }

    public Movie findMovieById(String id) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<Movie> root = criteriaQuery.from(clazz);

        Predicate predicate = criteriaBuilder.equal(root.get(IMDB_ID), id);

        criteriaQuery.select(root);
        criteriaQuery.where(predicate);
        return getSession().createQuery(criteriaQuery).getResultList().stream()
                           .findFirst()
                           .orElse(null);
    }

    public List<Movie> findMoviesByCriteria(String freeText, String genre, String language) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<Movie> root = criteriaQuery.from(clazz);

        List<Predicate> predicates = new ArrayList<>();
        if (freeText != null) {
            Stream.of(SEARCHABLE_FIELDS)
                  .map(field -> criteriaBuilder.like(root.get(field), withWildCards(freeText)))
                  .reduce(criteriaBuilder::or)
                  .ifPresent(predicates::add);
        }
        if (genre != null) {
            predicates.add(criteriaBuilder.isMember(genre, root.get(GENRES)));
        }
        if (language != null) {
            predicates.add(criteriaBuilder.isMember(language, root.get(LANGUAGES)));
        }
        Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        criteriaQuery.select(root);
        criteriaQuery.where(predicate);
        return getSession().createQuery(criteriaQuery).getResultList();
    }

    private static String withWildCards(String searchQuery) {
        return "%" + searchQuery + "%";
    }
}
