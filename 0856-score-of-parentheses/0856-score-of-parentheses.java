class Solution {
    public int scoreOfParentheses(String s) {
        int n = s.length();
        int[] stack = new int[n];
        int top = -1;

        // Push initial score level 0
        stack[++top] = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack[++top] = 0; // Push new level
            } else {
                int innerScore = stack[top--];
                int currentScore = (innerScore == 0) ? 1 : 2 * innerScore;
                stack[top] += currentScore; // Add score to parent level
            }
        }

        return stack[top];
    }
}