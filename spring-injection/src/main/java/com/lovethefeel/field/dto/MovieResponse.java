package com.lovethefeel.field.dto;

import com.lovethefeel.field.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class MovieResponse {
    private Long id;
    private String name;

    public static MovieResponse of(final Long id, final String name) {
        return new MovieResponse(id, name);
    }

    public static MovieResponse from(final Movie movie) {
        return new MovieResponse(movie.getId(), movie.getName());
    }
}
