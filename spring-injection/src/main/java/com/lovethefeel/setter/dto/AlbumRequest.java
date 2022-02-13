package com.lovethefeel.setter.dto;

import com.lovethefeel.setter.domain.Album;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class AlbumRequest {
    private String name;

    public static AlbumRequest from(final String name) {
        return new AlbumRequest(name);
    }

    public Album toEntity() {
        return Album.from(name);
    }
}
