package com.lovethefeel.field;

import com.lovethefeel.field.application.MovieService;
import com.lovethefeel.field.application.MovieServiceImpl;
import com.lovethefeel.field.dto.MovieRequest;
import com.lovethefeel.field.dto.MovieResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class MovieServiceImplTest {

    @Autowired
    private MovieService movieService;

    @DisplayName("영화를 등록합니다.")
    @Test
    void saveMovieTest() {
        // given
        final String movieName = "아이언맨";
        final MovieRequest movieRequest = MovieRequest.from(movieName);

        // when
        final MovieResponse movieResponse = movieService.saveBook(movieRequest);

        // then
        assertThat(movieResponse.getName()).isEqualTo(movieName);
    }

    @DisplayName("영화 저장시 NullPointerException 발생합니다.")
    @Test
    void saveMovie_exception() {
        // given
        final MovieServiceImpl movieService = new MovieServiceImpl();
        final String movieName = "아이언맨";
        final MovieRequest movieRequest = MovieRequest.from(movieName);

        // when
        assertThatThrownBy(() -> movieService.saveBook(movieRequest))
                .isInstanceOf(NullPointerException.class);
    }
}
