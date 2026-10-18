# 🔢 347. Top K Frequent Elements

**Difficulty:** Medium 🟡  
**Topic:** HashMap / Hashing / Sorting / Frequency Counting  
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

| Number | Frequency |
|--------|-----------|
| 1 | 3 |
| 2 | 2 |
| 3 | 1 |

Since `k = 2`, we need the two numbers with the highest frequencies.

Therefore, the answer is:

```text
[1, 2]
```

## 💡 Approach — HashMap + Sorting

We use a **HashMap** to count the frequency of each number, then sort the unique numbers based on their frequencies.

The solution has four main steps:

1. Count the frequency of each number using a HashMap.
2. Extract all unique numbers into a list.
3. Sort the list by frequency in descending order.
4. Return the first `k` elements.

This approach keeps each number connected to its frequency throughout the solution.

## 💻 Java Solution

```java
import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        // Step 1: Count frequencies
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // Step 2: Get unique numbers
        List<Integer> keys = new ArrayList<>(map.keySet());

        // Step 3: Sort by frequency (descending)
        keys.sort((a, b) -> map.get(b) - map.get(a));

        // Step 4: Take the first k elements
        int[] answer = new int[k];

        for (int i = 0; i < k; i++) {
            answer[i] = keys.get(i);
        }

        return answer;
    }
}
```

## 🔍 Step-by-Step Explanation

### Step 1: Count the Frequencies

First, we create a HashMap:

```java
Map<Integer, Integer> map = new HashMap<>();
```

The map stores:

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

We use:

```java
for (int num : nums) {
    if (map.containsKey(num)) {
        map.put(num, map.get(num) + 1);
    } else {
        map.put(num, 1);
    }
}
```

If a number already exists in the map, we increase its frequency by `1`.

Otherwise, we insert it with an initial frequency of `1`.

### Step 2: Get the Unique Numbers

Next, we extract all keys from the HashMap:

```java
List<Integer> keys = new ArrayList<>(map.keySet());
```

The method:

```java
map.keySet()
```

returns the set of unique numbers stored as keys.

We convert that set into an `ArrayList` so we can sort the numbers.

For example:

```text
HashMap:

1 → 3
2 → 2
3 → 1
```

The list contains:

```text
keys = [1, 2, 3]
```

*Note: A HashMap does not guarantee the order of its keys, so the initial list order may differ.*

**Important:** The list contains the numbers themselves, not their frequencies.

### Step 3: Sort by Frequency

Now we sort the unique numbers based on the frequencies stored in the HashMap.

```java
keys.sort((a, b) -> map.get(b) - map.get(a));
```

This sorts the numbers from **highest frequency to lowest frequency**.

For example:

```text
50  → 3 times
7   → 2 times
100 → 1 time
```

After sorting:

```text
keys = [50, 7, 100]
```

Notice that we are **not sorting the numbers by their actual values**.

Instead, we are sorting them using:

```java
map.get(number)
```

which returns each number's frequency.

## 🧩 Understanding the Comparator

One of the most important parts of this solution is:

```java
keys.sort((a, b) -> map.get(b) - map.get(a));
```

Let's understand how it works.

A comparator decides the order of two elements.

In Java:

- A negative result means `a` should come before `b`.
- A positive result means `a` should come after `b`.
- Zero means both elements are considered equal for sorting.

### Example

Suppose:

```text
a = 1
b = 2
```

Their frequencies are:

```text
map.get(1) = 3
map.get(2) = 2
```

The comparator calculates:

```text
map.get(b) - map.get(a)

= 2 - 3

= -1
```

The result is negative, so `a` comes before `b`.

Therefore:

```text
1 comes before 2
```

### Ascending vs. Descending

To sort by frequency in ascending order:

```java
keys.sort((a, b) -> map.get(a) - map.get(b));
```

To sort by frequency in descending order:

```java
keys.sort((a, b) -> map.get(b) - map.get(a));
```

We need **descending order** because we want the most frequent elements first.

**Important:** For general Java code, `Integer.compare(map.get(b), map.get(a))` is a safer alternative because subtraction can overflow with sufficiently large integers. For this LeetCode problem's constraints, the original comparator works correctly.

## 🎯 Step 4: Take the First K Elements

After sorting, the most frequent numbers are at the beginning of the list.

For example:

```text
keys = [1, 2, 3]
k = 2
```

We only need:

```text
[1, 2]
```

First, create an array of size `k`:

```java
int[] answer = new int[k];
```

Then copy the first `k` elements:

```java
for (int i = 0; i < k; i++) {
    answer[i] = keys.get(i);
}
```

Finally:

```java
return answer;
```

## 🔄 Full Example Walkthrough

Consider:

```text
nums = [1, 1, 1, 2, 2, 3]
k = 2
```

**1. Build the frequency map**

```text
1 → 3
2 → 2
3 → 1
```

**2. Extract unique numbers**

```text
keys = [1, 2, 3]
```

**3. Sort by frequency**

```text
Number 1 → Frequency 3
Number 2 → Frequency 2
Number 3 → Frequency 1
```

Sorted list:

```text
keys = [1, 2, 3]
```

**4. Take the first k elements**

Since:

```text
k = 2
```

We take:

```text
[1, 2]
```

**Final Output:**

```text
[1, 2]
```

## 🌱 Why Sort the Numbers Instead of the Frequencies?

Consider this frequency map:

```text
10 → 4
20 → 2
30 → 3
```

If we only sorted the frequencies, we would get:

```text
[4, 3, 2]
```

But we need to return the original numbers, not their frequencies.

By sorting the keys using their values in the HashMap, we preserve the relationship between each number and its frequency.

For example:

```text
Before sorting:

10 → 4
20 → 2
30 → 3
```

After sorting the keys:

```text
[10, 30, 20]
```

This makes it easy to return the most frequent numbers directly.

## ⏱️ Complexity Analysis

Let:

- `n` = Number of elements in `nums`.
- `m` = Number of unique elements in `nums`.

### Time Complexity — O(n + m log m)

The solution has four main operations:

| Operation | Time Complexity |
|-----------|-----------------|
| Count frequencies using HashMap | O(n) average |
| Extract unique numbers | O(m) |
| Sort unique numbers | O(m log m) |
| Copy the first k elements | O(k) |

Therefore:

**Time Complexity: O(n + m log m)**

Since `m ≤ n` and `k ≤ m`, the worst-case time complexity is:

**O(n log n)**

This happens when every number is unique.

### Space Complexity — O(n)

We use:

- A HashMap storing up to `m` unique numbers.
- An ArrayList storing `m` keys.
- An answer array containing `k` elements.
- Additional space used by sorting.

Since `m ≤ n` and `k ≤ m`, the overall space complexity is:

**Space Complexity: O(n)**

## ⚡ Can We Optimize This Solution?

The HashMap + Sorting approach is straightforward and easy to understand, but sorting is not required to solve this problem.

Other approaches include:

| Approach | Time Complexity |
|----------|-----------------|
| HashMap + Sorting | O(n log n) worst case |
| HashMap + Min Heap | O(n + m log k) |
| HashMap + Bucket Sort | O(n) |

A **Bucket Sort** solution can achieve O(n) time by grouping numbers according to their frequencies.

However, the sorting approach is still a good solution for understanding frequency counting, HashMaps, and custom comparators.

## 🧠 Key Takeaways

- A HashMap is useful for counting the frequency of elements.
- We can store frequencies using the structure `number → frequency`.
- `map.keySet()` returns all unique keys from a HashMap.
- An `ArrayList` allows us to sort those keys.
- A custom comparator can sort elements based on values stored in another data structure.
- `map.get(b) - map.get(a)` sorts frequencies in descending order.
- Sorting the keys preserves the connection between each number and its frequency.
- After sorting, we only need to return the first `k` elements.
- The sorting-based solution takes O(n log n) time in the worst case.
- Bucket Sort can improve the time complexity to O(n).

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
Sort Numbers by Frequency
     ↓
Take First K Elements
     ↓
Result
```

**Pattern Learned:** Hashing + Frequency Counting + Custom Comparator + Sorting

## 🔗 LeetCode Problem

[347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)
