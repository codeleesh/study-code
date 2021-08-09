package com.lovethefeel.springboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SlackChatBotRequestDto {
    private String channel;
    private String text;

    @Builder
    public SlackChatBotRequestDto(String channel, String text) {
        this.channel = channel;
        this.text = text;
    }
}
