
fun main() {
    d5p1()
    d5p2()
}

fun d5p1() {
    val input = PuzzleLoader().loadDay5()
    val rules = getRules(input)
    val updates = getUpdates(input)
    val validUpdates = updates.filter { it.isValid(rules) }

    println(validUpdates.sumOf { it.getMiddleNumber() })
}

fun d5p2() {
    val input = PuzzleLoader().loadDay5()
    val rules = getRules(input)
    val updates = getUpdates(input)
    val invalidUpdates = updates.filter { !it.isValid(rules) }
    val fixedUpdates = invalidUpdates.map { it.fix(rules) }

    println(fixedUpdates.sumOf { it.getMiddleNumber() })
}

private fun getRules(input: String): List<Rule> {
    return input.getLines().filter { it.contains("|") }
        .map { it.trim().split("|") }
        .map { (Rule(it[0].toInt(), it[1].toInt())) }
}

private fun getUpdates(input: String): List<Update> {
   return input.getLines().filter { !it.contains("|") && !it.isEmpty() }
                          .map { Update(it.trim().split(",").map { it.toInt() }) }
}

class Rule (val before: Int,
            val after: Int) {

    fun validate(update: Update): Boolean {
        val beforeIndex = update.sequence.indexOfFirst { it == before }
        val afterIndexes = update.sequence.indexOfFirst { it == after }

        if (beforeIndex != -1 && afterIndexes != -1) {
            return beforeIndex < afterIndexes
        }

        return true
    }
}

class Update(val sequence: List<Int>) {

    fun isValid(rules: List<Rule>): Boolean {
        return rules.all { it.validate(this)}
    }

    fun fix(rules: List<Rule>): Update {

        var fixedUpdate = Update(sequence)
        repeat(5) {
            rules.forEach { rule ->
                if (!rule.validate(fixedUpdate)) {
                    val fixedUpdateSecuence = mutableListOf<Int>()
                    fixedUpdateSecuence.addAll(fixedUpdate.sequence)

                    println("Rule: ${rule.before} -> ${rule.after}")
                    println("fixedSequenceBefore: ${fixedUpdateSecuence}")


                    val beforeIndex = fixedUpdateSecuence.indexOf(rule.before)
                    val afterIndex = fixedUpdateSecuence.indexOf(rule.after)

                    fixedUpdateSecuence[beforeIndex] = rule.after
                    fixedUpdateSecuence[afterIndex] = rule.before

                    fixedUpdate = Update(fixedUpdateSecuence)
                    println("fixedSequenceAfetr: $fixedUpdateSecuence")
                }
            }
        }


        return fixedUpdate
    }
    fun getMiddleNumber(): Int {
        val middleIndex =  sequence.size / 2
        return sequence[middleIndex]
    }
}