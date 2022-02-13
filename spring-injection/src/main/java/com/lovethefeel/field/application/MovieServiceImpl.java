package com.lovethefeel.field.application;

import com.lovethefeel.field.domain.Movie;
import com.lovethefeel.field.domain.MovieRepository;
import com.lovethefeel.field.dto.MovieRequest;
import com.lovethefeel.field.dto.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public MovieResponse saveBook(final MovieRequest movieRequest) {
        final Movie saveMovie = movieRepository.save(movieRequest.toEntity());
        return MovieResponse.from(saveMovie);
    }
}
