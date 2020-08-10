package com.freberg.app.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.freberg.app.entity.Movie;

public class MovieRepository {

    @JsonProperty("Movies")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
