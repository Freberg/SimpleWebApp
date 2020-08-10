package com.freberg.app.controller;


import java.util.List;

import com.freberg.app.SimpleWebApp;
import com.freberg.app.entity.Movie;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleWebApp.class)
public class MovieControllerIntegrationTest {

    @Autowired
    private MovieController movieController;

    @Test
    public void checkMovieRepositoryNotEmpty() {
        List<Movie> movies = movieController.getMovies();
        Assertions.assertFalse(movies.isEmpty());
    }

    @Test
    public void checkSpecificMovieIsPresent() {
        Movie movie = movieController.getMovie("tt0095016");
        Assertions.assertNotNull(movie);
    }

    @Test
    public void checkSpecificMovieIsAbsent() {
        Movie movie = movieController.getMovie("NOT_AN_ID");
        Assertions.assertNull(movie);
    }

    @Test
    public void checkMovieByGenreNotEmpty() {
        List<Movie> movies = movieController.getMovies(null, "Action", null);
        Assertions.assertFalse(movies.isEmpty());
    }

    @Test
    public void checkMovieByGenreEmpty() {
        List<Movie> movies = movieController.getMovies(null, "NOT_A_GENRE", null);
        Assertions.assertTrue(movies.isEmpty());
    }

    @Test
    public void checkMovieByLanguageNotEmpty() {
        List<Movie> movies = movieController.getMovies(null, null, "English");
        Assertions.assertFalse(movies.isEmpty());
    }

    @Test
    public void checkMovieByLanguageEmpty() {
        List<Movie> movies = movieController.getMovies(null, null, "NOT_A_LANGUAGE");
        Assertions.assertTrue(movies.isEmpty());
    }

    @Test
    public void checkFreeTextNotEmpty() {
        List<Movie> movies = movieController.getMovies("Bruce", null, null);
        Assertions.assertFalse(movies.isEmpty());
    }

    @Test
    public void checkFreeTextEmpty() {
        List<Movie> movies = movieController.getMovies("NOT_IN_SEARCHABLE:FIELDS", null, null);
        Assertions.assertTrue(movies.isEmpty());
    }
}
