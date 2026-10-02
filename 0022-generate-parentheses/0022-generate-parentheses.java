import java.util.ArrayList;
import java.util.List;

class Solution {
    public List generateParenthesis(int n) {
        List result = new ArrayList<>();
        char[] path = new char[2 * n];
        backtrack(n, 0, 0, 0, path, result);
        return result;
    }

    private void backtrack(int n, int open, int close, int idx, char[] path, List result) {
        // Base case: formed a valid string of length 2 * n
        if (idx == 2 * n) {
            result.add(new String(path));
            return;
        }

        // Option 1: Place an open parenthesis
        if (open < n) {
            path[idx] = '(';
            backtrack(n, open + 1, close, idx + 1, path, result);
        }

        // Option 2: Place a close parenthesis
        if (close < open) {
            path[idx] = ')';
            backtrack(n, open, close + 1, idx + 1, path, result);
        }
    }
}