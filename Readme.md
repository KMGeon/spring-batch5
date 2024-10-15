# 스프링 배치 5 (Spring Batch 5)

## 목차

**1. 스프링 배치 5 소개**

- 1.1. 스프링 배치 5의 주요 변경사항

**2. 배치 도메인 이해**

- 2.1. Job
- 2.2. Step
- 2.3. Flow
- 2.4. Split과 멀티 스레드 처리

**3. Job, Step, Flow 구현**
- 3.1. Job 구성
- 3.2. Step 구성
- 3.3. Flow 구성
- 3.4 @Value, @Scope, Parameter

**4. Chunk 기반 처리**
- 4.1. Chunk 개념
- 4.2. ItemReader
- 4.2.1. FlatFileItemReader
- 4.2.2. XMLItemReader
- 4.2.3. JsonItemReader
- 4.2.4. 데이터베이스 ItemReader
  - CursorItemReader
  - PagingItemReader (JdbcPagingItemReader, JpaPagingItemReader, MyBatisPagingItemReader)
- 4.3. ItemProcessor
- 4.3.1. 기본 ItemProcessor
- 4.3.2. CompositeItemProcessor
- 4.3.3. ClassifierCompositeItemProcessor
- 4.4. ItemWriter

**5. 반복 및 오류 제어**
- 5.1. Repeat
- 5.2. FaultTolerant
- 5.3. Skip
- 5.4. Retry

**6. 멀티 스레드 프로세싱**

**7. 이벤트 리스너**

**8. 테스트 및 운영**
   8.1. 배치 작업 테스트
   8.2. 운영 환경 설정 및 모니터링

**9. 스케줄러**

