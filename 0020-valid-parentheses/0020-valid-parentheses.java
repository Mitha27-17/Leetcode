class Solution {
    public boolean isValid(String s) {
        int n = s.length();
        
        // A valid string must have an even length
        if (n % 2 != 0) {
            return false;
        }

        char[] stack = new char[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            // Push expected closing bracket onto stack
            if (c == '(') {
                stack[++top] = ')';
            } else if (c == '{') {
                stack[++top] = '}';
            } else if (c == '[') {
                stack[++top] = ']';
            } else {
                // If closing bracket matches nothing or mismatches top element
                if (top == -1 || stack[top--] != c) {
                    return false;
                }
            }
        }

        // Valid only if all opened brackets have been closed
        return top == -1;
    }
}