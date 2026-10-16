class Solution {
    public int numIslands(char[][] grid) {
        int islandCount = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    islandCount++;
                    DFS(grid, row, col);
                }
            }
        }
        return islandCount;
    }

    private void DFS(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || 
            col < 0 || col >= grid[0].length || 
            grid[row][col] == '0') {
            return;
        }
        
        grid[row][col] = '0';

        DFS(grid, row + 1, col);
        DFS(grid, row - 1, col);
        DFS(grid, row, col + 1);
        DFS(grid, row, col - 1);
    }
}
