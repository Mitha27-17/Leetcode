class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;

        // dp[i][j] = number of ways to draw j segments using points from 0 to i
        long[][] dp = new long[n][k + 1];

        // Base case: 0 segments can always be drawn in 1 way for any number of points
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= k; j++) {
            long prefixSum = 0;
            for (int i = 1; i < n; i++) {
                // Accumulate ways to form j-1 segments up to point i-1
                prefixSum = (prefixSum + dp[i - 1][j - 1]) % MOD;

                // dp[i][j] = (ways without using point i as segment end) + (ways using point i as segment end)
                dp[i][j] = (dp[i - 1][j] + prefixSum) % MOD;
            }
        }

        return (int) dp[n - 1][k];
    }
}