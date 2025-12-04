import java.math.BigInteger

fun main() {
    val dir = listOf(
        Pair(-1, -1),
        Pair(-1, 0),
        Pair(-1, 1),
        Pair(0, -1),
        Pair(0, 1),
        Pair(1, -1),
        Pair(1, 0),
        Pair(1, 1),
    )
    fun part1(input: List<String>): Int {
        var result = 0
        val matrix = Array(input.size) { i -> Array(input[i].length) { j -> input[i][j]} }
        for (i in 0..<matrix.size) {
            for (j in 0..<matrix[i].size) {
                if (matrix[i][j] != '@') {
                    continue
                }
                var adj = 0
                for (d in dir) {
                    val newX = i + d.first
                    val newY = j + d.second
                    if (newX in 0..<matrix.size && newY in 0..<matrix[i].size) {
                        if (matrix[newX][newY] == '@') {
                            adj++
                        }
                    }
                }
                if (adj < 4) {
                    result += 1
                }
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val matrix = Array(input.size) { i -> Array(input[i].length) { j -> input[i][j]} }
        while (true) {
            val dotsToRemove = mutableListOf<Pair<Int, Int>>()
            for (i in 0..<matrix.size) {
                for (j in 0..<matrix[i].size) {
                    if (matrix[i][j] != '@') {
                        continue
                    }
                    var adj = 0
                    for (d in dir) {
                        val newX = i + d.first
                        val newY = j + d.second
                        if (newX in 0..<matrix.size && newY in 0..<matrix[i].size) {
                            if (matrix[newX][newY] == '@') {
                                adj++
                            }
                        }
                    }
                    if (adj < 4) {
                        result += 1
                        dotsToRemove.add(Pair(i, j))
                    }
                }
            }
            if (dotsToRemove.isEmpty()) {
                break
            }
            dotsToRemove.forEach { (i, j) ->
                matrix[i][j] = '.'
            }
        }
        return result
    }

    // test input from the `src/Day04_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
