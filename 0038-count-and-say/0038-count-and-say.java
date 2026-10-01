class Solution {
    public String countAndSay(int n) {
        if (n <= 0) return "";
        
        String curr = "1";

        for (int iter = 1; iter < n; iter++) {
            StringBuilder sb = new StringBuilder();
            int len = curr.length();
            int count = 1;

            for (int i = 0; i < len; i++) {
                // Check if the next character continues the same run
                if (i + 1 < len && curr.charAt(i) == curr.charAt(i + 1)) {
                    count++;
                } else {
                    // Flush count and digit character to StringBuilder
                    sb.append(count).append(curr.charAt(i));
                    count = 1; // Reset run length counter
                }
            }

            curr = sb.toString();
        }

        return curr;
    }
}