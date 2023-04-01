package me.lovethefeel.github;

import me.lovethefeel.github.repo.dto.GithubUserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GithubClientTest {

    @Autowired
    private GithubClient githubClient;

    @Test
    void getGithubUser() {

        GithubUserResponseDto responseDto = githubClient.getGithubUser("codeleesh");
    }

}