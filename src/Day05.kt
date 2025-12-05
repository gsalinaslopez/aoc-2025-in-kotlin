import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        val ranges = mutableListOf<LongRange>()
        var doingRanges = true
        for (line in input) {
            if (line.isEmpty()) {
                doingRanges = false
                continue
            }

            if (doingRanges) {
                val values = line.split("-").map { it.toLong() }
                ranges.add(values.first()..values.last())
            } else {
                for (range in ranges) {
                    if (line.toLong() in range) {
                        result += 1
                        break
                    }
                }
            }
        }
        return result
    }

    fun part2(input: List<String>): Long {
        val ranges = mutableListOf<Pair<Long, Long>>()
        for (line in input) {
            if (line.isEmpty()) {
                break
            }
            val values = line.split("-").map { it.toLong() }
            ranges.add(Pair(values.first(), values.last()))
        }
        ranges.sortWith(Comparator { o1, o2 ->
            if (o1.first == o2.first) {
                return@Comparator 0
            } else if (o1.first < o2.first) {
                return@Comparator -1
            } else {
                return@Comparator 1
            }
        })

        val mergedRanges = Array(ranges.size) { i -> Pair(ranges[i].first, ranges[i].second) }
        var head = -1
        for (i in 0..<ranges.size) {
            if (head == -1) {
                head += 1
                mergedRanges[head] = ranges[i]
            } else {
                if (ranges[i].first in mergedRanges[head].first..mergedRanges[head].second) {
                    mergedRanges[head] = Pair(mergedRanges[head].first, Math.max(mergedRanges[head].second, ranges[i].second))
                } else {
                    head += 1
                    mergedRanges[head] = ranges[i]
                }
            }
        }
        var result = 0L
        for (i in 0..head) {
            result += mergedRanges[i].second - mergedRanges[i].first + 1
        }
        return result
    }

    // test input from the `src/Day05_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 14L)

    // Read the input from the `src/Day05.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
