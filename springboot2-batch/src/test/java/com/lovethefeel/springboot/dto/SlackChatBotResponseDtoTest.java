package com.lovethefeel.springboot.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SlackChatBotResponseDtoTest {

    @Test
    public void testGetJsonStringToObject() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String stringJson = "{\"ok\":true,\"channel\":\"C027FP81Z0A\",\"ts\":\"1625837942.000100\",\"message\":{\"bot_id\":\"B027FTPB00J\",\"type\":\"message\",\"text\":\"I love you\",\"user\":\"U027UHMG7NV\",\"ts\":\"1625837942.000100\",\"team\":\"T01K58Q6M5H\",\"bot_profile\":{\"id\":\"B027FTPB00J\",\"deleted\":false,\"name\":\"SolutionLunchBot\",\"updated\":1625751417,\"app_id\":\"A027CP065FF\",\"icons\":{\"image_36\":\"https://a.slack-edge.com/80588/img/plugins/app/bot_36.png\",\"image_48\":\"https://a.slack-edge.com/80588/img/plugins/app/bot_48.png\",\"image_72\":\"https://a.slack-edge.com/80588/img/plugins/app/service_72.png\"},\"team_id\":\"T01K58Q6M5H\"}},\"warning\":\"missing_charset\",\"response_metadata\":{\"warnings\":[\"missing_charset\"]}}";

        SlackChatBotResponseDto slackChatBotResponseDto = mapper.readValue(stringJson, SlackChatBotResponseDto.class);

        Assertions.assertEquals(slackChatBotResponseDto.isOk(), true);
        Assertions.assertEquals(slackChatBotResponseDto.getChannel(), "C027FP81Z0A");
        Assertions.assertEquals(slackChatBotResponseDto.getTs(), "1625837942.000100");
        Assertions.assertNotNull(slackChatBotResponseDto.getMessage());
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getBot_id(), "B027FTPB00J");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getType(), "message");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getText(), "I love you");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getUser(), "U027UHMG7NV");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getTs(), "1625837942.000100");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getTeam(), "T01K58Q6M5H");
        Assertions.assertNotNull(slackChatBotResponseDto.getMessage().getBot_profile());
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getBot_profile().getId(), "B027FTPB00J");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getBot_profile().isDeleted(), false);
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getBot_profile().getName(), "SolutionLunchBot");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getBot_profile().getUpdated(), "1625751417");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getBot_profile().getApp_id(), "A027CP065FF");
        Assertions.assertNotNull(slackChatBotResponseDto.getMessage().getBot_profile().getIcons());
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getBot_profile().getIcons().getImage_36(), "https://a.slack-edge.com/80588/img/plugins/app/bot_36.png");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getBot_profile().getIcons().getImage_48(), "https://a.slack-edge.com/80588/img/plugins/app/bot_48.png");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getBot_profile().getIcons().getImage_72(), "https://a.slack-edge.com/80588/img/plugins/app/service_72.png");
        Assertions.assertEquals(slackChatBotResponseDto.getMessage().getBot_profile().getTeam_id(), "T01K58Q6M5H");
        Assertions.assertEquals(slackChatBotResponseDto.getWarning(), "missing_charset");
        Assertions.assertNotNull(slackChatBotResponseDto.getResponse_metadata());
        Assertions.assertEquals(slackChatBotResponseDto.getResponse_metadata().getWarnings().length, 1);
    }
}
