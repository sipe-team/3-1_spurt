# Benchmark
## Quick Start
```shell
$ ./gradlew jmh
```

```markdown
// build/result/jmh/results.txt
Benchmark                                   Mode  Cnt         Score         Error  Units
SortingUtilsBenchmark.리스트10000개_내림차순_기본정렬  thrpt    5     31727.086 ±    2827.015  ops/s
SortingUtilsBenchmark.리스트10000개_내림차순_삽입정렬  thrpt    5         7.962 ±       0.028  ops/s
SortingUtilsBenchmark.리스트10000개_내림차순_퀵정렬   thrpt    5       627.208 ±      24.761  ops/s
SortingUtilsBenchmark.리스트10000개_랜덤_기본정렬    thrpt    5       896.967 ±     155.907  ops/s
SortingUtilsBenchmark.리스트10000개_랜덤_삽입정렬    thrpt    5        18.934 ±       5.728  ops/s
SortingUtilsBenchmark.리스트10000개_랜덤_퀵정렬     thrpt    5       320.986 ±      64.288  ops/s
SortingUtilsBenchmark.리스트10000개_오름차순_기본정렬  thrpt    5     57141.538 ±   20041.231  ops/s
SortingUtilsBenchmark.리스트10000개_오름차순_삽입정렬  thrpt    5     42210.317 ±    7876.674  ops/s
SortingUtilsBenchmark.리스트10000개_오름차순_퀵정렬   thrpt    5       651.697 ±     111.696  ops/s

SortingUtilsBenchmark.리스트1000개_내림차순_기본정렬   thrpt    5    408006.663 ±   15529.753  ops/s
SortingUtilsBenchmark.리스트1000개_내림차순_삽입정렬   thrpt    5       771.764 ±      21.530  ops/s
SortingUtilsBenchmark.리스트1000개_내림차순_퀵정렬    thrpt    5      7872.022 ±    1328.380  ops/s
SortingUtilsBenchmark.리스트1000개_랜덤_기본정렬     thrpt    5     21184.622 ±    4240.606  ops/s
SortingUtilsBenchmark.리스트1000개_랜덤_삽입정렬     thrpt    5      1738.029 ±     271.379  ops/s
SortingUtilsBenchmark.리스트1000개_랜덤_퀵정렬      thrpt    5      5855.822 ±     133.395  ops/s
SortingUtilsBenchmark.리스트1000개_오름차순_기본정렬   thrpt    5    677063.863 ±   21872.524  ops/s
SortingUtilsBenchmark.리스트1000개_오름차순_삽입정렬   thrpt    5    588792.362 ±   94372.894  ops/s
SortingUtilsBenchmark.리스트1000개_오름차순_퀵정렬    thrpt    5      7856.970 ±    1341.914  ops/s

SortingUtilsBenchmark.리스트100개_내림차순_기본정렬    thrpt    5   3703630.616 ±   31512.195  ops/s
SortingUtilsBenchmark.리스트100개_내림차순_삽입정렬    thrpt    5    125199.163 ±   34309.013  ops/s
SortingUtilsBenchmark.리스트100개_내림차순_퀵정렬     thrpt    5     85039.758 ±   12262.847  ops/s
SortingUtilsBenchmark.리스트100개_랜덤_기본정렬      thrpt    5    391702.992 ±   59176.896  ops/s
SortingUtilsBenchmark.리스트100개_랜덤_삽입정렬      thrpt    5    249833.944 ±    3904.056  ops/s
SortingUtilsBenchmark.리스트100개_랜덤_퀵정렬       thrpt    5     79899.094 ±    2666.067  ops/s
SortingUtilsBenchmark.리스트100개_오름차순_기본정렬    thrpt    5   5834882.535 ±  923656.088  ops/s
SortingUtilsBenchmark.리스트100개_오름차순_삽입정렬    thrpt    5   6043506.799 ±  924888.591  ops/s
SortingUtilsBenchmark.리스트100개_오름차순_퀵정렬     thrpt    5     96848.448 ±    1338.825  ops/s

SortingUtilsBenchmark.리스트10개_내림차순_기본정렬     thrpt    5  22863767.238 ± 1080411.257  ops/s
SortingUtilsBenchmark.리스트10개_내림차순_삽입정렬     thrpt    5  11268855.448 ± 1971788.596  ops/s
SortingUtilsBenchmark.리스트10개_내림차순_퀵정렬      thrpt    5    953783.438 ±  176997.146  ops/s
SortingUtilsBenchmark.리스트10개_랜덤_기본정렬       thrpt    5  11857075.704 ± 2057393.840  ops/s
SortingUtilsBenchmark.리스트10개_랜덤_삽입정렬       thrpt    5  14279706.319 ± 1995747.806  ops/s
SortingUtilsBenchmark.리스트10개_랜덤_퀵정렬        thrpt    5    953404.390 ±   43914.435  ops/s
SortingUtilsBenchmark.리스트10개_오름차순_기본정렬     thrpt    5  35167084.811 ± 3802554.251  ops/s
SortingUtilsBenchmark.리스트10개_오름차순_삽입정렬     thrpt    5  47259248.874 ± 9581183.523  ops/s
SortingUtilsBenchmark.리스트10개_오름차순_퀵정렬      thrpt    5   1175051.630 ±   39875.163  ops/s
```

## 성능 측정과 JMH

### 성능 측정
보통 성능 측정을 진행한다고 하면 일반적으로 부하 테스트를 많이 생각하는데요.
- 대표적으로 nGrinder, jMeter, k6 같은 툴을 이용해서 애플리케이션에 부하를 주고 처리량 및 병목 지점을 확인하게 됩니다.

하지만 위 방법의 경우 애플리케이션을 통째로 실행하고 통합적인 기능에 대한 성능을 측정하는 방법입니다.

단위 기능에 대한 성능 측정 관점에서 고려했을 때에는 너무 비싼 비용을 지불하여 테스트를 수행하게 됩니다.

> 그렇다면 단위 기능에 대한 성능 측정은 어떻게 하는 게 좋을까?

간단하게 진행한다면 `System.currentTimeMillis()` 또는 Spring에서 제공하는 `StopWatch`를 이용하여 메서드의 수행시간을 측정해본 경험이 있을 것이라 생각됩니다.
- 하지만 이는 **단순히 메서드가 한번 호출됐을 때 시간을 측정을 하는 방법**으로 성능에 대해 오인(테스트 순간 디바이스의 CPU에 영향이 있는 경우)할 수 있는 가능성이 존재합니다.
- 또한 이를 방지하기 위해 여러 번 호출하고 개발자가 직접 호출한 시간들을 집계한다면 생산성이 많이 떨어질 것 입니다.

<br>

### JMH (Java Microbenchmark Harness)
> https://github.com/openjdk/jmh

그래서 이러한 단위 기능에 대한 성능 측정을 위한 도구, 흔히 벤치마크 도구라 불리는 툴들이 존재하고 JVM환경에서는 JMH를 많이 사용합니다.

#### 사용 방법
크게 세 가지 방법이 존재합니다.
1. 별도 애플리케이션에서 main 함수로 실행하는 방법 (추천 X)
2. jmh 플러그인과 디펜던시를 추가하여 실행하는 방법 (추천 O)
3. jmh 디펜던시를 추가하고 테스트 디렉토리 및 코드에서 실행하는 방법 (추천 O)

1번 방법의 경우 간단한 코드라면 복붙해서 테스트가 가능하겠지만, 일반적으로 여러 클래스를 별도 환경에서 복사해서 가져오는 건 매우 귀찮은 일입니다. 또한 벤치마크 코드를 소스코드 레포지터리에 함께 보관할 수 없기 때문에 추후 구성원들이 재측정 시 접근할 때 제약이 있습니다.

2, 3번의 경우 소스 코드 레포지터리에 함께 보관하고, 정의한 클래스를 바로 임포트해서 사용할 수 있기 때문에 편리하게 사용이 가능합니다.
- 2번 같은 경우 `java-test-fixtures` 플러그인을 사용할 때 처럼 `src`, `test` 외에 별도 디렉토리(`jmh`)를 만들고, 그곳에서 실행하는 것을 의미합니다.

#### 디펜던시 추가
```
dependencies {
    implementation "org.openjdk.jmh:jmh-core:1.37"
    implementation "org.openjdk.jmh:jmh-generator-annprocess:1.37"
}
```

<br>

#### 예시 코드
```java

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3, time = 500, timeUnit = MILLISECONDS)
@Measurement(iterations = 5, time = 1000, timeUnit = MILLISECONDS)
@OutputTimeUnit(MILLISECONDS)
@Fork(1)
public class BenchmarkTest {

      ...
     
      @Benchmark
      public void doBenchmark() {
          doSomething()
      }
    
      ...
}

```

주요 어노테이션을 아래와 같습니다.

- `@State`: 벤치마크에 사용되어지는 Argument의 적용 범위 설정
    - Thread(쓰레드별 각자 상태), Group(쓰레드 그룹별 각자 상태), Benchmark(벤치마크 대상 모든 쓰레드가 동일한 상태 객체 공유)

- `@BenchmarkMode`: 처리량(Throughput), 평균 시간(AverageTime) 등 측정할 메트릭 설정

- `@Measurement`: 실행 당 측정 횟수 지정

- `@OutputTimeUnit`: 출력 시간의 단위

- `@Fork`: 실행 횟수

- `@Benchmark`: 측정 대상