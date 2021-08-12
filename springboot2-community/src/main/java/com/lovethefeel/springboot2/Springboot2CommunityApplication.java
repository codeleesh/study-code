package com.lovethefeel.springboot2;

import com.lovethefeel.springboot2.domain.Board;
import com.lovethefeel.springboot2.domain.User;
import com.lovethefeel.springboot2.domain.enums.BoardType;
import com.lovethefeel.springboot2.repository.BoardRepository;
import com.lovethefeel.springboot2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class Springboot2CommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2CommunityApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) {
		return (args) -> {
			User user = userRepository.save(User.builder()
					.name("havi")
					.password("test")
					.email("havi@gmail.com")
					.createdDate(LocalDateTime.now())
					.build()
			);

			IntStream.rangeClosed(1, 200).forEach(index ->
					boardRepository.save(Board.builder()
							.title("게시글"+index)
							.subTitle("순서"+index)
							.content("콘텐츠")
							.boardType(BoardType.free)
							.createdDate(LocalDateTime.now())
							.updatedDate(LocalDateTime.now())
							.user(user)
							.build())
			);
		};
	}
}
