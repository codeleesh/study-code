package me.lovethefeel.github;

import me.lovethefeel.github.repo.dto.GithubUserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "githubClient", url = "https://api.github.com")
public interface GithubClient {

    @GetMapping(value = "/users/{githubId}")
    GithubUserResponseDto getGithubUser(@PathVariable("githubId") String githubId);
}
