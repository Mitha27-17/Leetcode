import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, n);
        Arrays.fill(right, -1);

        // Track the first and last occurrence index for every character
        for (int i = 0; i < n; i++) {
            int charIdx = s.charAt(i) - 'a';
            left[charIdx] = Math.min(left[charIdx], i);
            right[charIdx] = i;
        }

        List<String> res = new ArrayList<>();
        int lastRight = -1;

        for (int i = 0; i < n; i++) {
            
            if (i != left[s.charAt(i) - 'a']) continue;

            int newRight = getValidRightBound(s, i, left, right);
            if (newRight == -1) continue; 

           
            if (i <= lastRight && !res.isEmpty()) {
                res.set(res.size() - 1, s.substring(i, newRight + 1));
            } else {
                res.add(s.substring(i, newRight + 1));
            }

            lastRight = newRight;
        }

        return res;
    }

   
    private int getValidRightBound(String s, int startIdx, int[] left, int[] right) {
        int currentRight = right[s.charAt(startIdx) - 'a'];

        for (int j = startIdx; j <= currentRight; j++) {
            int charIdx = s.charAt(j) - 'a';
            
            
            if (left[charIdx] < startIdx) {
                return -1;
            }
            
           
            currentRight = Math.max(currentRight, right[charIdx]);
        }

        return currentRight;
    }
}