class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0;
        }

        // Primitive stack storing indices
        int[] stack = new int[n + 1];
        int top = -1;

        // Base boundary for valid length calculations
        stack[++top] = -1;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack[++top] = i;
            } else {
                top--; // Pop matching '(' or last boundary

                if (top == -1) {
                    // Reset boundary if no matching '(' exists
                    stack[++top] = i;
                } else {
                    maxLen = Math.max(maxLen, i - stack[top]);
                }
            }
        }

        return maxLen;
    }
}