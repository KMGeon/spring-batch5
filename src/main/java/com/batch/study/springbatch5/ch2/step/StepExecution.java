package com.batch.study.springbatch5.ch2.step;

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
public class StepExecution {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job simpleStepJob() throws Exception {
        return new JobBuilder("simpleStepJob", jobRepository)
                .start(simpleStepExecutionStep())
                .next(simpleStepExecutionStep_2())
                .build();
    }

    @Bean
    Step simpleStepExecutionStep() throws Exception {
        return new StepBuilder("simpleStepExecutionStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("step1 was excuted!");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
    @Bean
    Step simpleStepExecutionStep_2() throws Exception {
        return new StepBuilder("simpleStepExecutionStep_2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
//                    throw new RuntimeException("exception haha");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
