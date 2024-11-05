package team.sipe.spurt.practice.benchmark

/**
 * 정렬 기능을 제공하는 유틸리티 클래스
 */
object SortingUtils {

    /**
     * Dual-Pivot Quicksort
     * - best: O(log n)
     * - average: O(n log n)
     * - worst: O(n^2)
     *
     * cf1. https://www.geeksforgeeks.org/dual-pivot-quicksort/
     * cf2. Java 쪽에서는 기본 타입 정렬은 Dual-Pivot Quicksort를 사용하고, 그 외 객체 형태의 정렬은 Timsort를 사용
     *
     */
    @JvmStatic
    fun <T : Comparable<T>> default(list: List<T>): List<T> {
        return list.sorted()
    }

    /**
     * Insertion Sort
     * - best: O(n)
     * - average: O(n^2)
     * - worst: O(n^2)
     */
    @JvmStatic
    fun <T : Comparable<T>> insertion(list: List<T>): List<T> {
        val mutableList = list.toMutableList()
        for (i in 1 until mutableList.size) {
            val key = mutableList[i]
            var j = i - 1
            while (j >= 0 && mutableList[j] > key) {
                mutableList[j + 1] = mutableList[j]
                j--
            }
            mutableList[j + 1] = key
        }
        return mutableList
    }

    /**
     * Quick Sort
     * - best: O(n log n)
     * - average: O(n log n)
     * - worst: O(n^2)
     *
     * cf. https://www.geeksforgeeks.org/quick-sort/
     */
    @JvmStatic
    fun <T : Comparable<T>> quick(list: List<T>): List<T> {
        if (list.size < 2) return list

        val pivot = list[list.size / 2]
        val equal = list.filter { it == pivot }
        val less = list.filter { it < pivot }
        val greater = list.filter { it > pivot }

        return quick(less) + equal + quick(greater)
    }
}