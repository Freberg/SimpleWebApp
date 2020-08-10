package com.freberg.app.entity;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.freberg.app.json.CommaSeparatedCollectionDeserializer;

@Entity
public class Movie {
    @Column
    @JsonProperty("Title")
    private String title;
    @Column
    @JsonProperty("Year")
    private String year;
    @Column
    @JsonProperty("Rated")
    private String rated;
    @Column
    @JsonProperty("Released")
    private String released;
    @Column
    @JsonProperty("Runtime")
    private String runtime;
    @Column
    @JsonProperty("Genre")
    @JsonDeserialize(using = CommaSeparatedCollectionDeserializer.class)
    @ElementCollection(targetClass=String.class)
    private Collection<String> genres;
    @Column
    @JsonProperty("Director")
    private String director;
    @Column
    @JsonProperty("Writer")
    private String writer;
    @Column
    @JsonProperty("Actors")
    private String actors;
    @Column
    @JsonProperty("Plot")
    private String plot;
    @Column
    @JsonProperty("Language")
    @JsonDeserialize(using = CommaSeparatedCollectionDeserializer.class)
    @ElementCollection(targetClass=String.class)
    private Collection<String> languages;
    @Column
    @JsonProperty("Country")
    private String country;
    @Column
    @JsonProperty("Awards")
    private String awards;
    @Column
    @JsonProperty("Poster")
    private String poster;
    @Column
    @JsonProperty("Metascore")
    private String metascore;
    @Column
    @JsonProperty("imdbRating")
    private Double imdbRating;
    @Column
    @JsonProperty("imdbVotes")
    private Long imdbVotes;
    @Id
    @JsonProperty("imdbID")
    private String imdbId;
    @Column
    @JsonProperty("Type")
    private String type;
    @Column
    @JsonProperty("Response")
    private String response;
}
