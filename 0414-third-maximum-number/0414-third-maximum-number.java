class Solution {
    public int thirdMax(int[] nums) {
        Long first = null, second = null, third = null;

        for (int n : nums) {
            long num = (long) n;

            if (first != null && num == first) continue;
            if (second != null && num == second) continue;
            if (third != null && num == third) continue;

            if (first == null || num > first) {
                third = second;
                second = first;
                first = num;
            } else if (second == null || num > second) {
                third = second;
                second = num;
            } else if (third == null || num > third) {
                third = num;
            }
        }

        return (third != null ? third : first).intValue();
    }
}