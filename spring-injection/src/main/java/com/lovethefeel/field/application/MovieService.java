package com.lovethefeel.field.application;

import com.lovethefeel.field.dto.MovieRequest;
import com.lovethefeel.field.dto.MovieResponse;

public interface MovieService {
    MovieResponse saveBook(final MovieRequest movieRequest);
}
