class Solution {
    public boolean detectCapitalUse(String word) {
        int length = word.length();
        if (length <= 1) {
            return true;
        }

        int capitalsCount = 0;
        for (int i = 0; i < length; i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                capitalsCount++;
            }
        }

        // Case 1: All letters are capitals (e.g., "USA")
        if (capitalsCount == length) {
            return true;
        }

        // Case 2: All letters are not capitals (e.g., "leetcode")
        if (capitalsCount == 0) {
            return true;
        }

        // Case 3: Only the first letter is capital (e.g., "Google")
        return capitalsCount == 1 && Character.isUpperCase(word.charAt(0));
    }
}