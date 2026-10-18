# 🌳 102. Binary Tree Level Order Traversal

**Difficulty:** Medium 🟡  
**Topic:** Binary Tree / Breadth-First Search (BFS) / Queue  
**Language:** Java

## 📌 Problem Description

Given the root of a binary tree, return the values of its nodes **level by level**, from left to right.

Each level should be stored in a separate list.

### Example

Consider the following binary tree:

```text
        3
       / \
      9   20
         /  \
        15   7
```

**Output:**

```text
[
    [3],
    [9, 20],
    [15, 7]
]
```

Each inner list represents one level of the tree.

## 💡 Approach — BFS with Queue

We use **Breadth-First Search (BFS)** to traverse the binary tree level by level.

BFS explores nodes in the order they are discovered, making a **Queue** the ideal data structure.

A Queue follows the **FIFO** principle:

**First In → First Out**

We store `TreeNode` objects in the queue rather than just their values because we need access to each node's left and right children.

```java
Queue<TreeNode> queue = new ArrayDeque<>();
queue.add(root);
```

The main idea is:

1. Start by adding the root to the queue.
2. Process all nodes belonging to the current level.
3. Add their children to the queue.
4. Store the current level's values in the result.
5. Repeat until the queue is empty.

## 🧩 Why Do We Use levelSize?

At the beginning of each level, we save the current size of the queue:

```java
int levelSize = queue.size();
```

This tells us exactly how many nodes belong to the **current level**.

For example:

```text
Queue = [9, 20]

levelSize = 2
```

We process exactly two nodes.

While processing them, their children may be added to the queue, but those children belong to the **next level**.

By saving `levelSize` before the loop, we prevent nodes from different levels from being mixed together.

```java
for (int i = 0; i < levelSize; i++) {
    // Process nodes from the current level
}
```

This is the key idea behind level-order traversal.

## 💻 Java Solution

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {

                TreeNode current = queue.remove();

                currentLevel.add(current.val);

                if (current.left != null)
                    queue.add(current.left);

                if (current.right != null)
                    queue.add(current.right);
            }

            result.add(currentLevel);
        }

        return result;
    }
}
```

*Note: LeetCode provides the `TreeNode` class.*

## 🔍 Example — Step-by-Step Traversal

Consider this tree:

```text
        3
       / \
      9   20
         /  \
        15   7
```

### Level 1

Initially, the queue contains only the root:

```text
Queue = [3]
levelSize = 1
```

Remove node `3` and add its value to the current level.

```text
currentLevel = [3]
```

Then add its children, `9` and `20`, to the queue.

```text
Queue = [9, 20]
```

**Result so far:**

```text
[[3]]
```

### Level 2

The queue now contains:

```text
Queue = [9, 20]
levelSize = 2
```

We process exactly two nodes.

- Remove `9` — it has no children.
- Remove `20` — add its children `15` and `7`.

```text
currentLevel = [9, 20]
Queue = [15, 7]
```

**Result so far:**

```text
[
    [3],
    [9, 20]
]
```

### Level 3

The queue contains:

```text
Queue = [15, 7]
levelSize = 2
```

Process both nodes.

Neither node has any children, so the queue becomes empty.

```text
currentLevel = [15, 7]
Queue = []
```

**Final Output:**

```text
[
    [3],
    [9, 20],
    [15, 7]
]
```

## 🔄 Understanding How the Queue Works

The queue controls the order in which nodes are visited.

For our example:

```text
        3
       / \
      9   20
         /  \
        15   7
```

The queue changes like this:

```text
[3]
 ↓
[9, 20]
 ↓
[15, 7]
 ↓
[]
```

At each iteration of the outer `while` loop, we process one complete level.

The inner `for` loop processes exactly `levelSize` nodes.

| Iteration | Current Level | Queue After Processing |
|-----------|---------------|------------------------|
| 1 | `[3]` | `[9, 20]` |
| 2 | `[9, 20]` | `[15, 7]` |
| 3 | `[15, 7]` | `[]` |

This allows us to separate the values of each level into individual lists.

## 🧠 BFS vs. DFS

In previous binary tree problems, we used **Depth-First Search (DFS)** with recursion.

For example:

```java
inorderTraversal(root.left);
inorderTraversal(root.right);
```

DFS explores deeper into a subtree before returning to other branches.

However, level-order traversal requires visiting nodes **horizontally**, one level at a time.

This is why BFS is a better fit.

| DFS | BFS |
|-----|-----|
| Explores one branch deeply | Explores nodes level by level |
| Often uses recursion or a stack | Typically uses a queue |
| Useful for preorder, inorder, and postorder traversals | Useful for level-order traversal |

**Important:** BFS uses the queue to remember which nodes should be processed next.

We do not need recursive calls to visit the left and right subtrees.

## ⏱️ Complexity Analysis

Let `n` be the total number of nodes in the binary tree.

### Time Complexity — O(n)

Every node is:

- Added to the queue once.
- Removed from the queue once.
- Processed once.

Each queue operation takes constant time.

Therefore:

**Time Complexity: O(n)**

### Space Complexity — O(n)

In the worst case, the queue may contain many nodes from the same level.

For example, in a complete binary tree, the last level can contain approximately half of all nodes.

Additionally, the result stores the values of all `n` nodes.

Therefore:

**Space Complexity: O(n)**

## 🧠 Key Takeaways

- Level-order traversal visits binary tree nodes from top to bottom and left to right.
- **BFS + Queue** is the standard approach for level-order traversal.
- A queue follows the FIFO principle: First In, First Out.
- We store `TreeNode` objects in the queue to access their children.
- `levelSize` tells us how many nodes belong to the current level.
- The inner `for` loop processes one complete level.
- Children added during the current level are processed in the next iteration.
- The outer `while` loop continues until every node has been visited.
- BFS is especially useful when a problem requires processing nodes level by level.

### ⭐ Pattern to Remember

```java
while (!queue.isEmpty()) {

    int levelSize = queue.size();

    for (int i = 0; i < levelSize; i++) {
        // Process the current node
        // Add its children to the queue
    }
}
```

This pattern can be reused in many BFS problems involving trees and graphs.

**Pattern Learned:** Binary Tree + BFS + Queue + Level-by-Level Processing

## 🔗 LeetCode Problem

[102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)
