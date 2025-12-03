import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach { bank ->
            var max = Int.MIN_VALUE
            for (i in 0..<(bank.length - 1)) {
                for (j in i + 1..<bank.length) {
                    max = Math.max(((bank[i].code - '0'.code) * 10) + bank[j].code - '0'.code, max)
                }
            }
            result += max
        }
        return result
    }

    fun part2(input: List<String>): Long {
        var result = 0L
        input.forEach { bank ->
            var max: Long = bank.substring(startIndex = bank.length - 12).toLong()
            for (i in (bank.length - 13) downTo 0) {
                var curr = max
                for (j in 0..<12) {
                    val str = max.toString().removeRange(startIndex = j, endIndex = j + 1).toLong()
                    val value = "${bank[i]}$str".toLong()
                    curr = Math.max(value, curr)
                }
                max = curr
            }
            result += max
        }
        return result
    }

    // test input from the `src/Day03_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357)
    check(part2(testInput) == 3121910778619L)

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
    //part3(input).println()
}
