package com.freberg.app.controller;

import java.util.List;

import com.freberg.app.dao.MovieDao;
import com.freberg.app.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @GetMapping
    public Movie getMovie(String imdbId) {
        return movieDao.findMovieById(imdbId);
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movieDao.findAll();
    }

    @GetMapping("/movies/search")
    public List<Movie> getMovies(@RequestParam(required = false) String freeText,
                                 @RequestParam(required = false) String genre,
                                 @RequestParam(required = false) String language) {
        return movieDao.findMoviesByCriteria(freeText, genre, language);
    }
}
