package me.lovethefeel.github;

import me.lovethefeel.github.repo.dto.GithubUserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class GithubClientTest {

    @Autowired
    private GithubClient githubClient;

    @Test
    void getGithubUser() {

        final GithubUserResponseDto response = githubClient.getGithubUser("codeleesh");

        assertAll(
                () -> assertThat(response).isNotNull()
        );
    }
}