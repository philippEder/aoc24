import kotlin.math.abs

fun main() {
    d2p1()
    d2p2()
}


fun d2p1() {
    val input = PuzzleLoader().loadDayTwo()
    val lines = input.split("\r\n")
    val reports = lines.map { it.split(" ").map { s -> s.toInt() } }

    val amountSafe = reports.count { isSave(it) }


    println("here: $amountSafe")
}

fun d2p2() {
    val input = PuzzleLoader().loadDayTwo()
    val lines = input.split("\r\n")
    val reports = lines.map { it.split(" ").map { s -> s.toInt() } }

    val amountSafe = reports.count { isSaveWithToleranceOne(it) }


    println("here: $amountSafe")
}

fun isSaveWithToleranceOne(report: List<Int>): Boolean {

    if (isSave(report))
        return true

    repeat(report.size) {
        val copy = report.toMutableList()
        copy.removeAt(it)
        if (isSave(copy))
            return true
    }

    return false
}


fun isSave(report: List<Int>): Boolean {
    val isAscending = report.sorted() == report
    val isDescending = report.sortedDescending() == report

    val isSorted = isAscending || isDescending


    var isInBounds = true
    report.forEachIndexed { i, current ->
        if ((i+1) <= report.lastIndex) {
            val next = report[i+1]

            val distance = abs(current - next)

            if (distance < 1 || distance > 3) {
                isInBounds = false
            }

        }
    }

    return isSorted && isInBounds
}

