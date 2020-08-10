package com.freberg.app.mock;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freberg.app.dao.MovieDao;
import com.freberg.app.json.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class MovieDataTableInitializer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${mock.data.path}")
    private String mockDataPath;

    @Autowired
    private MovieDao movieDao;

    @PostConstruct
    public void init() throws IOException {
        logger.info("Reading in movie data from {}", mockDataPath);
        Optional.ofNullable(new ObjectMapper().readValue(new File(mockDataPath), MovieRepository.class))
                .map(MovieRepository::getMovies)
                .ifPresent(movies -> {
                    logger.info("{} movies read successfully! Persisting to database...", movies.size());
                    movieDao.persist(movies);
                });
    }
}
