package com.batch.study.springbatch5.flow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
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
public class FlowJobExam2 {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job simpleFlowExam2() throws Exception {
        return new JobBuilder("simpleFlowExam2", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstFlow1())
                .next((flowExamStep3()))
                .on("COMPLETED")
                .to(flowExamStep4())
                .on("COMPLETED")
                .to(flowExamStep5())
                .end()
                .build();
    }

    @Bean
    public Flow firstFlow1() {
        return new FlowBuilder<Flow>("firstFlow1")
                .start(flowExamStep1())
                .next(flowExamStep2())
                .build();
    }
    @Bean
    Step flowExamStep1() {
        return new StepBuilder("flowExamStep1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("===============step1 was excuted!===============");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    Step flowExamStep2() {
        return new StepBuilder("flowExamStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("===============step2 was excuted!===============");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    Step flowExamStep3() {
        return new StepBuilder("flowExamStep3", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("===============step3 was excuted!===============");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    Step flowExamStep4() {
        return new StepBuilder("flowExamStep4", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("===============step4 was excuted!===============");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    Step flowExamStep5() {
        return new StepBuilder("flowExamStep5", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("===============step5 was excuted!===============");
                    throw new RuntimeException("Step5 Exception Haha");
                }, transactionManager)
                .build();
    }
}
