import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[31];
        
        // Step 1: Count the total frequency of 1-bits at each position
        for (int x : nums) {
            for (int i = 0; i < 31; i++) {
                if ((x & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }

        long totalSum = 0;
        long MOD = 1_000_000_007;

        // Step 2: Greedily construct k numbers to maximize individual values
        for (int step = 0; step < k; step++) {
            long curr = 0;
            for (int i = 0; i < 31; i++) {
                if (bitCount[i] > 0) {
                    curr |= (1L << i);
                    bitCount[i]--;
                }
            }
            totalSum = (totalSum + (curr * curr) % MOD) % MOD;
        }

        return (int) totalSum;
    }
}