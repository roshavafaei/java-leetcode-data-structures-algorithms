# 🌳 211. Design Add and Search Words Data Structure

**Difficulty:** Medium 🟡  
**Topic:** Trie / DFS / Recursion / Backtracking  
**Language:** Java

## 📌 Problem Description

Design a data structure that supports two operations:

- `addWord(word)` — Adds a word to the dictionary.
- `search(word)` — Returns `true` if the given word matches a previously added word.

The special character `.` acts as a **wildcard**, meaning it can match any single letter.

Unlike a normal Trie search, we must be able to explore multiple possible paths when a wildcard appears.

## 💡 Approach — Trie + Recursive DFS

We use a **Trie (Prefix Tree)** to store words character by character.

Each Trie node contains:

- `children` — A HashMap that maps characters to child nodes.
- `isEndOfWord` — A boolean indicating whether a complete word ends at the current node.
- `value` — The character represented by the node.

The root node does not represent an actual character.

### 1. Adding Words

To insert a word into the Trie:

1. Start from the root.
2. Process each character of the word.
3. Create a child node if it does not already exist.
4. Move to the corresponding child.
5. Mark the final node as the end of a complete word.

For example, inserting `"bad"`, `"dad"`, and `"mad"` creates:

```text
root
 ├── b
 │   └── a
 │       └── d (*)
 ├── d
 │   └── a
 │       └── d (*)
 └── m
     └── a
         └── d (*)
```

`(*)` indicates `isEndOfWord = true`.

### 2. Searching Words

We use **recursive Depth-First Search (DFS)** to find a matching word.

There are two possible cases.

**Case 1: Normal Character**

If the current character is not `.`, we follow its corresponding child node.

- If the child does not exist, return `false`.
- Otherwise, recursively search the next character.

```java
if (ch != '.') {
    if (!current.hasChild(ch))
        return false;

    return searchRecursive(
        current.getChild(ch),
        word,
        index + 1
    );
}
```

**Case 2: Wildcard `.`**

If the current character is `.`, it can match any single letter.

Therefore, we must explore **all children** of the current node.

```java
for (Node child : current.children.values()) {
    if (searchRecursive(child, word, index + 1))
        return true;
}

return false;
```

If any path produces a complete matching word, return `true`.

If all possible paths fail, return `false`.

This is where recursive DFS and backtracking become useful.

## 💻 Java Solution

```java
import java.util.HashMap;
import java.util.Map;

class WordDictionary {

    private class Node {
        private char value;
        private Map<Character, Node> children = new HashMap<>();
        private boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        public Node getChild(char ch) {
            return children.get(ch);
        }
    }

    private Node root = new Node(' ');

    public WordDictionary() {
    }

    public void addWord(String word) {
        Node current = root;

        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);

            current = current.getChild(ch);
        }

        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        return searchRecursive(root, word, 0);
    }

    private boolean searchRecursive(
            Node current, String word, int index) {

        if (index == word.length())
            return current.isEndOfWord;

        char ch = word.charAt(index);

        if (ch != '.') {
            if (!current.hasChild(ch))
                return false;

            return searchRecursive(
                current.getChild(ch),
                word,
                index + 1
            );
        }

        for (Node child : current.children.values()) {
            if (searchRecursive(child, word, index + 1))
                return true;
        }

        return false;
    }
}
```

## 🔍 Example

**Operations:**

```java
WordDictionary dictionary = new WordDictionary();

dictionary.addWord("bad");
dictionary.addWord("dad");
dictionary.addWord("mad");

dictionary.search("pad"); // false
dictionary.search("bad"); // true
dictionary.search(".ad"); // true
dictionary.search("b.."); // true
```

**Results:**

| Search Pattern | Result | Explanation |
|----------------|--------|-------------|
| `"pad"` | `false` | No matching word exists |
| `"bad"` | `true` | Exact word exists |
| `".ad"` | `true` | `.` matches `b`, `d`, or `m` |
| `"b.."` | `true` | The two dots match `a` and `d` |
| `"..."` | `true` | Matches any stored three-letter word |

## 🔄 Dry Run — Searching `".ad"`

Suppose the Trie contains:

```text
root
 ├── b ── a ── d (*)
 ├── d ── a ── d (*)
 └── m ── a ── d (*)
```

We call:

```java
dictionary.search(".ad");
```

**Step 1: Start at the root**

```text
current = root
index = 0
ch = '.'
```

Since the first character is `.`, we must explore the root's children.

**Step 2: Explore a possible child**

Suppose DFS first chooses the `b` node.

```text
current = b
index = 1
ch = 'a'
```

The character is not a wildcard, so we follow the `a` child.

**Step 3: Continue searching**

```text
current = a
index = 2
ch = 'd'
```

We follow the `d` child.

**Step 4: Reach the end of the pattern**

```text
current = d
index = 3
word.length() = 3
isEndOfWord = true
```

The base case is reached:

```java
if (index == word.length())
    return current.isEndOfWord;
```

Since `isEndOfWord` is `true`, the method returns `true`.

**Step 5: Return through the recursive calls**

The successful result propagates back through the recursive calls.

```text
d → true
a → true
b → true
root → true
```

**Final Output:**

```text
true
```

### What If a Branch Fails?

If the first explored child does not produce a match, DFS continues exploring the remaining children.

For example:

```text
Try first child → false
Try second child → false
Try third child → true
```

The search returns `true` as soon as one matching path is found.

**Important:** A failed branch does not mean the entire search has failed.

## 🧩 Why Use Recursion?

Normal Trie searches follow one specific path.

However, the wildcard `.` introduces multiple possibilities.

For example:

```text
Pattern: ".ad"

Possible matches:
bad
dad
mad
```

Recursion allows us to explore these possible paths without manually managing a stack.

Each recursive call represents the next character in the search pattern.

The base case checks whether we have reached the end of a complete stored word.

This approach combines:

- **Trie** for efficient string storage.
- **DFS** for exploring possible paths.
- **Recursion** for moving through the Trie.
- **Backtracking** for trying another path when one fails.

## ⏱️ Complexity Analysis

Let:

- `L` = Length of the input word or search pattern.
- `N` = Number of nodes in the Trie.
- `C` = Maximum number of children per node (26 for lowercase English letters).

### Time Complexity

| Operation | Time Complexity |
|-----------|-----------------|
| `addWord()` | O(L) |
| `search()` without wildcards | O(L) |
| `search()` with wildcards | O(C^L) worst case |

**Adding Words — O(L)**

We process each character once, assuming average constant-time HashMap operations.

**Searching Without Wildcards — O(L)**

Each character follows exactly one child node.

**Searching With Wildcards — O(C^L)**

Each wildcard can branch into multiple child nodes.

In the worst case, the search may explore an exponential number of possible paths.

The actual work is also bounded by the number of Trie nodes visited.

### Space Complexity

- **Adding a word:** O(L) additional space in the worst case for newly created nodes.
- **Recursive search:** O(L) auxiliary call-stack space.
- **Trie storage:** O(N).

## 🧠 Key Takeaways

- A Trie stores words character by character.
- `isEndOfWord` distinguishes complete words from prefixes.
- Normal characters follow one specific path.
- The wildcard `.` can match any single character.
- Wildcards require exploring multiple possible paths.
- Recursive DFS makes exploring those paths straightforward.
- A failed branch does not mean the entire search has failed.
- We return `true` as soon as one complete matching word is found.
- The base case ensures that we match complete words rather than prefixes.
- Combining Trie with DFS is useful for wildcard-based string searching.

**Pattern Learned:** Trie + DFS + Recursion + Backtracking

## 🔗 LeetCode Problem

[211. Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure/)
