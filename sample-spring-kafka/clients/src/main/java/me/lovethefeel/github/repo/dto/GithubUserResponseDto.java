package me.lovethefeel.github.repo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GithubUserResponseDto {

    String login;

    Long id;

    @JsonProperty("node_id")
    String nodeId;

    @JsonProperty("avatar_url")
    String avatarUrl;

    @JsonProperty("gravatar_id")
    String gravatarId;

    private GithubUserResponseDto() {}
}
