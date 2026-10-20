# 🌴 695. Max Area of Island

**Difficulty:** Medium 🟡  
**Topic:** Graph / DFS / Matrix  
**Language:** Java

## 📌 Problem Description

Given an `m × n` binary matrix `grid`, find the **maximum area of an island**.

- `1` represents land.
- `0` represents water.
- Land cells are connected horizontally or vertically, not diagonally.
- The area of an island is the number of connected land cells.

If there are no islands, return `0`.

### Example

**Input:**

```text
grid = [
  [1, 1, 0, 0],
  [0, 1, 0, 1],
  [0, 0, 0, 1],
  [1, 0, 0, 1]
]
```

**Output:**

```text
3
```

The islands have areas `3`, `3`, and `1`, so the maximum area is `3`.

---

## 💡 Approach — Depth-First Search (DFS)

I solved this problem using **DFS**.

The idea is similar to *Number of Islands (LeetCode 200)*, but instead of counting islands, we calculate the area of each island and keep the largest one.

The solution has three main steps:

1. Traverse the entire matrix using two nested loops.
2. Whenever we find a land cell (`1`), start DFS to calculate its island's area.
3. Update `maxArea` with the largest area found so far.

Inside DFS, every visited land cell contributes `1` to the total area.

## 💻 Java Solution

```java
class Solution {

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {

                if (grid[row][col] == 1) {
                    int result = dfs(grid, row, col);
                    maxArea = Math.max(maxArea, result);
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int row, int col) {

        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length ||
            grid[row][col] == 0) {
            return 0;
        }

        int area = 1;

        grid[row][col] = 0;

        int down = dfs(grid, row + 1, col);
        area = area + down;

        int up = dfs(grid, row - 1, col);
        area = area + up;

        int right = dfs(grid, row, col + 1);
        area = area + right;

        int left = dfs(grid, row, col - 1);
        area = area + left;

        return area;
    }
}
```

## 🔍 Step-by-Step Explanation

### Step 1 — Traverse the Matrix

We use two nested loops to visit every cell:

```java
for (int row = 0; row < grid.length; row++) {
    for (int col = 0; col < grid[0].length; col++) {
        // Check each cell
    }
}
```

When a cell contains `1`, we start DFS:

```java
if (grid[row][col] == 1) {
    int result = dfs(grid, row, col);
    maxArea = Math.max(maxArea, result);
}
```

The `dfs()` method returns the area of that island.

We only need to store the largest area, so an additional list is unnecessary.

### Step 2 — Handle the DFS Base Case

```java
if (row < 0 || row >= grid.length ||
    col < 0 || col >= grid[0].length ||
    grid[row][col] == 0) {
    return 0;
}
```

DFS returns `0` when:

- The position is outside the matrix.
- The current cell is water.
- The current cell has already been visited.

These cells do not contribute to the island's area.

### Step 3 — Mark the Cell as Visited

```java
int area = 1;
grid[row][col] = 0;
```

Every valid land cell contributes `1`.

We then change the current cell from `1` to `0` to prevent visiting it again.

**Important:** We must mark the cell before exploring its neighbors. Otherwise, DFS could repeatedly move between connected cells and cause infinite recursion.

This approach modifies the original input matrix.

### Step 4 — Explore Four Directions

```java
int down = dfs(grid, row + 1, col);
area = area + down;

int up = dfs(grid, row - 1, col);
area = area + up;

int right = dfs(grid, row, col + 1);
area = area + right;

int left = dfs(grid, row, col - 1);
area = area + left;
```

Each recursive call returns the number of connected land cells discovered in that direction.

We add these results to `area` and finally return it.

The movement rules are:

```text
row + 1 → Down
row - 1 → Up
col + 1 → Right
col - 1 → Left
```

## 🔄 Example Walkthrough

Consider:

```text
1  1  0
0  1  0
0  1  1
```

DFS starts at `grid[0][0]`.

It explores all connected land cells:

```text
(0,0) → (0,1) → (1,1) → (2,1) → (2,2)
```

Each cell contributes `1`, giving:

```text
Area = 1 + 1 + 1 + 1 + 1 = 5
```

After DFS, the matrix becomes:

```text
0  0  0
0  0  0
0  0  0
```

The main method updates:

```java
maxArea = Math.max(maxArea, 5);
```

**Final Output:**

```text
5
```

## ⏱️ Complexity Analysis

Let:

- `m` = number of rows
- `n` = number of columns

**Time Complexity: O(m × n)**

Every cell is examined by the outer loops, and each land cell is processed by DFS at most once.

**Space Complexity: O(m × n)**

In the worst case, the recursive DFS call stack can contain up to `m × n` calls.

We do not need an additional `visited` array because we mark visited cells directly in `grid`.

## 🧠 Key Takeaways

- DFS can be used to explore connected components in a matrix.
- Each land cell contributes `1` to its island's area.
- Returning `int` from DFS allows us to calculate the total area recursively.
- A Base Case prevents invalid positions and repeated visits.
- Marking cells as visited **before recursion** prevents infinite loops.
- `Math.max()` helps track the largest island without storing every area.
- Two nested loops ensure that all islands are discovered.
- Modifying the input matrix removes the need for a separate visited structure.

### ⭐ Pattern Learned — DFS on a Grid

```text
Traverse the matrix
        ↓
Find a land cell (1)
        ↓
Start DFS
        ↓
Mark the cell as visited
        ↓
Explore four directions
        ↓
Sum connected land cells
        ↓
Update maximum area
```

**Pattern:** Graph Traversal / DFS / Connected Components

## 🔗 LeetCode Problem

[695. Max Area of Island](https://leetcode.com/problems/max-area-of-island/)
