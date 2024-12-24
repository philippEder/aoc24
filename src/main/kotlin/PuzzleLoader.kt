class PuzzleLoader {

    fun loadDayOne() = loadDay(1)
    fun loadDayTwo() = loadDay(2)

    private fun loadDay(day: Int): String {
        return PuzzleLoader::class.java.getResource("/day${day}Input.txt").readText()
    }

}