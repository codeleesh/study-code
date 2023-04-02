package me.lovethefeel.github;

import me.lovethefeel.github.repo.dto.GithubUserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "githubClient", url = "https://api.github.com", configuration = GithubFeignClientConfig.class)
public interface GithubClient {

    @GetMapping(value = "/users/{githubId}")
    GithubUserResponseDto getGithubUser(@PathVariable("githubId") String githubId);
}
