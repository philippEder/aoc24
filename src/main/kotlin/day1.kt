
fun main() {
    part1()
    part2()
}

//day 1 - part 1
fun part1() {
    val input = PuzzleLoader().loadDayOne()

    val rows = input.split("\r\n")

    val left = rows.map { it.split("   ")[0].toInt() }.sorted()
    val right = rows.map { it.split("   ")[1].toInt() }.sorted()

    val toSum = mutableListOf<Int>()


    left.forEachIndexed { index, currentLeft ->
        val currentRight = right[index]
        val distance = Math.abs(currentLeft - currentRight)
        toSum.add(distance)
    }

    println("here: ${toSum.sum()}")
}


//day 1 - part 2
fun part2() {
    val input = PuzzleLoader().loadDayOne()

    val rows = input.split("\r\n")

    val left = rows.map { it.split("   ")[0].toInt() }.sorted()
    val right = rows.map { it.split("   ")[1].toInt() }.sorted()

    val toSum = mutableListOf<Int>()


    left.forEachIndexed { index, currentLeft ->
        val currentRights = right.filter { it == currentLeft }
        val amountRights = currentRights.size

        val prod = Math.abs(currentLeft * amountRights)
        toSum.add(prod)
    }

    println("here: ${toSum.sum()}")
}