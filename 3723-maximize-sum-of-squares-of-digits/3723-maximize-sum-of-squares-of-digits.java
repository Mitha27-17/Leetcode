class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // A single digit can contribute at most 9 to the sum
        if (sum > 9 * num) {
            return "";
        }

        int nines = sum / 9;
        int remainder = sum % 9;
        
        char[] result = new char[num];
        int idx = 0;

        // Greedily place maximum possible 9s at the most significant positions
        for (int i = 0; i < nines; i++) {
            result[idx++] = '9';
        }

        // Place the remainder digit if any exists
        if (remainder > 0) {
            result[idx++] = (char) ('0' + remainder);
        }

        // Fill any remaining lower-significance slots with zeros
        while (idx < num) {
            result[idx++] = '0';
        }

        return new String(result);
    }
}