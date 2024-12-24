import java.lang.IndexOutOfBoundsException

fun String.getLines(): List<String> {
    return split("\r\n")
}

fun String.getColumns(): List<String> {
    val columns = mutableListOf<String>()

    val lines =  getLines()
    val amountColumns = lines[0].length

    repeat(amountColumns) { idx ->
        columns.add(
            lines.map { it[idx] }.joinToString(separator = "")
        )
    }

    return columns
}



fun String.getDiagonals(): List<String> {
    return getDiagonals0(getLines())
}

fun String.getDiagonals2(): List<String> {
    val lines = getLines()
    val linesReversed = lines.map { it.reversed() }
    return getDiagonals0(linesReversed)
}

fun String.as2dArray(splitter: String = ""): List<List<String>> {
    val lines = getLines()
    return lines.map { it.split(splitter) }
}


fun List<List<String>>.forEachWithValidNeighbours(doIt: (it: String,
                                                  left: String,
                                                  up: String,
                                                  right: String,
                                                  down: String,
                                                  leftUp: String,
                                                  rightUp: String,
                                                  leftDown: String,
                                                  rightDown: String) -> Unit) {

        forEachIndexed { outerIdx, outer ->
            outer.forEachIndexed { innerIdx, inner ->
                try {
                    val it = inner.lowercase()
                    val left = outer[innerIdx-1].lowercase()
                    val right = outer[innerIdx+1].lowercase()
                    val up = get(outerIdx-1)[innerIdx].lowercase()
                    val down = get(outerIdx+1)[innerIdx].lowercase()
                    val leftUp = get(outerIdx-1)[innerIdx-1].lowercase()
                    val rightUp = get(outerIdx-1)[innerIdx+1].lowercase()
                    val leftDown = get(outerIdx+1)[innerIdx-1].lowercase()
                    val rightDown = get(outerIdx+1)[innerIdx+1].lowercase()

                    doIt(it, left, up, right, down, leftUp, rightUp, leftDown, rightDown )

                } catch (ex: IndexOutOfBoundsException) {
                    //ignore out of bounds
                }
            }
        }

}


private fun getDiagonals0(lines: List<String>): List<String> {
    val diagonals = mutableMapOf<String, String>()

    repeat(lines.size) { lineIdx ->
        val currentLine = lines[lineIdx]

        currentLine.forEachIndexed { colIndex, value ->
            val diagonalId = if (colIndex >= lineIdx) {
                "d${colIndex-lineIdx}"
            } else {
                "l${lineIdx-colIndex}"
            }

            if (diagonals.containsKey(diagonalId)) {
                diagonals[diagonalId] = "${diagonals[diagonalId]}${currentLine[colIndex]}"
            } else {
                diagonals[diagonalId] = "${currentLine[colIndex]}"
            }

        }

    }

    return diagonals.values.toList()
}


