package com.lovethefeel.setter;

import com.lovethefeel.setter.application.AlbumService;
import com.lovethefeel.setter.application.AlbumServiceImpl;
import com.lovethefeel.setter.domain.AlbumRepository;
import com.lovethefeel.setter.dto.AlbumRequest;
import com.lovethefeel.setter.dto.AlbumResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class AlbumServiceImplTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    @DisplayName("책을 등록합니다.")
    @Test
    void saveAlbumTest() {
        // given
        final String albumName = "Let It be";
        final AlbumRequest albumRequest = AlbumRequest.from(albumName);

        // when
        final AlbumResponse albumResponse = albumService.saveAlbum(albumRequest);

        // then
        assertThat(albumResponse.getName()).isEqualTo(albumName);
    }

    @DisplayName("영화 저장시 NullPointerException 발생합니디.")
    @Test
    void saveAlbum_exception() {
        // givenD
        final AlbumServiceImpl albumService = new AlbumServiceImpl();
        final String albumName = "Let It be";
        final AlbumRequest albumRequest = AlbumRequest.from(albumName);

        // when
        assertThatThrownBy(() -> albumService.saveAlbum(albumRequest))
                .isInstanceOf(NullPointerException.class);
    }
}
