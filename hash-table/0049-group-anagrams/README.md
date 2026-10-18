# 🔤 49. Group Anagrams

**Difficulty:** Medium 🟡  
**Topic:** HashMap / Hashing / Sorting / String  
**Language:** Java

## 📌 Problem Description

Given an array of strings `strs`, group the **anagrams** together.

Two strings are anagrams if they contain the same characters with the same frequencies, but possibly in a different order.

### Example

**Input:**

```text
["eat", "tea", "tan", "ate", "nat", "bat"]
```

**Output:**

```text
[
    ["eat", "tea", "ate"],
    ["tan", "nat"],
    ["bat"]
]
```

The order of the groups does not matter.

## 💡 Approach — HashMap + Sorting

The key observation is that **anagrams become identical after sorting their characters**.

For example:

```text
eat → aet
tea → aet
ate → aet
```

All three words produce the same sorted string, so they belong to the same group.

We use a HashMap where:

```text
Sorted String → List of Original Strings
```

For example:

```text
"aet" → ["eat", "tea", "ate"]
"ant" → ["tan", "nat"]
"abt" → ["bat"]
```

The solution has four main steps:

1. Convert each word into a character array.
2. Sort its characters to create a unique key.
3. Use the sorted string as a HashMap key.
4. Add the original word to the corresponding group.

Finally, return all the grouped lists.

## 💻 Java Solution

```java
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);

            String sortedString = new String(chars);

            if (!map.containsKey(sortedString))
                map.put(sortedString, new ArrayList<>());

            map.get(sortedString).add(strs[i]);
        }

        return new ArrayList<>(map.values());
    }
}
```

## 🔍 Step-by-Step Explanation

### Step 1: Sort Each Word

A Java `String` cannot be directly sorted using `Arrays.sort()`.

First, convert it into a character array:

```java
char[] chars = strs[i].toCharArray();
```

Then sort the characters:

```java
Arrays.sort(chars);
```

Finally, convert the array back into a String:

```java
String sortedString = new String(chars);
```

For example:

```text
"tea"
   ↓
['t', 'e', 'a']
   ↓
['a', 'e', 't']
   ↓
"aet"
```

This sorted string becomes the key used for grouping.

### Step 2: Create a Group

If the sorted string does not exist in the HashMap, create a new list:

```java
if (!map.containsKey(sortedString))
    map.put(sortedString, new ArrayList<>());
```

For example:

```text
"aet" → []
```

Then add the original word:

```java
map.get(sortedString).add(strs[i]);
```

Now:

```text
"aet" → ["eat"]
```

If another word has the same sorted form, it is added to the existing list.

For example:

```text
"tea" → "aet"
```

Since `"aet"` already exists:

```text
"aet" → ["eat", "tea"]
```

We do not need to create another group.

## 🔄 Example Walkthrough

**Input:**

```text
["eat", "tea", "tan", "ate", "nat", "bat"]
```

We process each word:

| Word | Sorted Key | Action |
|---|---|---|
| `"eat"` | `"aet"` | Create group |
| `"tea"` | `"aet"` | Add to existing group |
| `"tan"` | `"ant"` | Create group |
| `"ate"` | `"aet"` | Add to existing group |
| `"nat"` | `"ant"` | Add to existing group |
| `"bat"` | `"abt"` | Create group |

After processing all words, the HashMap contains:

```text
"aet" → ["eat", "tea", "ate"]

"ant" → ["tan", "nat"]

"abt" → ["bat"]
```

The final result is:

```text
[
    ["eat", "tea", "ate"],
    ["tan", "nat"],
    ["bat"]
]
```

## 🧩 Why Do We Use map.values()?

Our HashMap stores:

```text
Key → Value
```

In this problem:

```text
"aet" → ["eat", "tea", "ate"]
```

The sorted key is only needed to identify which words belong together.

The final answer does not need the keys. It only needs the grouped lists.

We use:

```java
map.values()
```

This returns a collection containing all the lists stored in the HashMap.

Since the method must return `List<List<String>>`, we convert the collection into an ArrayList:

```java
return new ArrayList<>(map.values());
```

**Important:** The HashMap does not guarantee the order of the groups, but that is acceptable for this problem.

## ⏱️ Complexity Analysis

Let:

- `n` = Number of strings.
- `k` = Maximum length of a string.

### Time Complexity — O(n × k log k)

For each string:

- Converting it to a character array takes O(k).
- Sorting the characters takes O(k log k).
- Creating the sorted string takes O(k).
- Hashing the key takes O(k) on average.

We repeat these operations for all `n` strings.

Therefore:

**Time Complexity: O(n × k log k)**

### Space Complexity — O(n × k)

The HashMap stores the grouped strings and their sorted keys.

We also create temporary character arrays while processing words.

Therefore, the overall auxiliary space complexity is:

**Space Complexity: O(n × k)**

## 🧠 Key Takeaways

- Anagrams contain the same characters with the same frequencies.
- Sorting anagrams produces identical strings.
- A sorted string can be used as a **HashMap key**.
- `toCharArray()` converts a String into a character array.
- `Arrays.sort()` sorts the characters.
- `new String(chars)` converts the sorted array back into a String.
- A HashMap can store a **List of values for each key**.
- `computeIfAbsent()` is another way to create a group only when needed.
- `map.values()` retrieves all the grouped lists.
- Hashing is useful for grouping elements that share a common property.

### ⭐ Pattern to Remember

```text
Original Word
      ↓
Sort Characters
      ↓
Use Sorted Word as Key
      ↓
Add Original Word to HashMap Group
      ↓
Return All Groups
```

**Pattern Learned:** HashMap + Sorting + Grouping by Common Key

## 🔗 LeetCode Problem

[49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)
