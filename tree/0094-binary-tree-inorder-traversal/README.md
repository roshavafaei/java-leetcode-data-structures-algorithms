# 🌳 94. Binary Tree Inorder Traversal

**Difficulty:** Easy 🟢  
**Topic:** Binary Tree / Depth-First Search (DFS) / Recursion  
**Language:** Java

## 📌 Problem Description

Given the root of a binary tree, return the **inorder traversal** of its nodes' values.

Inorder traversal follows this order:

**Left → Root → Right**

This means we visit the left subtree first, then the current node, and finally the right subtree.

## 💡 Approach — Recursive Inorder Traversal

We use **recursion** to traverse the binary tree.

For each node:

1. Recursively traverse the left subtree.
2. Recursively traverse the right subtree.
3. Create a result list.
4. Add the left subtree's values.
5. Add the current node's value.
6. Add the right subtree's values.
7. Return the combined list.

Although the right recursive call is evaluated before the lists are combined, the final result still follows **Left → Root → Right**.

### Base Case

When the current node is `null`, there is nothing to traverse.

```java
if (root == null)
    return new ArrayList<>();
```

Returning an empty list allows the recursive results to be combined without special handling.

## 💻 Java Solution

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        List<Integer> leftResult = inorderTraversal(root.left);
        List<Integer> rightResult = inorderTraversal(root.right);

        List<Integer> result = new ArrayList<>();

        result.addAll(leftResult);
        result.add(root.val);
        result.addAll(rightResult);

        return result;
    }
}
```

*Note: LeetCode provides the `TreeNode` class. The solution uses `List` and `ArrayList` from `java.util`.*

## 🔍 Example

Consider the following binary tree:

```text
        10
       /  \
      5    15
     / \
    2   7
```

**Inorder traversal:**

```text
Left → Root → Right
```

**Step-by-step results:**

| Recursive Call | Returned List |
|----------------|---------------|
| `inorderTraversal(2)` | `[2]` |
| `inorderTraversal(7)` | `[7]` |
| `inorderTraversal(5)` | `[2, 5, 7]` |
| `inorderTraversal(15)` | `[15]` |
| `inorderTraversal(10)` | `[2, 5, 7, 10, 15]` |

**Final Output:**

```text
[2, 5, 7, 10, 15]
```

## 🔄 Understanding Recursion

One of the most important concepts in this problem is understanding how **recursive calls and the call stack** work.

When a method calls itself, the current method does not disappear.

Instead, it pauses and waits for the recursive call to finish.

For example:

```text
inorderTraversal(10)
    ↓
inorderTraversal(5)
    ↓
inorderTraversal(2)
    ↓
inorderTraversal(null)
```

When `root == null`, the base case is reached and an empty list is returned.

The recursive calls then begin returning their results.

```text
inorderTraversal(null) → []
inorderTraversal(2)    → [2]
inorderTraversal(5)    → [2, 5, 7]
inorderTraversal(10)   → [2, 5, 7, 10, 15]
```

These results are shown after each corresponding subtree has been fully processed.

### How Does Execution Continue?

Consider these two lines:

```java
List<Integer> leftResult = inorderTraversal(root.left);
List<Integer> rightResult = inorderTraversal(root.right);
```

When the first recursive call starts, the current method pauses.

After the left subtree returns its result, execution continues from the **next line**, not from the beginning of the method.

Then the right subtree is processed.

Finally, both results are combined with the current node's value.

This is how the recursive call stack allows the algorithm to return to previous nodes.

## 🧩 Understanding add() vs. addAll()

Both methods are used when building the final traversal result.

**`add()` — Adds a single element**

```java
result.add(root.val);
```

For example:

```text
Before: [2, 5]
Add:    7
After:  [2, 5, 7]
```

**`addAll()` — Adds all elements from another collection**

```java
result.addAll(leftResult);
```

For example:

```text
result     = []
leftResult = [2, 5, 7]

After addAll:
result = [2, 5, 7]
```

In this solution, we combine the traversal results using:

```java
result.addAll(leftResult);
result.add(root.val);
result.addAll(rightResult);
```

This produces the correct inorder sequence.

## ⏱️ Complexity Analysis

Let:

- `n` = Number of nodes in the binary tree.
- `h` = Height of the binary tree.

### Time Complexity

**O(n log n) for a balanced tree**

**O(n²) in the worst case**

Every node is visited once, but this implementation also creates new lists and copies elements using `addAll()`.

For a balanced tree, the repeated copying takes O(n log n) time.

For a completely unbalanced tree, the same values may be copied repeatedly through many recursive levels, resulting in O(n²) time.

### Space Complexity

**O(n + h)** peak space, including the returned lists and recursion stack.

- The recursive call stack requires O(h) space.
- The lists created and retained during traversal require O(n) peak space.

For a balanced tree, `h = O(log n)`.

For a completely unbalanced tree, `h = O(n)`.

Therefore, the overall peak space complexity is **O(n)**.

### Possible Optimization

We can optimize the solution by using a **single shared result list** and adding node values directly during traversal.

That approach achieves:

- **Time:** O(n)
- **Auxiliary Space:** O(h), excluding the output list.

However, the current implementation is useful for understanding how recursive methods return and combine results.

## 🧠 Key Takeaways

- Inorder traversal follows **Left → Root → Right**.
- Recursion allows us to traverse a binary tree without manually managing a stack.
- The base case `root == null` stops recursive calls.
- Each recursive call pauses while deeper calls execute.
- When a recursive call returns, execution continues from the next statement.
- Recursive methods can return lists that are combined into a final result.
- `add()` inserts one element, while `addAll()` inserts all elements from another collection.
- Repeatedly combining lists is easy to understand but can be less efficient than using a shared result list.

**Pattern Learned:** Binary Tree + DFS + Recursion + Combining Recursive Results

## 🔗 LeetCode Problem

[94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)
