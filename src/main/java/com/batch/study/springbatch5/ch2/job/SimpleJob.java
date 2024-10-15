package com.batch.study.springbatch5.ch2.job;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class SimpleJob {
    private static final Logger logger = LoggerFactory.getLogger(SimpleJob.class);

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;


    @Bean
    public Job simpleJob_1() throws Exception {
        return new JobBuilder("simpleJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(simpleStep_1())
                .build();
    }

    @Bean
    Step simpleStep_1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    logger.info("step1 was excuted!");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
