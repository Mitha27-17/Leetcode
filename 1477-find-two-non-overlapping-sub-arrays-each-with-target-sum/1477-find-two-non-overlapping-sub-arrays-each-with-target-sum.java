import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        
        // minLen[i] stores the minimum length of a valid subarray ending at or before index i
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        
        int left = 0;
        int currentSum = 0;
        int result = Integer.MAX_VALUE;
        int minSoFar = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Shrink window if sum exceeds target
            while (currentSum > target) {
                currentSum -= arr[left];
                left++;
            }
            
            // Valid subarray found ending at 'right'
            if (currentSum == target) {
                int currentLen = right - left + 1;
                
                // If a non-overlapping valid subarray exists prior to 'left'
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, currentLen + minLen[left - 1]);
                }
                
                minSoFar = Math.min(minSoFar, currentLen);
            }
            
            // Maintain the minimum length seen up to the current index
            minLen[right] = minSoFar;
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}