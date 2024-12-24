fun main() {
    d4p1()
    d4p2()
}

fun d4p1() {
    val input = PuzzleLoader().loadDayFour()
    var xmasses = 0

    val horizontals = input.getLines()
    val horizontalsBackwards = horizontals.map { it.reversed() }
    val verticals = input.getColumns()
    val verticalsBackwards = verticals.map { it.reversed() }
    val diagonals = input.getDiagonals()
    val diagonalsBackwards = diagonals.map { it.reversed() }
    val diagonals2 = input.getDiagonals2()
    val diagonals2Backwards = diagonals2.map { it.reversed() }


    horizontals.forEach { xmasses += it.howOftenXmas() }
    horizontalsBackwards.forEach { xmasses += it.howOftenXmas() }
    verticals.forEach { xmasses += it.howOftenXmas() }
    verticalsBackwards.forEach { xmasses += it.howOftenXmas() }
    diagonals.forEach { xmasses += it.howOftenXmas() }
    diagonalsBackwards.forEach { xmasses += it.howOftenXmas() }
    diagonals2.forEach { xmasses += it.howOftenXmas() }
    diagonals2Backwards.forEach { xmasses += it.howOftenXmas() }



    println("here: $xmasses")

}

fun d4p2() {
    val input = PuzzleLoader().loadDayFour()
    var xmasses = 0

    val the2dArray = input.as2dArray()
    the2dArray.forEachWithValidNeighbours { it, left, up, right, down, leftUp, rightUp, leftDown, rightDown ->

        if (it == "a") {
            val diagonal = leftUp + it + rightDown
            val diagonalReversed = diagonal.reversed()
            val diagonal2 = rightUp + it + leftDown
            val diagonal2Reversed = diagonal2.reversed()

            if ((diagonal == "mas" || diagonalReversed == "mas") && (diagonal2 == "mas" || diagonal2Reversed == "mas"))
                xmasses++

        }

    }



    println("here: $xmasses")
}


fun String.howOftenXmas(): Int {
    val regex = Regex("xmas")

    val maches = regex.findAll(lowercase())

    return maches.toList().size
}