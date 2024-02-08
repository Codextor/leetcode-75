/**
 * Given a binary array nums and an integer k,
 * return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 * @see <a href="https://leetcode.com/problems/max-consecutive-ones-iii/">LeetCode</a>
 */
fun longestOnes(nums: IntArray, k: Int): Int {
    var startIndex = 0
    var numberOfZeros = 0
    var maxConsecutiveOnes = 0
    for ((endIndex, endDigit) in nums.withIndex()) {
        if (endDigit == 0) {
            numberOfZeros++
        }
        while (numberOfZeros > k) {
            if (nums[startIndex] == 0) {
                numberOfZeros--
            }
            startIndex++
        }
        maxConsecutiveOnes = maxOf(maxConsecutiveOnes, endIndex - startIndex + 1)
    }
    return maxConsecutiveOnes
}
