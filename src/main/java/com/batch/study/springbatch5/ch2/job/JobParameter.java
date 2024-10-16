package com.batch.study.springbatch5.ch2.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Date;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobParameter {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final JobLauncher jobLauncher;

    @Bean
    public Job jobParameterTest() throws Exception {
        return new JobBuilder("jobParameterTest", jobRepository)
                .start(simpleStepJobParameterTest())
                .build();
    }

    @Bean
    Step simpleStepJobParameterTest() {
        return new StepBuilder("simpleStepJobParameterTest", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    // JobParameters 가져오기
                    JobParameters jobParameters = contribution.getStepExecution().getJobParameters();
                    String param1 = jobParameters.getString("param1");
                    String param2 = jobParameters.getString("param2");
                    Date param3 = jobParameters.getDate("param3");

                    Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();
                    Object object1 = jobParameters1.get("param1");
                    Object object2 = jobParameters1.get("param2");
                    Object object3 = jobParameters1.get("param3");

                    log.info("param1: {}, param2: {}, param3: {}", param1, param2, param3);
                    log.info("step1 was executed!");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}

//@Component
//@RequiredArgsConstructor
//public class JobParameterApplicationRunner implements ApplicationRunner {
//
//    private final JobLauncher jobLauncher;
//    private final Job jobParameterTest;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("param1", "value1")
//                .addString("param2", "123")
//                .addDate("param3", new Date())
//                .toJobParameters();
//
//        jobLauncher.run(jobParameterTest, jobParameters);
//    }
//}
