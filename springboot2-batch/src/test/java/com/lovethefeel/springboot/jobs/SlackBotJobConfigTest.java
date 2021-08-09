package com.lovethefeel.springboot.jobs;

import com.lovethefeel.springboot.dto.SlackChatBotRequestDto;
import com.lovethefeel.springboot.dto.SlackChatBotResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

public class SlackBotJobConfigTest {

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void SlackBotChat_성공() {

        // given
        SlackChatBotRequestDto slackChatBotRequestDto = SlackChatBotRequestDto.builder()
                .channel("채널명")
                .text("I love you")
                .build();

        String targetUrl = "타겟URL";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + "토큰값");
        HttpEntity<Object> httpEntity = new HttpEntity<>(slackChatBotRequestDto, httpHeaders);

        // when
        ResponseEntity<SlackChatBotResponseDto> entity = testRestTemplate.exchange(targetUrl, HttpMethod.POST, httpEntity, SlackChatBotResponseDto.class);

        // then
        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(entity.getBody().isOk(), true);
        Assertions.assertEquals(entity.getBody().getChannel(), "C027FP81Z0A");
        //Assertions.assertEquals(entity.getBody().getTs(), "1625837942.000100");
        Assertions.assertNotNull(entity.getBody().getMessage());
        Assertions.assertEquals(entity.getBody().getMessage().getBot_id(), "B027FTPB00J");
        Assertions.assertEquals(entity.getBody().getMessage().getType(), "message");
        Assertions.assertEquals(entity.getBody().getMessage().getText(), "I love you");
        Assertions.assertEquals(entity.getBody().getMessage().getUser(), "U027UHMG7NV");
        //Assertions.assertEquals(entity.getBody().getMessage().getTs(), "1625837942.000100");
        Assertions.assertEquals(entity.getBody().getMessage().getTeam(), "T01K58Q6M5H");
        Assertions.assertNotNull(entity.getBody().getMessage().getBot_profile());
        Assertions.assertEquals(entity.getBody().getMessage().getBot_profile().getId(), "B027FTPB00J");
        Assertions.assertEquals(entity.getBody().getMessage().getBot_profile().isDeleted(), false);
        Assertions.assertEquals(entity.getBody().getMessage().getBot_profile().getName(), "SolutionLunchBot");
        Assertions.assertEquals(entity.getBody().getMessage().getBot_profile().getUpdated(), "1625751417");
        Assertions.assertEquals(entity.getBody().getMessage().getBot_profile().getApp_id(), "A027CP065FF");
        Assertions.assertNotNull(entity.getBody().getMessage().getBot_profile().getIcons());
        Assertions.assertEquals(entity.getBody().getMessage().getBot_profile().getIcons().getImage_36(), "https://a.slack-edge.com/80588/img/plugins/app/bot_36.png");
        Assertions.assertEquals(entity.getBody().getMessage().getBot_profile().getIcons().getImage_48(), "https://a.slack-edge.com/80588/img/plugins/app/bot_48.png");
        Assertions.assertEquals(entity.getBody().getMessage().getBot_profile().getIcons().getImage_72(), "https://a.slack-edge.com/80588/img/plugins/app/service_72.png");
        Assertions.assertEquals(entity.getBody().getMessage().getBot_profile().getTeam_id(), "T01K58Q6M5H");
        Assertions.assertEquals(entity.getBody().getWarning(), "missing_charset");
        Assertions.assertNotNull(entity.getBody().getResponse_metadata());
        Assertions.assertEquals(entity.getBody().getResponse_metadata().getWarnings().length, 1);
    }
}
