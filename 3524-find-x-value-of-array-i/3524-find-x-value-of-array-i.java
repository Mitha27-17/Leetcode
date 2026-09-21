class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k]; // Total counts for each remainder r in [0, k - 1]
        long[] dp = new long[k];     // Subarrays ending at current position with product % k == r

        for (int num : nums) {
            long[] nextDp = new long[k];
            int numMod = num % k;

            // Start a new single-element subarray starting at current num
            nextDp[numMod] = 1;

            // Extend all valid previous subarrays ending at the previous position
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextMod = (int) ((1L * r * numMod) % k);
                    nextDp[nextMod] += dp[r];
                }
            }

            // Accumulate counts into the global result array
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }

            // Transition DP state to next position
            dp = nextDp;
        }

        return result;
    }
}