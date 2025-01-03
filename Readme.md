# 스프링 배치 5 (Spring Batch 5)

### 목차
1. [스프링 배치 5 소개](#1-스프링-배치-5-소개)
2. [배치 도메인 이해](#2-배치-도메인-이해)
3. [Job, Step, Flow 구현](#3-job-step-flow-구현)
4. [Chunk 기반 처리](#4-chunk-기반-처리)
5. [반복 및 오류 제어](#5-반복-및-오류-제어)
6. [멀티 스레드 프로세싱](#6-멀티-스레드-프로세싱)
7. [이벤트 리스너](#7-이벤트-리스너)
8. [배치 테스트 전략 및 운영](#8-테스트-및-운영)
9. [스케줄러](#9-스케줄러)

---

### Info
1.  [스프링 배치 5.2.1](https://spring.io/projects/spring-batch)
2. [기록보다 기억을](https://jojoldu.tistory.com/324)
3. [배치 블로그](https://cheese10yun.github.io/spring-batch-basic)

### 1. 스프링 배치 5 소개
- [1.1. 스프링 배치 5의 주요 변경사항](docs/1.스프링배치.md)

### [2. 배치 도메인 이해](docs/2.배치도메인이해.md)
- 2.1. Job
    - JobInstance
    - JobParameter
    - JobExecution
- 2.2. Step
    - StepExecution
    - StepContribution
    - ExecutionContext

[//]: # (todo : 작성하기)
- 2.4. Split과 멀티 스레드 처리

### [3. Job, Step, Flow 구현](docs/3.Job,Step,Flow.md)
- 3.1. Job 구성
- 3.2. Step 구성
- 3.3. Flow 구성
    - Status
    - Transition (on, stop, fail, end, stopAndRestart)
    - JobExecutionDecider
- 3.4. 스코프와 파라미터
    - @JobScope
    - @StepScope
    - @Value

### 4. Chunk 기반 처리
- 4.1. Chunk 개념
- 4.2. ItemReader
    - 4.2.1. FlatFileItemReader
    - 4.2.2. XMLItemReader
    - 4.2.3. JsonItemReader
    - 4.2.4. 데이터베이스 ItemReader
        - CursorItemReader
        - PagingItemReader
            - JdbcPagingItemReader
            - JpaPagingItemReader
            - MyBatisPagingItemReader
- 4.3. ItemProcessor
    - 4.3.1. 기본 ItemProcessor
    - 4.3.2. CompositeItemProcessor
    - 4.3.3. ClassifierCompositeItemProcessor
- 4.4. ItemWriter

### 5. 반복 및 오류 제어
- 5.1. Repeat
- 5.2. FaultTolerant
- 5.3. Skip
- 5.4. Retry

### 6. 멀티 스레드 프로세싱
- 6.1. 멀티 스레드 Step
- 6.2. 병렬 Steps
- 6.3. 비동기 ItemProcessor/ItemWriter

### 7. 이벤트 리스너
- 7.1. JobExecutionListener
- 7.2. StepExecutionListener
- 7.3. ChunkListener
- 7.4. ItemReadListener
- 7.5. ItemProcessListener
- 7.6. ItemWriteListener

### 8. 테스트 및 운영
- 8.1. 배치 작업 테스트
    - 8.1.1. 단위 테스트
    - 8.1.2. 통합 테스트
- 8.2. 운영 환경 설정 및 모니터링
    - 8.2.1. 로깅 전략
    - 8.2.2. 성능 모니터링
    - 8.2.3. 오류 처리 및 알림

### 9. 스케줄러
- 9.1. Jenkins 
- 9.2. Jenkins 스케줄러
- 9.3. 스프링 내장 스케줄러
- 9.4. Quartz 스케줄러 연동

