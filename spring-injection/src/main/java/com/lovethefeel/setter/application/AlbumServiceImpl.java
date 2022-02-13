package com.lovethefeel.setter.application;

import com.lovethefeel.setter.domain.Album;
import com.lovethefeel.setter.domain.AlbumRepository;
import com.lovethefeel.setter.dto.AlbumRequest;
import com.lovethefeel.setter.dto.AlbumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
    private AlbumRepository albumRepository;

    @Autowired
    public void setAlbumRepository(final AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public AlbumResponse saveAlbum(final AlbumRequest albumRequest) {
        final Album saveAlbum = albumRepository.save(albumRequest.toEntity());
        return AlbumResponse.from(saveAlbum);
    }
}
