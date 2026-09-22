import java.util.Arrays;

class Solution {
    static class Node {
        int[] remain = new int[5]; // Counts for remainders [0..k-1]
        int prod = 1;              // Product of all elements in segment mod k
    }

    private Node[] tree;
    private int n;
    private int k;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        // Normalize nums modulo k
        for (int i = 0; i < n; i++) {
            nums[i] %= k;
        }

        tree = new Node[4 * n];
        build(nums, 0, 0, n - 1);

        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1] % k;
            int start = queries[i][2];
            int x = queries[i][3];

            // Update nums[idx] to val (persists for subsequent queries)
            update(0, 0, n - 1, idx, val);

            // Query range [start, n - 1]
            Node resNode = query(0, 0, n - 1, start, n - 1);
            ans[i] = resNode.remain[x];
        }

        return ans;
    }

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.prod = (left.prod * right.prod) % k;

        // Prefixes ending inside left child
        for (int r = 0; r < k; r++) {
            res.remain[r] = left.remain[r];
        }

        // Prefixes extending into right child:
        // Combined product = (left.prod * right_prefix_prod) % k
        for (int r = 0; r < k; r++) {
            int newRem = (left.prod * r) % k;
            res.remain[newRem] += right.remain[r];
        }

        return res;
    }

    private void build(int[] nums, int nodeIdx, int l, int r) {
        tree[nodeIdx] = new Node();
        if (l == r) {
            tree[nodeIdx].prod = nums[l];
            tree[nodeIdx].remain[nums[l]] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(nums, 2 * nodeIdx + 1, l, mid);
        build(nums, 2 * nodeIdx + 2, mid + 1, r);
        tree[nodeIdx] = merge(tree[2 * nodeIdx + 1], tree[2 * nodeIdx + 2]);
    }

    private void update(int nodeIdx, int l, int r, int idx, int val) {
        if (l == r) {
            Arrays.fill(tree[nodeIdx].remain, 0);
            tree[nodeIdx].prod = val;
            tree[nodeIdx].remain[val] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * nodeIdx + 1, l, mid, idx, val);
        } else {
            update(2 * nodeIdx + 2, mid + 1, r, idx, val);
        }
        tree[nodeIdx] = merge(tree[2 * nodeIdx + 1], tree[2 * nodeIdx + 2]);
    }

    private Node query(int nodeIdx, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[nodeIdx];
        }
        int mid = l + (r - l) / 2;
        if (qr <= mid) {
            return query(2 * nodeIdx + 1, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(2 * nodeIdx + 2, mid + 1, r, ql, qr);
        }

        Node leftRes = query(2 * nodeIdx + 1, l, mid, ql, qr);
        Node rightRes = query(2 * nodeIdx + 2, mid + 1, r, ql, qr);
        return merge(leftRes, rightRes);
    }
}