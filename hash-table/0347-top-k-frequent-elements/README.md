# 🔢 347. Top K Frequent Elements

**Difficulty:** Medium 🟡  
**Topic:** HashMap / Sorting / Frequency Counting  
**Language:** Java

## 📌 Problem Description

Given an integer array `nums` and an integer `k`, return the **k most frequent elements**.

The answer can be returned in any order.

### Example

**Input:**
```text
nums = [1, 1, 1, 2, 2, 3]
k = 2
```

**Output:**
```text
[1, 2]
```

The frequencies are:

```text
1 → 3 times
2 → 2 times
3 → 1 time
```

Since `k = 2`, we return the two numbers with the highest frequencies.

## 💡 Approach — HashMap + Sorting

I solved this problem using a **HashMap** and **Sorting**.

The solution has four main steps:

1. Count how many times each number appears using a HashMap.
2. Extract all unique numbers into an ArrayList.
3. Sort the numbers based on their frequencies in descending order.
4. Return the first `k` elements.

The main idea is to keep each number connected to its frequency while sorting.

## 💻 Java Solution

```java
import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        // Count frequencies
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // Get unique numbers
        List<Integer> keys = new ArrayList<>(map.keySet());

        // Sort by frequency (descending)
        keys.sort((a, b) -> map.get(b) - map.get(a));

        // Take the first k elements
        int[] answer = new int[k];

        for (int i = 0; i < k; i++) {
            answer[i] = keys.get(i);
        }

        return answer;
    }
}
```

## 🔍 Step-by-Step Explanation

### Step 1: Count Frequencies

We use a HashMap to store:

```text
number → frequency
```

For example:

```text
nums = [1, 1, 1, 2, 2, 3]
```

After counting:

```text
1 → 3
2 → 2
3 → 1
```

If a number already exists in the map, we increase its frequency by `1`. Otherwise, we insert it with a frequency of `1`.

### Step 2: Extract Unique Numbers

```java
List<Integer> keys = new ArrayList<>(map.keySet());
```

`map.keySet()` returns all unique numbers in the HashMap.

For our example, the list contains:

```text
keys = [1, 2, 3]
```

The list contains the **numbers themselves**, not their frequencies.

The initial order is not guaranteed because HashMap does not preserve insertion order.

### Step 3: Sort by Frequency

```java
keys.sort((a, b) -> map.get(b) - map.get(a));
```

This sorts the numbers from highest frequency to lowest frequency.

For example:

```text
50  → frequency 3
7   → frequency 2
100 → frequency 1
```

After sorting:

```text
keys = [50, 7, 100]
```

Notice that we are sorting by **frequency**, not by the actual numerical values.

### Step 4: Return the First K Elements

After sorting, the most frequent numbers appear at the beginning.

```java
int[] answer = new int[k];

for (int i = 0; i < k; i++) {
    answer[i] = keys.get(i);
}
```

For:

```text
keys = [1, 2, 3]
k = 2
```

We return:

```text
[1, 2]
```

## 🧩 Understanding the Comparator

One of the most important parts of this solution is:

```java
keys.sort((a, b) -> map.get(b) - map.get(a));
```

The comparator compares the frequencies of two numbers.

Suppose:

```text
a = 1
b = 2

map.get(1) = 3
map.get(2) = 2
```

The comparator calculates:

```text
map.get(b) - map.get(a)
= 2 - 3
= -1
```

A negative result means `a` should come before `b`.

Therefore, `1` comes before `2`.

**Remember:**

```java
// Ascending frequency
keys.sort((a, b) -> map.get(a) - map.get(b));

// Descending frequency
keys.sort((a, b) -> map.get(b) - map.get(a));
```

For this problem, we need descending order.

*Note: `Integer.compare(map.get(b), map.get(a))` is a safer alternative in general Java code because subtraction can overflow.*

## 🔄 Full Example Walkthrough

**Input:**

```text
nums = [1, 1, 1, 2, 2, 3]
k = 2
```

**1. Count frequencies:**

```text
HashMap:
1 → 3
2 → 2
3 → 1
```

**2. Extract unique numbers:**

```text
[1, 2, 3]
```

**3. Sort by frequency:**

```text
1 (frequency 3)
2 (frequency 2)
3 (frequency 1)
```

**4. Take the first 2 elements:**

```text
[1, 2]
```

**Final Output:**

```text
[1, 2]
```

## ⏱️ Complexity Analysis

Let:

- `n` = Number of elements in `nums`.
- `m` = Number of unique elements.

### Time Complexity — O(n + m log m)

- Counting frequencies: O(n) average.
- Extracting unique numbers: O(m).
- Sorting the unique numbers: O(m log m).
- Returning the first `k` elements: O(k).

Since `m ≤ n`, the worst-case time complexity is **O(n log n)**.

### Space Complexity — O(n)

We use a HashMap, an ArrayList of unique numbers, and an answer array.

In the worst case, every number is unique, so the space complexity is **O(n)**.

## 🧠 Key Takeaways

- A **HashMap** is useful for counting element frequencies.
- `map.keySet()` returns all unique keys.
- We can convert those keys into an ArrayList for sorting.
- A custom comparator allows sorting based on values stored in a HashMap.
- Sorting by frequency keeps each number connected to its count.
- After sorting, we only need the first `k` elements.
- The sorting approach is easy to understand, but **Bucket Sort** can solve this problem in O(n) time.

### ⭐ Pattern to Remember

```text
Input Array
    ↓
Count Frequencies
    ↓
HashMap<Number, Frequency>
    ↓
Extract Unique Numbers
    ↓
Sort by Frequency (Descending)
    ↓
Take First K Elements
    ↓
Result
```

**Pattern Learned:** HashMap + Frequency Counting + Custom Comparator + Sorting

## 🔗 LeetCode Problem

[347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)
