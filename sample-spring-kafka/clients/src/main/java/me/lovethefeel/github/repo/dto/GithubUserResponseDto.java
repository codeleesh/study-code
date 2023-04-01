package me.lovethefeel.github.repo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GithubUserResponseDto {

    String login;

    Long id;

    String node_id;

    String avatar_url;

    String gravatar_id;
}
