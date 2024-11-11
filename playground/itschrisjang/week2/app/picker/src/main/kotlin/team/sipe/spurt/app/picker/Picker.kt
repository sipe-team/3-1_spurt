package team.sipe.spurt.app.picker

import team.sipe.spurt.app.picker.Picker.pickRandom
import team.sipe.spurt.app.picker.Picker.printResult
import java.util.*

fun main() {
    val candidates: List<String> = mutableListOf(
        "우재",
        "희동",
        "준용",
        "영학",
        "지예",
        "준환",
        "상준",
        "정화"
    )
    val targetSize = 1

    val picked: List<String> = pickRandom(candidates, targetSize)
    printResult(picked)
}

object Picker {
    fun <T> pickRandom(candidates: List<T>, amount: Int): List<T> {
        val copy = ArrayList(candidates)
        copy.shuffle()
        return copy.subList(0, amount)
    }

    fun <T> printResult(picked: List<T>) {
        val joinString = picked.stream().map { obj: T -> obj.toString() }.toList()
        println(
            """
                축하합니다 🚀: ${java.lang.String.join(", ", joinString)}
            """.trimIndent()
        )
    }
}