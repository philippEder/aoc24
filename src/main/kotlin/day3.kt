

fun main() {
    d3p1()
    d3p2()
}

fun d3p1() {
    val input = PuzzleLoader().loadDayThree()
    val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
    val matches = regex.findAll(input)

    var sum = 0

    matches.forEach {
        val split = it.value.split(",")
        val x = split[0].removePrefix("mul(").toInt()
        val y = split[1].removeSuffix(")").toInt()

        sum += x*y
    }

    println("here: $sum")

}

fun d3p2() {
    val input = PuzzleLoader().loadDayThree()
    val regex = Regex("(mul\\(\\d{1,3},\\d{1,3}\\))|(don't\\(\\))|(do\\(\\))")

    val matches = regex.findAll(input)

    var sum = 0
    var doIt = true

    matches.forEach {
        if (it.value == "don't()")
            doIt = false

        if (it.value == "do()") {
            doIt = true
        }

        if (doIt && it.value != "don't()" && it.value != "do()") {
            val split = it.value.split(",")
            val x = split[0].removePrefix("mul(").toInt()
            val y = split[1].removeSuffix(")").toInt()

            sum += x*y
        }

    }

    println("here: $sum")

}