package com.lovethefeel.springboot.tasklets;

import com.lovethefeel.springboot.dto.SlackChatBotRequestDto;
import com.lovethefeel.springboot.dto.SlackChatBotResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class SlackBotTaskletConfig implements Tasklet {

    final String targetUrl = "타겟URL";
    final String channel = "슬랙채널명";
    final String authorization = "Bearer " + "슬랙토큰명";

    private RestOperations restTemplate = new RestTemplate();

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // log.info("executed tasklet !!");
        // reader
        SlackChatBotRequestDto slackChatBotRequestDto = SlackChatBotRequestDto.builder()
                .channel(channel)
                .text("I love you")
                .build();

        // processor
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", authorization);
        HttpEntity<Object> httpEntity = new HttpEntity<>(slackChatBotRequestDto, httpHeaders);
        ResponseEntity<SlackChatBotResponseDto> entity = restTemplate.exchange(targetUrl, HttpMethod.POST, httpEntity, SlackChatBotResponseDto.class);

        // writer
        return RepeatStatus.FINISHED;
    }
}
