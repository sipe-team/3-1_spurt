package team.sipe.spurt.practice.benchmark

import org.openjdk.jmh.annotations.*
import team.sipe.spurt.practice.benchmark.SortingUtilsSteps.Int형_리스트_내림차순_생성
import team.sipe.spurt.practice.benchmark.SortingUtilsSteps.Int형_리스트_오름차순_생성
import team.sipe.spurt.practice.benchmark.SortingUtilsSteps.Int형_리스트_랜덤_생성
import java.util.concurrent.TimeUnit


@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 2, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
class SortingUtilsBenchmark {

    /**
     * 10
     */

    private val asc10 = Int형_리스트_오름차순_생성(10)
    private val desc10 = Int형_리스트_내림차순_생성(10)
    private val rand10 = Int형_리스트_랜덤_생성(10)

    @Benchmark
    fun `리스트10개_오름차순_기본정렬`() {
        SortingUtils.default(asc10)
    }

    @Benchmark
    fun `리스트10개_오름차순_삽입정렬`() {
        SortingUtils.insertion(asc10)
    }

    @Benchmark
    fun `리스트10개_오름차순_퀵정렬`() {
        SortingUtils.quick(asc10)
    }

    @Benchmark
    fun `리스트10개_내림차순_기본정렬`() {
        SortingUtils.default(desc10)
    }

    @Benchmark
    fun `리스트10개_내림차순_삽입정렬`() {
        SortingUtils.insertion(desc10)
    }

    @Benchmark
    fun `리스트10개_내림차순_퀵정렬`() {
        SortingUtils.quick(desc10)
    }

    @Benchmark
    fun `리스트10개_랜덤_기본정렬`() {
        SortingUtils.default(rand10)
    }

    @Benchmark
    fun `리스트10개_랜덤_삽입정렬`() {
        SortingUtils.insertion(rand10)
    }

    @Benchmark
    fun `리스트10개_랜덤_퀵정렬`() {
        SortingUtils.quick(rand10)
    }


    /**
     * 100
     */

    private val asc100 = Int형_리스트_오름차순_생성(100)
    private val desc100 = Int형_리스트_내림차순_생성(100)
    private val rand100 = Int형_리스트_랜덤_생성(100)

    @Benchmark
    fun `리스트100개_오름차순_기본정렬`() {
        SortingUtils.default(asc100)
    }

    @Benchmark
    fun `리스트100개_오름차순_삽입정렬`() {
        SortingUtils.insertion(asc100)
    }

    @Benchmark
    fun `리스트100개_오름차순_퀵정렬`() {
        SortingUtils.quick(asc100)
    }

    @Benchmark
    fun `리스트100개_내림차순_기본정렬`() {
        SortingUtils.default(desc100)
    }

    @Benchmark
    fun `리스트100개_내림차순_삽입정렬`() {
        SortingUtils.insertion(desc100)
    }

    @Benchmark
    fun `리스트100개_내림차순_퀵정렬`() {
        SortingUtils.quick(desc100)
    }

    @Benchmark
    fun `리스트100개_랜덤_기본정렬`() {
        SortingUtils.default(rand100)
    }

    @Benchmark
    fun `리스트100개_랜덤_삽입정렬`() {
        SortingUtils.insertion(rand100)
    }

    @Benchmark
    fun `리스트100개_랜덤_퀵정렬`() {
        SortingUtils.quick(rand100)
    }

    /**
     * 1000
     */

    private val asc1000 = Int형_리스트_오름차순_생성(1000)
    private val desc1000 = Int형_리스트_내림차순_생성(1000)
    private val rand1000 = Int형_리스트_랜덤_생성(1000)

    @Benchmark
    fun `리스트1000개_오름차순_기본정렬`() {
        SortingUtils.default(asc1000)
    }

    @Benchmark
    fun `리스트1000개_오름차순_삽입정렬`() {
        SortingUtils.insertion(asc1000)
    }

    @Benchmark
    fun `리스트1000개_오름차순_퀵정렬`() {
        SortingUtils.quick(asc1000)
    }

    @Benchmark
    fun `리스트1000개_내림차순_기본정렬`() {
        SortingUtils.default(desc1000)
    }

    @Benchmark
    fun `리스트1000개_내림차순_삽입정렬`() {
        SortingUtils.insertion(desc1000)
    }

    @Benchmark
    fun `리스트1000개_내림차순_퀵정렬`() {
        SortingUtils.quick(desc1000)
    }

    @Benchmark
    fun `리스트1000개_랜덤_기본정렬`() {
        SortingUtils.default(rand1000)
    }

    @Benchmark
    fun `리스트1000개_랜덤_삽입정렬`() {
        SortingUtils.insertion(rand1000)
    }

    @Benchmark
    fun `리스트1000개_랜덤_퀵정렬`() {
        SortingUtils.quick(rand1000)
    }


    /**
     * 10000
     */

    private val asc10000 = Int형_리스트_오름차순_생성(10000)
    private val desc10000 = Int형_리스트_내림차순_생성(10000)
    private val rand10000 = Int형_리스트_랜덤_생성(10000)

    @Benchmark
    fun `리스트10000개_오름차순_기본정렬`() {
        SortingUtils.default(asc10000)
    }

    @Benchmark
    fun `리스트10000개_오름차순_삽입정렬`() {
        SortingUtils.insertion(asc10000)
    }

    @Benchmark
    fun `리스트10000개_오름차순_퀵정렬`() {
        SortingUtils.quick(asc10000)
    }

    @Benchmark
    fun `리스트10000개_내림차순_기본정렬`() {
        SortingUtils.default(desc10000)
    }

    @Benchmark
    fun `리스트10000개_내림차순_삽입정렬`() {
        SortingUtils.insertion(desc10000)
    }

    @Benchmark
    fun `리스트10000개_내림차순_퀵정렬`() {
        SortingUtils.quick(desc10000)
    }

    @Benchmark
    fun `리스트10000개_랜덤_기본정렬`() {
        SortingUtils.default(rand10000)
    }

    @Benchmark
    fun `리스트10000개_랜덤_삽입정렬`() {
        SortingUtils.insertion(rand10000)
    }

    @Benchmark
    fun `리스트10000개_랜덤_퀵정렬`() {
        SortingUtils.quick(rand10000)
    }
}