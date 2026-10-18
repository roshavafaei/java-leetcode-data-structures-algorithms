# 🌳 208. Implement Trie (Prefix Tree)

**Difficulty:** Medium 🟡  
**Topic:** Trie / Prefix Tree / HashMap  
**Language:** Java

## 📌 Problem Description

Implement a **Trie (Prefix Tree)** that supports three operations:

- `insert(word)` — Inserts a word into the Trie.
- `search(word)` — Returns `true` if the complete word exists in the Trie.
- `startsWith(prefix)` — Returns `true` if any stored word starts with the given prefix.

A Trie is a tree-based data structure used to store and search strings efficiently.

Each node represents a character, and words are formed by following paths from the root.

## 🌱 What Is a Trie?

Suppose we insert two words:

```text
apple
app
```

The Trie looks like this:

```text
root
 └── a
     └── p
         └── p  (*) 
             └── l
                 └── e  (*)
```

`(*)` indicates that `isEndOfWord = true`.

The word `"app"` ends at the second `p`, while `"apple"` continues through `l` and `e`.

**Important:** A path existing in the Trie does not necessarily mean that it represents a complete word.

## 💡 Approach — Trie with Shared Traversal

Each Trie node contains:

- A character value.
- A collection of child nodes.
- A boolean `isEndOfWord` indicating whether a complete word ends at that node.

The root node does not represent an actual character.

The main idea is to process strings **one character at a time**, moving through the corresponding child nodes.

We also use a helper method, `findLastNodeOf()`, to avoid repeating the same traversal logic in `search()` and `startsWith()`.

## 💻 Java Implementation

### 1. Insert a Word

To insert a word:

1. Start from the root.
2. Iterate through each character.
3. Create a child node if it does not already exist.
4. Move to the corresponding child.
5. Mark the final node as the end of a word.

```java
public void insert(String word) {
    Node current = root;

    for (char ch : word.toCharArray()) {
        if (!current.hasChild(ch))
            current.addChild(ch);

        current = current.getChild(ch);
    }

    current.isEndOfWord = true;
}
```

For example, `insert("cat")` creates:

```text
root
 └── c
     └── a
         └── t  (*)
```

### 2. Find the Last Node

Both `search()` and `startsWith()` need to follow the characters of a string through the Trie.

Instead of duplicating that logic, we create a private helper method.

```java
private Node findLastNodeOf(String str) {
    Node current = root;

    for (char ch : str.toCharArray()) {
        if (!current.hasChild(ch))
            return null;

        current = current.getChild(ch);
    }

    return current;
}
```

- If a character's path does not exist, return `null`.
- If the entire path exists, return its last node.

For example, if the Trie contains `"apple"`, calling `findLastNodeOf("app")` returns the node corresponding to the second `p`.

### 3. Search for a Complete Word

The `search()` method checks two conditions:

1. The entire path exists.
2. The last node is marked as `isEndOfWord`.

```java
public boolean search(String word) {
    Node node = findLastNodeOf(word);

    return node != null && node.isEndOfWord;
}
```

If the Trie contains only `"apple"`:

```text
search("apple") → true
search("app")   → false
search("xyz")   → false
```

Even though `"app"` exists as a path, it has not been inserted as a complete word.

### 4. Check a Prefix

The `startsWith()` method only checks whether a path exists.

It does **not** require `isEndOfWord` to be `true`.

```java
public boolean startsWith(String prefix) {
    Node node = findLastNodeOf(prefix);

    return node != null;
}
```

If the Trie contains `"apple"`:

```text
startsWith("app")   → true
startsWith("ap")    → true
startsWith("apple") → true
startsWith("xyz")   → false
```

## 🔍 Example

**Operations:**

```text
Trie trie = new Trie();

trie.insert("apple");

trie.search("apple");    // true
trie.search("app");      // false
trie.startsWith("app");  // true

trie.insert("app");

trie.search("app");      // true
```

**Step-by-step explanation:**

| Step | Operation | Result |
|------|-----------|--------|
| 1 | `insert("apple")` | Stores `"apple"` |
| 2 | `search("apple")` | `true` |
| 3 | `search("app")` | `false` |
| 4 | `startsWith("app")` | `true` |
| 5 | `insert("app")` | Marks `"app"` as a complete word |
| 6 | `search("app")` | `true` |

### Search vs. startsWith

This is the most important distinction:

| Input | `search()` | `startsWith()` |
|-------|------------|----------------|
| `"apple"` | `true` | `true` |
| `"app"` | `false` | `true` |
| `"ap"` | `false` | `true` |
| `"xyz"` | `false` | `false` |

*This comparison assumes that only `"apple"` has been inserted.*

- `search()` requires an existing path **and** `isEndOfWord = true`.
- `startsWith()` only requires an existing path.

## 🧩 Why Use a Helper Method?

Without `findLastNodeOf()`, both `search()` and `startsWith()` would contain almost identical traversal code.

By extracting the shared logic, each method has a clear responsibility:

| Method | Responsibility |
|--------|----------------|
| `insert()` | Add characters and mark the end of a word |
| `findLastNodeOf()` | Follow a path and return its last node |
| `search()` | Check whether a complete word exists |
| `startsWith()` | Check whether a prefix exists |

This approach reduces code duplication, improves readability, and makes the implementation easier to maintain.

## ⏱️ Complexity Analysis

Let `L` be the length of the input word or prefix.

**Time Complexity: O(L)**

| Operation | Time Complexity |
|-----------|-----------------|
| `insert()` | O(L) |
| `search()` | O(L) |
| `startsWith()` | O(L) |

Each operation processes the input string character by character.

These bounds assume child-node lookups and insertions take constant time on average.

**Space Complexity:**

- `insert()` — O(L) additional space in the worst case, when all characters require new nodes.
- `search()` — O(1) auxiliary space.
- `startsWith()` — O(1) auxiliary space.

The total memory used by the Trie depends on the number of unique character nodes stored.

## 🧠 Key Takeaways

- A Trie stores strings character by character.
- Words with common prefixes share the same nodes.
- `isEndOfWord` distinguishes complete words from prefixes.
- `search()` requires both an existing path and an end-of-word marker.
- `startsWith()` only checks whether the prefix path exists.
- `findLastNodeOf()` allows multiple methods to reuse the same traversal logic.
- Helper methods reduce code duplication and improve maintainability.
- Tries are especially useful for prefix searching and autocomplete systems.

## 🔗 LeetCode Problem

[208. Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/)
