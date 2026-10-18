# 🌳 226. Invert Binary Tree

**Difficulty:** Easy 🟢  
**Topic:** Binary Tree / Depth-First Search (DFS) / Recursion  
**Language:** Java

## 📌 Problem Description

Given the root of a binary tree, **invert the tree** and return its root.

Inverting a binary tree means swapping the left and right children of **every node**.

### Example

**Input:**

```text
        4
       / \
      2   7
     / \ / \
    1  3 6  9
```

**Output:**

```text
        4
       / \
      7   2
     / \ / \
    9  6 3  1
```

Every node has its left and right children swapped.

## 💡 Approach — Recursive DFS

We use **recursion** to invert the binary tree.

For every node:

1. Check whether the current node is `null`.
2. Swap its left and right children.
3. Recursively invert the new left subtree.
4. Recursively invert the new right subtree.
5. Return the current root.

The main idea is simple: **swap the children, then repeat the same operation for both subtrees.**

### Base Case

When the current node is `null`, there is nothing to invert.

```java
if (root == null)
    return null;
```

This stops the recursive calls when there are no more nodes to process.

## 💻 Java Solution

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {

        if (root == null)
            return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
```

*Note: LeetCode provides the `TreeNode` class.*

The solution modifies the original tree **in place**, without creating another tree.

## 🔍 Example — Step-by-Step Recursion

Consider this binary tree:

```text
        4
       / \
      2   7
     / \ / \
    1  3 6  9
```

### Step 1: Start at the Root

The first call is:

```java
invertTree(4);
```

We swap the left and right children of node `4`.

**Before:**

```text
    4
   / \
  2   7
```

**After:**

```text
    4
   / \
  7   2
```

The complete tree now looks like:

```text
        4
       / \
      7   2
     / \ / \
    6  9 1  3
```

The root's children have been swapped, but their own children have not been inverted yet.

### Step 2: Invert the Left Subtree

After the swap, `root.left` refers to node `7`.

The next recursive call is:

```java
invertTree(root.left);
```

Which means:

```java
invertTree(7);
```

Node `7` has:

```text
    7
   / \
  6   9
```

We swap its children:

```text
    7
   / \
  9   6
```

The recursive calls continue until the entire subtree is inverted.

### Step 3: Invert the Right Subtree

After finishing the left subtree, recursion returns to node `4`.

We execute:

```java
invertTree(root.right);
```

Which means:

```java
invertTree(2);
```

Before swapping:

```text
    2
   / \
  1   3
```

After swapping:

```text
    2
   / \
  3   1
```

### Step 4: Final Result

After both subtrees are inverted:

```text
        4
       / \
      7   2
     / \ / \
    9  6 3  1
```

**Final Output:**

```text
[4, 7, 2, 9, 6, 3, 1]
```

This array represents the tree in level-order format.

## 🔄 Understanding How Recursion Works

Each recursive call is responsible for inverting **one node and its subtrees**.

Consider:

```text
invertTree(4)
    |
    |-- invertTree(7)
    |       |
    |       |-- invertTree(9)
    |       |-- invertTree(6)
    |
    |-- invertTree(2)
            |
            |-- invertTree(3)
            |-- invertTree(1)
```

The process works as follows:

1. The current node swaps its children.
2. The method recursively processes the left subtree.
3. Once the left subtree is finished, execution returns to the current method.
4. The right subtree is processed.
5. The method returns the current root.

This is a form of **Depth-First Search (DFS)** because recursion explores a subtree before moving to the next one.

### Why Does the Method Return root?

```java
return root;
```

The original root node still exists after the inversion.

We only change its `left` and `right` references.

Therefore, we return the same root, now representing the inverted tree.

## 🧩 Why Don't We Check Each Child for null?

Initially, it may seem necessary to check:

```java
if (root.left == null)
```

Or:

```java
if (root.right == null)
```

However, separate conditions are unnecessary.

The swapping logic already handles `null` references correctly.

Consider:

```text
    4
   /
  2
```

Initially:

```text
root.left  = 2
root.right = null
```

We execute:

```java
TreeNode temp = root.left;
root.left = root.right;
root.right = temp;
```

After the swap:

```text
root.left  = null
root.right = 2
```

The tree becomes:

```text
    4
     \
      2
```

The swap works even when one child is `null`.

Then recursion handles the missing child through the base case:

```java
if (root == null)
    return null;
```

**Important:** We only need to check whether the current node itself is `null`, not whether each child is `null` before swapping.

## 🌱 Understanding In-Place Modification

This solution does not create a new binary tree.

Instead, it changes the existing nodes' references.

```java
TreeNode temp = root.left;
root.left = root.right;
root.right = temp;
```

The temporary variable prevents us from losing the original left child.

Without `temp`, assigning:

```java
root.left = root.right;
```

would overwrite the original left reference before we could assign it to `root.right`.

Using a temporary variable allows us to safely swap both references.

This is the same basic idea as swapping two variables.

## ⏱️ Complexity Analysis

Let:

- `n` = Total number of nodes in the binary tree.
- `h` = Height of the binary tree.

### Time Complexity — O(n)

Every node is visited exactly once.

For each node, we perform constant-time operations:

- Swap the left and right references.
- Make recursive calls for both subtrees.
- Return the current root.

Therefore:

**Time Complexity: O(n)**

### Space Complexity — O(h)

The algorithm modifies the tree in place, so it does not require another tree or additional data structures.

However, recursion uses the call stack.

The maximum recursion depth depends on the height of the tree.

| Tree Structure | Space Complexity |
|----------------|------------------|
| Balanced tree | O(log n) |
| Completely skewed tree | O(n) |

Therefore, the worst-case auxiliary space complexity is **O(n)**.

## 🧠 Key Takeaways

- Inverting a binary tree means swapping the left and right children of every node.
- Recursive DFS is a natural approach for processing every subtree.
- The base case `root == null` stops recursion.
- Each recursive call swaps the current node's children.
- The algorithm continues recursively through both subtrees.
- Separate checks for `root.left == null` and `root.right == null` are unnecessary.
- Swapping references works correctly even when one child is `null`.
- A temporary variable allows us to swap references without losing either child.
- The tree is modified **in place**, without creating a new tree.
- The original root is returned after the entire tree has been inverted.

### ⭐ Pattern to Remember

```java
if (root == null)
    return null;

// Swap children
TreeNode temp = root.left;
root.left = root.right;
root.right = temp;

// Recursively invert both subtrees
invertTree(root.left);
invertTree(root.right);

return root;
```

This recursive pattern is useful when a binary tree problem requires modifying every node using the same operation.

**Pattern Learned:** Binary Tree + DFS + Recursion + In-Place Modification

## 🔗 LeetCode Problem

[226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/)
