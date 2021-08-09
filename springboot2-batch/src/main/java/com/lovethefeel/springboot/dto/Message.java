package com.lovethefeel.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Message {
    private String bot_id;
    private String type;
    private String text;
    private String user;
    private String ts;
    private String team;
    private BotProfile bot_profile;
}
