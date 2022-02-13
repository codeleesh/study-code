package com.lovethefeel.setter.application;

import com.lovethefeel.setter.dto.AlbumRequest;
import com.lovethefeel.setter.dto.AlbumResponse;

public interface AlbumService {
    AlbumResponse saveAlbum(final AlbumRequest albumRequest);
}
