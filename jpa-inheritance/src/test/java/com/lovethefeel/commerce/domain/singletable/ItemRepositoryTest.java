package com.lovethefeel.commerce.domain.singletable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @DisplayName("Item 테이블의 책 항목을 저장한다.")
    @Test
    void 책을_생성() {
        final Book book = Book.from("자바의정석", 15000, 1, "LEE", "12312313");
        final Book savedBook = itemRepository.save(book);

        final Book itemBook = (Book) itemRepository.findById(savedBook.getId()).get();

        assertAll(
                () -> assertThat(itemBook.getName()).isEqualTo(book.getName()),
                () -> assertThat(itemBook.getPrice()).isEqualTo(book.getPrice()),
                () -> assertThat(itemBook.getStockQuantity()).isEqualTo(book.getStockQuantity()),
                () -> assertThat(itemBook.getAuthor()).isEqualTo(book.getAuthor()),
                () -> assertThat(itemBook.getIsbn()).isEqualTo(book.getIsbn())
        );
    }

    @DisplayName("Item 테이블의 앨범 항목을 저장한다.")
    @Test
    void 앨범을_생성() {
        final Album album = Album.from("Let It Be", 200000, 1, "비틀즈", "");
        final Album savedAlbum = itemRepository.save(album);

        final Album itemAlbum = (Album) itemRepository.findById(savedAlbum.getId()).get();

        assertAll(
                () -> assertThat(itemAlbum.getName()).isEqualTo(album.getName()),
                () -> assertThat(itemAlbum.getPrice()).isEqualTo(album.getPrice()),
                () -> assertThat(itemAlbum.getStockQuantity()).isEqualTo(album.getStockQuantity()),
                () -> assertThat(itemAlbum.getArtist()).isEqualTo(album.getArtist()),
                () -> assertThat(itemAlbum.getEtc()).isEqualTo(album.getEtc())
        );
    }

    @DisplayName("Item 테이블의 영화 항목을 저장한다.")
    @Test
    void 영화를_생성() {
        final Movie movie = Movie.from("스파이더맨: 노 웨이 홈", 200000, 1, "존 와츠", "톰 홀랜드");
        final Movie savedMovie = itemRepository.save(movie);

        final Movie itemMovie = (Movie) itemRepository.findById(savedMovie.getId()).get();

        assertAll(
                () -> assertThat(itemMovie.getName()).isEqualTo(movie.getName()),
                () -> assertThat(itemMovie.getPrice()).isEqualTo(movie.getPrice()),
                () -> assertThat(itemMovie.getStockQuantity()).isEqualTo(movie.getStockQuantity()),
                () -> assertThat(itemMovie.getActor()).isEqualTo(movie.getActor()),
                () -> assertThat(itemMovie.getDirector()).isEqualTo(movie.getDirector())
        );
    }
}