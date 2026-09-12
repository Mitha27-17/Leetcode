import java.util.*;

class Solution {
    // Custom state to hold max weight and lexicographically smallest indices
    static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }

        static State best(State s1, State s2) {
            if (s1.weight != s2.weight) {
                return s1.weight > s2.weight ? s1 : s2;
            }
            // Tie-break: Lexicographically smaller sequence of original indices
            int size1 = s1.indices.size();
            int size2 = s2.indices.size();
            int minLen = Math.min(size1, size2);

            for (int i = 0; i < minLen; i++) {
                int cmp = Integer.compare(s1.indices.get(i), s2.indices.get(i));
                if (cmp != 0) return cmp < 0 ? s1 : s2;
            }
            return size1 <= size2 ? s1 : s2;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // Store: {start, end, weight, originalIndex}
        int[][] items = new int[n][4];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            items[i][0] = interval.get(0);
            items[i][1] = interval.get(1);
            items[i][2] = interval.get(2);
            items[i][3] = i;
        }

        // Sort by end time
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        int[] endTimes = new int[n];
        for (int i = 0; i < n; i++) {
            endTimes[i] = items[i][1];
        }

        // dp[i][k] stores the best State considering first i intervals with exactly k chosen
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(-1, new ArrayList<>());
            }
        }
        dp[0][0] = new State(0, new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            int start = items[i - 1][0];
            int weight = items[i - 1][2];
            int origIdx = items[i - 1][3];

            int p = binarySearch(endTimes, i - 1, start);

            for (int k = 0; k <= 4; k++) {
                // Option 1: Do not pick current interval
                if (dp[i - 1][k].weight != -1) {
                    dp[i][k] = State.best(dp[i][k], dp[i - 1][k]);
                }

                // Option 2: Pick current interval
                if (k > 0 && dp[p][k - 1].weight != -1) {
                    List<Integer> nextIndices = new ArrayList<>(dp[p][k - 1].indices);
                    nextIndices.add(origIdx);
                    Collections.sort(nextIndices); // Maintain sorted order of indices

                    State takeState = new State(dp[p][k - 1].weight + weight, nextIndices);
                    dp[i][k] = State.best(dp[i][k], takeState);
                }
            }
        }

        // Find the absolute best state among all k in [1, 4]
        State bestOverall = new State(-1, new ArrayList<>());
        for (int k = 1; k <= 4; k++) {
            if (dp[n][k].weight != -1) {
                bestOverall = State.best(bestOverall, dp[n][k]);
            }
        }

        int[] result = new int[bestOverall.indices.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bestOverall.indices.get(i);
        }

        return result;
    }

    private int binarySearch(int[] endTimes, int rightBound, int targetStart) {
        int low = 0, high = rightBound - 1;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (endTimes[mid] < targetStart) {
                ans = mid + 1;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
}