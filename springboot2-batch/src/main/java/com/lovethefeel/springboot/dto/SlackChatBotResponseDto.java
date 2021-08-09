package com.lovethefeel.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SlackChatBotResponseDto {
    private boolean ok;
    private String channel;
    private String ts;
    private Message message;
    private String warning;
    private ResponseMetadata response_metadata;

}
