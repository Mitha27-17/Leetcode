import java.util.*;

class Solution {
    public int maxRemovals(String source, String pattern, int[] targetIndices) {
        int n = source.length();
        int m = pattern.length();

        boolean[] removable = new boolean[n];
        for (int index : targetIndices) {
            removable[index] = true;
        }

        int[] dp = new int[m + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = m; j >= 1; j--) {
                if (source.charAt(i) == pattern.charAt(j - 1)
                        && dp[j - 1] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j],
                            dp[j - 1] + (removable[i] ? 1 : 0));
                }
            }
        }

        return targetIndices.length - dp[m];
    }
}