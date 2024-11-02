package team.sipe.spurt.practice.benchmark

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SortingUtilsTests {

    @Test
    fun `리스트를 기본 정렬 알고리즘을 통해 오름차순으로 정렬한다`() {
        // given
        val list = listOf(5, 4, 3)

        // when
        val actual = SortingUtils.default(list)

        // then
        assertThat(actual).containsExactly(3, 4, 5)
    }

    @Test
    fun `리스트를 삽입 정렬 알고리즘을 통해 오름차순으로 정렬한다`() {
        // given
        val list = listOf(5, 4, 3)

        // when
        val actual = SortingUtils.insertion(list)

        // then
        assertThat(actual).containsExactly(3, 4, 5)
    }

    @Test
    fun `리스트를 퀵 정렬 알고리즘을 통해 오름차순으로 정렬한다`() {
        // given
        val list = listOf(5, 4, 3)

        // when
        val actual = SortingUtils.quick(list)

        // then
        assertThat(actual).containsExactly(3, 4, 5)
    }
}