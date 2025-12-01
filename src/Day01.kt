fun main() {
    fun part1(input: List<String>): Int {
        var curr = 50
        var result = 0

        for (instruction in input) {
            val dir = instruction.take(1)
            val amount = instruction.substring(1).toInt()
            when (dir) {
                "L" -> {
                    val temp = (curr - amount) % 100
                    curr = if (temp < 0) {
                        100 + temp
                    } else {
                        temp
                    }
                }
                else -> {
                    curr = (curr + amount) % 100
                }
            }
            if (curr == 0) {
                result += 1
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var curr = 50
        var result = 0

        for (instruction in input) {
            val dir = instruction.take(1)
            val amount = instruction.substring(1).toInt()
            when (dir) {
                "L" -> {
                    var temp = curr - amount
                    if (temp < 0) {
                        result -= temp / 100
                    }

                    temp %= 100
                    curr = if (temp < 0) {
                        if (curr != 0) {
                            result += 1
                        }
                        100 + temp
                    } else {
                        temp
                    }
                    if (curr == 0) {
                        result += 1
                    }
                }
                else -> {
                    val temp = curr + amount
                    result += temp / 100
                    curr = temp % 100
                }
            }
        }
        return result
    }

    // test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
