/**
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree).
 * Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 *
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 *
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0.
 * Return the minimum number of edges changed.
 *
 * It's guaranteed that each city can reach city 0 after reorder.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 2:
 *
 *
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 3:
 *
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 2 <= n <= 5 * 10^4
 * connections.length == n - 1
 * connections[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * @see <a href="https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/">LeetCode</a>
 */
fun minReorder(n: Int, connections: Array<IntArray>): Int {
    val cityToNeighbourMap = Array(n) { mutableListOf<Pair<Int, Boolean>>() }
    connections.forEach { connection ->
        val fromCity = connection[0]
        val toCity = connection[1]
        // true -> the road needs reversal
        cityToNeighbourMap[fromCity].add(Pair(toCity, true))
        // false -> the road does not need reversal
        cityToNeighbourMap[toCity].add(Pair(fromCity, false))
    }
    val visitedCities = BooleanArray(n)
    visitedCities[0] = true
    return reorderUnvisitedNeighbours(0, cityToNeighbourMap, visitedCities)
}

private fun reorderUnvisitedNeighbours(
    city: Int,
    cityToNeighbourMap: Array<MutableList<Pair<Int, Boolean>>>,
    visitedCities: BooleanArray,
): Int {
    var reversedRoadsCount = 0
    cityToNeighbourMap[city].forEach { (neighbour, needsReversal) ->
        if (!visitedCities[neighbour]) {
            visitedCities[neighbour] = true
            reversedRoadsCount += if (needsReversal) 1 else 0
            reversedRoadsCount += reorderUnvisitedNeighbours(neighbour, cityToNeighbourMap, visitedCities)
        }
    }
    return reversedRoadsCount
}
