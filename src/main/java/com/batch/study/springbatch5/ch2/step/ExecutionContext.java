package com.batch.study.springbatch5.ch2.step;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ExecutionContext {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job executionContextTestJob() {
        return new JobBuilder("executionContextTestJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

    @Bean
    Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    // Job ExecutionContext에 데이터 추가
                    contribution.getStepExecution().getJobExecution().getExecutionContext().put("jobKey", "job execution context");

                    // Step ExecutionContext에 데이터 추가
                    contribution.getStepExecution().getExecutionContext().put("stepKey", "step1 execution context");

                    log.info("Step1 was executed");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    Step step2() {
        return new StepBuilder("step2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    // Job ExecutionContext에서 데이터 읽기
                    String jobValue = (String) contribution.getStepExecution().getJobExecution().getExecutionContext().get("jobKey");
                    log.info("Job ExecutionContext value from Step2: {}", jobValue);

                    // Step1의 ExecutionContext에서 데이터 읽기 시도 (공유되지 않음)
                    String stepValue = (String) contribution.getStepExecution().getExecutionContext().get("stepKey");
                    log.info("Step ExecutionContext value from Step2: {}", stepValue); // null이 출력됨

                    // Step2의 ExecutionContext에 새로운 데이터 추가
                    contribution.getStepExecution().getExecutionContext().put("stepKey", "step2 execution context");

                    log.info("Step2 was executed");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    Step step3() {
        return new StepBuilder("step3", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    // Job ExecutionContext에서 데이터 읽기
                    String jobValue = (String) contribution.getStepExecution().getJobExecution().getExecutionContext().get("jobKey");
                    log.info("Job ExecutionContext value from Step3: {}", jobValue);

                    // Step2의 ExecutionContext에서 데이터 읽기 시도 (공유되지 않음)
                    String stepValue = (String) contribution.getStepExecution().getExecutionContext().get("stepKey");
                    log.info("Step ExecutionContext value from Step3: {}", stepValue); // null이 출력됨

                    log.info("Step3 was executed");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

}
