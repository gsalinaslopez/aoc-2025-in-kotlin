import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): BigInteger {
        val ranges = input.first().split(",")

        var result = 0.toBigInteger()
        ranges.map { it.split("-") }.forEach { range ->
            var start = range.first().toBigInteger()
            val end = range.last().toBigInteger()
            while (start <= end) {
                val str = start.toString()
                if (str.length % 2 == 0) {
                    result += if (str.take(str.length / 2) == str.substring(startIndex = str.length / 2)) start else 0.toBigInteger()
                }
                start++
            }
        }
        return result
    }

    fun part2(input: List<String>): BigInteger {
        val ranges = input.first().split(",")

        var result = 0.toBigInteger()
        ranges.map { it.split("-") }.forEach { range ->
            var start = range.first().toBigInteger()
            val end = range.last().toBigInteger()
            while (start <= end) {
                val str = start.toString()

                for (i in 0..<(str.length / 2)) {
                    val chunk = str.take(i + 1)
                    // repeat
                    var found = true
                    for (j in (i + 1)..<str.length step i + 1) {
                        try {
                            if (chunk != str.substring(startIndex = j, endIndex = j + i + 1)) {
                                found = false
                                break
                            }
                        } catch(_: Exception) {
                            found = false
                            break
                        }
                    }
                    if (found) {
                        result += start
                        break
                    }
                }
                start++
            }
        }
        return result
    }

    // test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == "1227775554".toBigInteger())
    check(part2(testInput) == "4174379265".toBigInteger())

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
