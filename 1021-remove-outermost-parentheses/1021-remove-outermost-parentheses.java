class Solution {
    public String removeOuterParentheses(String s) {
        int n = s.length();
        char[] result = new char[n];
        int writeIdx = 0;
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If depth > 0, c is not the outermost '('
                if (depth > 0) {
                    result[writeIdx++] = c;
                }
                depth++;
            } else {
                depth--;
                // If depth > 0 after decrementing, c is not the outermost ')'
                if (depth > 0) {
                    result[writeIdx++] = c;
                }
            }
        }

        return new String(result, 0, writeIdx);
    }
}