package team.sipe.spurt.practice.benchmark

object SortingUtilsSteps {

    fun `Int형_리스트_오름차순_생성`(size: Int): List<Int> {
        return (1..size).toList()
    }

    fun `Int형_리스트_내림차순_생성`(size: Int): List<Int> {
        return (size downTo 1).toList()
    }

    fun `Int형_리스트_랜덤_생성`(size: Int): List<Int> {
        return (1..size).shuffled()
    }
}