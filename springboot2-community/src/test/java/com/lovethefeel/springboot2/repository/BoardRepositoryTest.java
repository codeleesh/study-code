package com.lovethefeel.springboot2.repository;

import com.lovethefeel.springboot2.domain.Board;
import com.lovethefeel.springboot2.domain.User;
import com.lovethefeel.springboot2.domain.enums.BoardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BoardRepositoryTest {

    private final String boardTestTitle = "테스트";
    private final String email = "test@gmail.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    public void init() {
        User user = userRepository.save(User.builder()
                .name("havi")
                .password("test")
                .email(email)
                .createdDate(LocalDateTime.now())
                .build());

        boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .subTitle("서브 타이틀")
                .content("콘텐츠")
                .boardType(BoardType.free)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .user(user).build());
    }

    @Test
    public void 제대로_생성되는지_테스트() {
        User user = userRepository.findByEmail(email);
        assertThat("havi").isEqualTo(user.getName());
        assertThat("test").isEqualTo(user.getPassword());
        assertThat(email).isEqualTo(user.getEmail());

        Board board = boardRepository.findByUser(user);
        assertThat(boardTestTitle).isEqualTo(board.getTitle());
        assertThat("서브 타이틀").isEqualTo(board.getSubTitle());
        assertThat("콘텐츠").isEqualTo(board.getContent());
        assertThat(BoardType.free).isEqualTo(board.getBoardType());
    }
}
