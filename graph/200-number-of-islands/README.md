
# 200. Number of Islands

**Difficulty:** Medium  
**Topic:** Graph, Matrix, Depth-First Search (DFS), Recursion  
**LeetCode:** [Number of Islands](https://leetcode.com/problems/number-of-islands/)

## Problem Description

Given an `m x n` 2D grid containing `'1'` (land) and `'0'` (water), return the number of islands.

An island is a group of connected land cells surrounded by water. Land cells are connected only **horizontally or vertically**, not diagonally.

## Example

**Input:**

```text
grid = [
  ["1","1","0"],
  ["0","1","0"],
  ["0","0","1"]
]
```

**Output:**

```text
2
```

**Explanation:**

There are two islands:

- The first island consists of three connected land cells.
- The second island consists of one isolated land cell.

## Approach: Depth-First Search (DFS)

We can treat the grid as an implicit graph, where each land cell represents a node and its adjacent land cells represent neighbors.

Instead of building an adjacency list using a `HashMap`, we can directly explore neighboring cells in the matrix.

### Algorithm

1. Initialize `islandCount` to `0`.
2. Traverse every cell using two nested loops.
3. When an unvisited land cell (`'1'`) is found:
   - Increment `islandCount`.
   - Start DFS from that cell.
4. During DFS:
   - Return if the position is outside the grid or the cell contains `'0'`.
   - Mark the current cell as visited by changing `'1'` to `'0'`.
   - Recursively explore the four neighboring cells: up, down, left, and right.
5. Return `islandCount`.

Marking visited cells prevents counting the same island multiple times and avoids infinite recursion.

## Java Solution

```java
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

        // Base case: out of bounds or water/visited cell
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length ||
            grid[row][col] == '0') {
            return;
        }

        // Mark the current cell as visited
        grid[row][col] = '0';

        // Explore all four directions
        DFS(grid, row + 1, col); // Down
        DFS(grid, row - 1, col); // Up
        DFS(grid, row, col + 1); // Right
        DFS(grid, row, col - 1); // Left
    }
}
```

## Complexity Analysis

Let:

- `m` = number of rows
- `n` = number of columns

**Time Complexity: O(m × n)**

Each cell is visited at most once as land. DFS marks visited land cells as `'0'`, preventing repeated exploration.

**Space Complexity: O(m × n)**

In the worst case, the recursive DFS call stack can contain up to `m × n` calls.

No additional visited matrix or hash set is required because the input grid is modified in place.

## Key Concepts

### 1. Matrix as a Graph

A matrix can represent a graph without explicitly creating nodes or an adjacency list.

Each land cell has up to four neighbors:

```java
DFS(grid, row - 1, col); // Up
DFS(grid, row + 1, col); // Down
DFS(grid, row, col - 1); // Left
DFS(grid, row, col + 1); // Right
```

### 2. DFS and Recursion

DFS explores connected land cells before returning to the previous recursive call.

The base case prevents accessing invalid positions and revisiting processed cells.

### 3. Tracking Visited Cells

Instead of using a `HashSet`, we mark visited cells directly:

```java
grid[row][col] = '0';
```

**Note:** This approach modifies the original input grid.

### 4. Connected Components

Each island represents a connected component of the graph.

We increment the island counter only when discovering a new, unvisited land cell in the main traversal.

DFS then marks the entire connected component as visited.

## What I Learned

- How to apply DFS to a 2D matrix.
- How a matrix can represent an implicit graph.
- How to use recursion and base cases for grid traversal.
- How to explore neighboring cells using row and column indices.
- How to track visited cells without an additional data structure.
- How DFS can be used to count connected components.

---

**LeetCode Problem:** [200. Number of Islands](https://leetcode.com/problems/number-of-islands/)
