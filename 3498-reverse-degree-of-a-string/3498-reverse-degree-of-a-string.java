class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Reversed alphabet position: 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reversedAlphabetPos = 26 - (c - 'a');
            
            // Multiply by 1-based string position
            totalDegree += reversedAlphabetPos * (i + 1);
        }
        
        return totalDegree;
    }
}