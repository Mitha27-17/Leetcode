class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If neededRight is odd, we need to insert a ')' for a previous '('
                if (neededRight % 2 != 0) {
                    insertions++;
                    neededRight--; // Now aligned to an even number
                }
                neededRight += 2; // Each '(' needs '))'
            } else {
                // Check if the next char is also ')'
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++; // Consume the pair "))"
                } else {
                    insertions++; // Insert a missing ')' to form "))"
                }

                if (neededRight > 0) {
                    neededRight -= 2;
                } else {
                    insertions++; // Insert a missing '(' before this "))"
                }
            }
        }

        return insertions + neededRight;
    }
}