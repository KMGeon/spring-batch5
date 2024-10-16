package com.batch.study.springbatch5.ch2.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobExecution {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job simpleJobExecution() throws Exception {
        return new JobBuilder("simpleJobExecution", jobRepository)
                .start(simpleJobExecutionStep_1())
                .next(simpleJobExecutionStep_2())
                .build();
    }

    @Bean
    Step simpleJobExecutionStep_1() throws Exception {
        return new StepBuilder("simpleJobExecutionStep1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("step1 was excuted!");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
    @Bean
    Step simpleJobExecutionStep_2() throws Exception {
        return new StepBuilder("simpleJobExecutionStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    throw new RuntimeException("exception haha");
                }, transactionManager)
                .build();
    }
}
