import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        long[] encoded = new long[n];

        // Step 1: Pack digit sum and original value into a single primitive long for sorting
        for (int i = 0; i < n; i++) {
            long dSum = getDigitSum(nums[i]);
            // Upper 32 bits store digit sum, lower 32 bits store the number itself
            encoded[i] = (dSum << 32) | (long) nums[i];
        }

        // Step 2: Sort based on digit sum ascending, then by numerical value ascending
        Arrays.sort(encoded);

        // Step 3: Map each sorted value to its target index
        Map<Integer, Integer> targetIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int originalVal = (int) encoded[i]; // Extract lower 32 bits
            targetIndexMap.put(originalVal, i);
        }

        // Step 4: Compute minimum swaps using cycle decomposition
        boolean[] visited = new boolean[n];
        int cycles = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            // Traverse the permutation cycle
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                // Find where the current number in nums should go in the sorted order
                curr = targetIndexMap.get(nums[curr]);
            }
            cycles++;
        }

        return n - cycles;
    }

    private int getDigitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}