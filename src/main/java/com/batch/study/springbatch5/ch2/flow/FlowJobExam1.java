package com.batch.study.springbatch5.ch2.flow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.support.state.StepState;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FlowJobExam1 {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job simpleFlowJobExam1() throws Exception {
        return new JobBuilder("simpleFlowJobExam1", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(simpleFlowStep_1())
                .on("COMPLETED")
                .to(simpleFlowStep_2())
                .on("FAILED")
                .to(simpleFlowStep_3())
                .end()
                .build();
    }

    @Bean
    Step simpleFlowStep_1() throws Exception {
        return new StepBuilder("simpleFlowStep_1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("step1 was excuted!");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
    @Bean
    Step simpleFlowStep_2() throws Exception {
        return new StepBuilder("simpleFlowStep_2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("step2 was excuted!");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    Step simpleFlowStep_3() throws Exception {
        return new StepBuilder("simpleFlowStep_3", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("step3 was excuted!");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
