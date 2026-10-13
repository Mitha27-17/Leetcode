import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        // Step 1: Compute squares of all elements
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        // Step 2: Sort squares in ascending order
        Arrays.sort(squares);

        long score = 0;
        int m = n / 2;

        // Step 3: Subtract smaller squares and add larger squares
        for (int i = 0; i < m; i++) {
            score -= squares[i];
        }

        for (int i = m; i < n; i++) {
            score += squares[i];
        }

        return score;
    }
}