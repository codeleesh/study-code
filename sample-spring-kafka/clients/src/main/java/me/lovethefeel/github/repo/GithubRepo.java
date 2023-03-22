package me.lovethefeel.github.repo;

import me.lovethefeel.github.GithubFeignClientConfig;
import me.lovethefeel.github.repo.dto.GithubUserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "githubClient", url = "https://api.github.com", configuration = GithubFeignClientConfig.class)
public interface GithubRepo {

    @GetMapping(value = "/users/{githubId}")
    GithubUserResponseDto getGithubUser(@PathVariable("githubId") String githubId);

}
