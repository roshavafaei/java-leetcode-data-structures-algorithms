class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {

                if (grid[row][col] == 1) {
                    var result = dfs(grid, row, col);
                    maxArea = Math.max(maxArea, result);
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int row, int col) {
        int area = 1;

        if (row < 0 || row >= grid.length ||
        col < 0 || col >= grid[0].length ||
        grid[row][col] == 0)
            return 0;

        grid[row][col] = 0;

        var down = dfs(grid, row + 1, col);
        area = area + down;
        var up = dfs(grid, row - 1, col);
        area = area + up;
        var right = dfs(grid, row, col + 1);
        area = area + right;
        var left = dfs(grid, row, col - 1);
        area = area + left;

        return area;
    }
}
