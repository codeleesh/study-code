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
public class MovieRequest {
    private String name;

    public static MovieRequest from(final String name) {
        return new MovieRequest(name);
    }

    public Movie toEntity() {
        return Movie.from(name);
    }
}
