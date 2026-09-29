class Solution {
    private boolean[][][] visited;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Total path length must be even to have balanced parentheses
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        int maxBal = (m + n) / 2;
        visited = new boolean[m][n][maxBal + 1];

        return dfs(grid, 0, 0, 0, maxBal);
    }

    private boolean dfs(char[][] grid, int r, int c, int bal, int maxBal) {
        bal += (grid[r][c] == '(' ? 1 : -1);

        // Invalid path if balance drops negative or exceeds maximum possible remaining pairs
        if (bal < 0 || bal > maxBal) {
            return false;
        }

        // Reached destination: balance must end at 0
        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }

        // State already visited
        if (visited[r][c][bal]) {
            return false;
        }
        visited[r][c][bal] = true;

        // Move Right
        if (c + 1 < n && dfs(grid, r, c + 1, bal, maxBal)) {
            return true;
        }

        // Move Down
        if (r + 1 < m && dfs(grid, r + 1, c, bal, maxBal)) {
            return true;
        }

        return false;
    }
}