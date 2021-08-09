package com.lovethefeel.springboot.jobs;

import com.lovethefeel.springboot.tasklets.SlackBotTaskletConfig;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class SlackBotJobConfig {

    @Bean
    public Job slackChatBotJobConfig(JobBuilderFactory jobBuilderFactory,
                                 Step slackChatBotJobStep) {
        return jobBuilderFactory.get("slackChatBotJobConfig")
                .preventRestart()
                .start(slackChatBotJobStep)
                .build();
    }

    @Bean
    public Step slackChatBotJobStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("slackChatBotJobStep")
                .tasklet(new SlackBotTaskletConfig()) // Tasklet 설정
                .build();
    }
}
