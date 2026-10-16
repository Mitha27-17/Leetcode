import java.util.HashMap;
import java.util.Map;

class Solution {
    private long[] tree;
    private long[] lazy;
    private long[] sumSq;
    private static final long MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new long[4 * n];
        lazy = new long[4 * n];
        sumSq = new long[4 * n];

        Map<Integer, Integer> lastOccur = new HashMap<>();
        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int prev = lastOccur.getOrDefault(val, -1);
            
            // Update range [prev + 1, i] by adding 1 to counts
            update(1, 0, n - 1, prev + 1, i, 1);
            
            // Query total sum of squares for all subarrays ending at index i
            long currentSumSq = query(1, 0, n - 1, 0, i);
            totalSum = (totalSum + currentSumSq) % MOD;
            
            lastOccur.put(val, i);
        }

        return (int) totalSum;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            long lz = lazy[node];
            int mid = l + (r - l) / 2;
            
            apply(2 * node, l, mid, lz);
            apply(2 * node + 1, mid + 1, r, lz);
            
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        long len = (r - l + 1);
        // sumSq' = sum((c + val)^2) = sum(c^2) + 2*val*sum(c) + val^2 * length
        sumSq[node] = (sumSq[node] + 2 * val * tree[node] + val * val % MOD * len) % MOD;
        tree[node] = (tree[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        push(node, l, r);
        int mid = l + (r - l) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        
        tree[node] = (tree[2 * node] + tree[2 * node + 1]) % MOD;
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
    }

    private long query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) {
            return (sumSq[node] + MOD) % MOD;
        }
        push(node, l, r);
        int mid = l + (r - l) / 2;
        long leftRes = query(2 * node, l, mid, ql, qr);
        long rightRes = query(2 * node + 1, mid + 1, r, ql, qr);
        return (leftRes + rightRes) % MOD;
    }
}