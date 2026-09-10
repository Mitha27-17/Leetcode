class Solution {
    private int matchingNodeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodeCount = 0;
        postOrder(root);
        return matchingNodeCount;
    }

    // Returns int[] where:
    // index 0 -> sum of values in subtree
    // index 1 -> count of nodes in subtree
    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int subtreeSum = left[0] + right[0] + node.val;
        int subtreeCount = left[1] + right[1] + 1;

        // Integer division automatically rounds down (floors) the average
        if (node.val == subtreeSum / subtreeCount) {
            matchingNodeCount++;
        }

        return new int[]{subtreeSum, subtreeCount};
    }
}