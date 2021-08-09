package com.lovethefeel.springboot.schedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SlackBotSchedulerConfig {

    private final Job slackChatBotJobConfig;  // slackChatBotJobConfig
    private final JobLauncher jobLauncher;

    // 5초마다 실행
    @Scheduled(fixedDelay = 5 * 1000L)
    public void executeJob () {
        try {
            jobLauncher.run(
                    slackChatBotJobConfig,
                    new JobParametersBuilder()
                            .addString("datetime", LocalDateTime.now().toString())
                            .toJobParameters()  // job parameter 설정
            );
        } catch (JobExecutionException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
