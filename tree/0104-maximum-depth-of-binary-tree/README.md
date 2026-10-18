# 🌳 104. Maximum Depth of Binary Tree

**Difficulty:** Easy 🟢  
**Topic:** Binary Tree / Depth-First Search (DFS) / Recursion  
**Language:** Java

## 📌 Problem Description

Given the root of a binary tree, return its **maximum depth**.

The maximum depth is the number of nodes along the longest path from the root to the farthest leaf node.

### Example

Consider the following binary tree:

```text
        3
       / \
      9   20
         /  \
        15   7
```

The longest path can be:

```text
3 → 20 → 15
```

Or:

```text
3 → 20 → 7
```

Both paths contain **3 nodes**.

**Output:**

```text
3
```

## 💡 Approach — Recursive DFS

We use **recursion** to calculate the maximum depth of the binary tree.

For every node:

1. Recursively calculate the depth of the left subtree.
2. Recursively calculate the depth of the right subtree.
3. Take the larger depth.
4. Add `1` to count the current node.

The main recursive formula is:

```java
return 1 + Math.max(leftDepth, rightDepth);
```

The `1` represents the current node itself.

### Base Case

When the current node is `null`, there are no nodes to count.

```java
if (root == null)
    return 0;
```

Returning `0` allows the recursive calls to calculate the depth correctly as they return back up the tree.

## 💻 Java Solution

```java
class Solution {
    public int maxDepth(TreeNode root) {

        if (root == null)
            return 0;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }
}
```

*Note: LeetCode provides the `TreeNode` class.*

## 🔍 Example — Step-by-Step Recursion

Consider this simple tree:

```text
    3
   /
  9
```

### Step 1: Start at the Root

The first call is:

```java
maxDepth(3);
```

Inside this method, we recursively calculate the left subtree:

```java
int leftDepth = maxDepth(root.left);
```

This calls:

```java
maxDepth(9);
```

### Step 2: Process the Leaf Node

Node `9` has no children.

```text
    9
   / \
null null
```

Both recursive calls return `0`:

```text
maxDepth(null) → 0
maxDepth(null) → 0
```

Therefore:

```text
leftDepth  = 0
rightDepth = 0
```

We calculate:

```text
maxDepth(9) = 1 + max(0, 0)
            = 1
```

The result is `1` because node `9` itself counts as one node.

### Step 3: Return to the Root

Recursion returns to node `3`.

Now:

```text
leftDepth  = 1
rightDepth = 0
```

We calculate:

```text
maxDepth(3) = 1 + max(1, 0)
            = 2
```

**Final Output:**

```text
2
```

The longest path is:

```text
3 → 9
```

It contains exactly two nodes.

## 🔄 Understanding How Recursion Builds the Answer

The important idea is that recursion calculates the answer **from the bottom up**.

Consider:

```text
    3
   /
  9
 /
5
```

The recursive calls move downward until they reach `null`.

```text
maxDepth(3)
    ↓
maxDepth(9)
    ↓
maxDepth(5)
    ↓
maxDepth(null)
```

Then the results return upward:

```text
maxDepth(null) → 0
maxDepth(5)    → 1
maxDepth(9)    → 2
maxDepth(3)    → 3
```

Each node receives the depths of its children, chooses the larger one, and adds `1`.

This is why we do not need to manually track the depth while moving down the tree.

The recursive return values build the final answer.

## 🧩 Why Does a Leaf Have Depth 1?

Consider a tree containing only one node:

```text
    9
```

Its left child is `null`:

```text
maxDepth(null) = 0
```

Its right child is also `null`:

```text
maxDepth(null) = 0
```

Therefore:

```text
maxDepth(9) = 1 + max(0, 0)
            = 1
```

The `1` represents the leaf node itself.

### Important Rule

| Node Type | Maximum Depth |
|-----------|---------------|
| `null` | 0 |
| Leaf node | 1 |
| Parent of a leaf (with no deeper branch) | 2 |
| Grandparent (with no deeper branch) | 3 |

The current node always contributes `1` to the depth of its longest path.

## ⚠️ Node Count vs. Edge Count

One important concept in this problem is understanding how depth is defined.

Depending on the problem, tree depth or height may be measured using **nodes** or **edges**.

Consider:

```text
    3
   /
  9
 /
5
```

### Counting Edges

When measuring the depth of an individual node from the root using edges:

```text
Node 3 → depth 0
Node 9 → depth 1
Node 5 → depth 2
```

The longest root-to-leaf path contains **2 edges**.

### Counting Nodes

LeetCode 104 defines maximum depth using the number of nodes along the longest root-to-leaf path.

```text
3 → 9 → 5
```

This path contains **3 nodes**.

Therefore:

```text
Maximum Depth = 3
```

**Important:** Always check whether a tree problem defines depth or height using nodes or edges.

The definition determines the base case and the recursive formula.

## 🧠 Understanding the Recursive Formula

The entire solution is based on:

```text
depth(node) = 1 + max(
    depth(node.left),
    depth(node.right)
)
```

With the base case:

```text
depth(null) = 0
```

For example:

```text
        3
       / \
      9   20
         /  \
        15   7
```

The calculations are:

```text
depth(9)  = 1
depth(15) = 1
depth(7)  = 1

depth(20) = 1 + max(1, 1)
          = 2

depth(3)  = 1 + max(1, 2)
          = 3
```

The root receives the maximum depth of its subtrees and adds `1` for itself.

**Final Result: 3**

## ⏱️ Complexity Analysis

Let:

- `n` = Total number of nodes in the binary tree.
- `h` = Height of the binary tree.

### Time Complexity — O(n)

Every node is visited exactly once.

For each node, we perform constant-time work:

- Calculate the left subtree depth.
- Calculate the right subtree depth.
- Compare the two depths.
- Add `1`.

Therefore:

**Time Complexity: O(n)**

### Space Complexity — O(h)

The additional space is used by the recursive call stack.

The maximum number of active recursive calls depends on the height of the tree.

| Tree Structure | Space Complexity |
|----------------|------------------|
| Balanced tree | O(log n) |
| Completely skewed tree | O(n) |

Therefore, the worst-case auxiliary space complexity is **O(n)**.

## 🧠 Key Takeaways

- Maximum depth is the number of nodes along the longest root-to-leaf path.
- Recursive DFS is a natural approach for calculating tree depth.
- The base case `root == null` returns `0`.
- A leaf node returns `1` because it counts itself.
- Every node contributes `1` to the depth of its longest path.
- `Math.max()` selects the deeper subtree.
- Recursive calls calculate subtree depths and return their results upward.
- The final answer is built from the bottom of the tree back to the root.
- Tree depth and height can be defined using either nodes or edges, depending on the problem.
- Understanding the exact problem definition helps determine the correct base case.

### ⭐ Pattern to Remember

```java
if (root == null)
    return 0;

int leftDepth = maxDepth(root.left);
int rightDepth = maxDepth(root.right);

return 1 + Math.max(leftDepth, rightDepth);
```

This recursive pattern is useful in many binary tree problems where the answer depends on information returned from the left and right subtrees.

**Pattern Learned:** Binary Tree + DFS + Recursion + Bottom-Up Calculation

## 🔗 LeetCode Problem

[104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
