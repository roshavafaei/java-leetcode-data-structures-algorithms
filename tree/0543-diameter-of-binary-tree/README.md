# 🌳 543. Diameter of Binary Tree

**Difficulty:** Easy 🟢  
**Topic:** Binary Tree / Depth-First Search (DFS) / Recursion  
**Language:** Java

## 📌 Problem Description

Given the root of a binary tree, return its **diameter**.

The diameter of a binary tree is the length of the longest path between any two nodes.

Two important rules:

- The path does **not necessarily pass through the root**.
- The path length is measured by the **number of edges**, not the number of nodes.

### Example

Consider the following binary tree:

```text
        1
       / \
      2   3
     / \
    4   5
```

One of the longest paths is:

```text
4 → 2 → 1 → 3
```

Another possible longest path is:

```text
5 → 2 → 1 → 3
```

Each path contains **3 edges**.

**Output:**

```text
3
```

## 💡 Approach — Recursive DFS + Height Calculation

We use **recursive Depth-First Search (DFS)** to calculate the diameter.

For every node:

1. Recursively calculate the height of its left subtree.
2. Recursively calculate the height of its right subtree.
3. Add both heights to calculate the longest path passing through the current node.
4. Update the maximum diameter found so far.
5. Return the current node's height to its parent.

The main idea is:

```java
diameter = Math.max(
    diameter,
    leftHeight + rightHeight
);
```

We calculate the diameter at **every node** because the longest path might not pass through the root.

### Base Case

When the current node is `null`, its height is `0`.

```java
if (root == null)
    return 0;
```

This allows the recursive calls to calculate heights correctly as they return up the tree.

## 💻 Java Solution

```java
class Solution {

    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }

    private int height(TreeNode root) {

        if (root == null)
            return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        diameter = Math.max(
            diameter,
            leftHeight + rightHeight
        );

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
```

*Note: LeetCode provides the `TreeNode` class.*

## 🔍 Example — Step-by-Step Recursion

Consider this tree:

```text
        1
       / \
      2   3
     / \
    4   5
```

We start with:

```java
height(1);
```

Before calculating the height of node `1`, we need the heights of its left and right subtrees.

Recursion continues downward until it reaches the leaf nodes.

### Step 1: Process Node 4

Node `4` has no children.

```text
    4
   / \
null null
```

Both recursive calls return `0`.

```text
leftHeight  = 0
rightHeight = 0
```

Diameter through node `4`:

```text
0 + 0 = 0
```

Height of node `4`:

```text
1 + max(0, 0) = 1
```

Therefore:

```text
height(4) = 1
```

### Step 2: Process Node 5

Node `5` is also a leaf.

```text
leftHeight  = 0
rightHeight = 0

Diameter through node 5 = 0
Height of node 5 = 1
```

### Step 3: Process Node 2

Now recursion returns to node `2`.

We already know:

```text
leftHeight  = height(4) = 1
rightHeight = height(5) = 1
```

Diameter through node `2`:

```text
1 + 1 = 2
```

This represents the path:

```text
4 → 2 → 5
```

The path contains **2 edges**.

Update the diameter:

```text
diameter = max(0, 2) = 2
```

Height of node `2`:

```text
1 + max(1, 1) = 2
```

Therefore:

```text
height(2) = 2
```

### Step 4: Process Node 3

Node `3` is a leaf.

```text
leftHeight  = 0
rightHeight = 0

Diameter through node 3 = 0
Height of node 3 = 1
```

The maximum diameter remains `2`.

### Step 5: Process Node 1

Finally, recursion returns to the root.

```text
leftHeight  = height(2) = 2
rightHeight = height(3) = 1
```

Diameter through node `1`:

```text
2 + 1 = 3
```

Update the maximum diameter:

```text
diameter = max(2, 3) = 3
```

Height of node `1`:

```text
1 + max(2, 1) = 3
```

**Final Output:**

```text
3
```

## 🔄 Understanding How Recursion Works

The recursive method calculates the height of each subtree **from the bottom up**.

For example:

```text
height(1)
    ↓
height(2)
    ↓
height(4)
    ↓
height(null)
```

Once the base case is reached, the recursive calls start returning their results.

```text
height(null) → 0
height(4)    → 1
height(2)    → 2
height(1)    → 3
```

While returning these heights, the algorithm also updates the maximum diameter.

| Node | Left Height | Right Height | Diameter Through Node | Returned Height |
|------|-------------|--------------|-----------------------|-----------------|
| 4 | 0 | 0 | 0 | 1 |
| 5 | 0 | 0 | 0 | 1 |
| 2 | 1 | 1 | 2 | 2 |
| 3 | 0 | 0 | 0 | 1 |
| 1 | 2 | 1 | 3 | 3 |

The largest diameter found is **3**.

## 🧩 Height vs. Diameter

This is the most important distinction in the problem.

### Height — Choose One Side

Height answers:

*What is the longest downward path starting from this node?*

We calculate:

```java
return 1 + Math.max(leftHeight, rightHeight);
```

We choose the larger subtree because a downward path can continue through only one child.

The `1` counts the current node.

### Diameter — Use Both Sides

Diameter through a node answers:

*What is the longest path connecting nodes through the current node?*

We calculate:

```java
leftHeight + rightHeight
```

We use both sides because the path can extend into the left subtree and the right subtree.

### Key Difference

| Height | Diameter Through a Node |
|--------|-------------------------|
| Uses the deeper subtree | Uses both subtrees |
| `1 + max(left, right)` | `left + right` |
| Returned to the parent | Used to update the maximum diameter |

**Remember:**

```text
HEIGHT:
1 + max(leftHeight, rightHeight)

DIAMETER:
leftHeight + rightHeight
```

## 🌱 Why Do We Check Every Node?

The longest path does not always pass through the root.

Consider:

```text
        1
       /
      2
     / \
    3   4
   /     \
  5       6
```

One of the longest paths is:

```text
5 → 3 → 2 → 4 → 6
```

This path contains **4 edges** and does not pass through node `1`.

If we calculated the diameter only at the root, we would miss this path.

Therefore, at every node, we calculate:

```java
leftHeight + rightHeight
```

And update:

```java
diameter = Math.max(
    diameter,
    leftHeight + rightHeight
);
```

The maximum value found anywhere in the tree is the final diameter.

## ❓ Why Is There No -1?

At first, it may seem that we should calculate:

```text
leftHeight + rightHeight - 1
```

because our height function counts nodes, while diameter counts edges.

However, consider:

```text
    2
   / \
  4   5
```

We get:

```text
leftHeight  = 1
rightHeight = 1
```

Therefore:

```text
diameter = 1 + 1 = 2
```

The actual path is:

```text
4 → 2 → 5
```

It contains exactly **2 edges**.

Each subtree height counts the number of edges needed to travel from the current node to the deepest node on that side.

Therefore:

```text
diameter = leftHeight + rightHeight
```

already gives us the correct number of edges.

**No subtraction is necessary.**

## 🧠 Why Do We Return Height Instead of Diameter?

Our helper method is:

```java
private int height(TreeNode root)
```

But the problem asks for the diameter.

Why does the method return height?

Because the parent node needs to know the heights of its left and right subtrees.

```java
int leftHeight = height(root.left);
int rightHeight = height(root.right);
```

We use these heights for two different purposes.

**1. Update the diameter**

```java
diameter = Math.max(
    diameter,
    leftHeight + rightHeight
);
```

**2. Return the height to the parent**

```java
return 1 + Math.max(leftHeight, rightHeight);
```

The recursive helper method effectively has two responsibilities:

- Calculate and return the height of the current subtree.
- Update the maximum diameter found so far.

The `diameter` field stores the answer, while the recursive return value provides information needed by the parent.

This is a useful pattern in recursive tree problems: **return one value while updating another**.

## ⏱️ Complexity Analysis

Let:

- `n` = Total number of nodes in the binary tree.
- `h` = Height of the binary tree.

### Time Complexity — O(n)

Every node is visited exactly once.

For each node, we perform constant-time operations:

- Calculate the left and right heights from recursive results.
- Update the maximum diameter.
- Return the current subtree's height.

Therefore:

**Time Complexity: O(n)**

### Space Complexity — O(h)

The additional space is used by the recursive call stack.

The maximum recursion depth depends on the height of the tree.

| Tree Structure | Space Complexity |
|----------------|------------------|
| Balanced tree | O(log n) |
| Completely skewed tree | O(n) |

Therefore, the worst-case auxiliary space complexity is **O(n)**.

## 🧠 Key Takeaways

- The diameter is the longest path between any two nodes, measured in edges.
- The longest path does not necessarily pass through the root.
- We must calculate the possible diameter at every node.
- Recursive DFS allows us to calculate subtree heights from the bottom up.
- The base case `root == null` returns `0`.
- Height uses the larger of the two subtree heights.
- Diameter through a node uses the sum of both subtree heights.
- `leftHeight + rightHeight` already gives the correct number of edges.
- The helper method returns height because the parent needs that information.
- A separate variable stores the maximum diameter found during traversal.
- One recursive traversal can calculate subtree information and update a global result.

### ⭐ Pattern to Remember

```java
int leftHeight = height(root.left);
int rightHeight = height(root.right);

// Update the maximum diameter
diameter = Math.max(
    diameter,
    leftHeight + rightHeight
);

// Return height to the parent
return 1 + Math.max(leftHeight, rightHeight);
```

**Pattern Learned:** Binary Tree + DFS + Recursion + Bottom-Up Height Calculation + Global Maximum

## 🔗 LeetCode Problem

[543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/)
