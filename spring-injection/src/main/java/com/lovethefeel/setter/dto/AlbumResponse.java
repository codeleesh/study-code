package com.lovethefeel.setter.dto;

import com.lovethefeel.setter.domain.Album;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class AlbumResponse {
    private Long id;
    private String name;

    public static AlbumResponse of(final Long id, final String name) {
        return new AlbumResponse(id, name);
    }

    public static AlbumResponse from(final Album album) {
        return new AlbumResponse(album.getId(), album.getName());
    }
}
