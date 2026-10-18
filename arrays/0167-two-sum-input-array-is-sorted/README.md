# 🎯 167. Two Sum II – Input Array Is Sorted

**Difficulty:** Medium 🟡  
**Topic:** Array / Two Pointers / Sorted Array  
**Language:** Java

## 📌 Problem Description

Given a **sorted integer array** `numbers` and an integer `target`, find two numbers whose sum equals the target.

Return their indices using **1-based indexing**.

The problem guarantees exactly one solution, and we cannot use the same element twice.

### Example

**Input:**

```text
numbers = [2, 7, 11, 15]
target = 9
```

**Output:**

```text
[1, 2]
```

Because:

```text
2 + 7 = 9
```

Their Java array indices are `0` and `1`, but the problem requires **1-based indexing**, so we return `[1, 2]`.

## 💡 Approach — Two Pointers

Since the array is already sorted, we can use the **Two Pointers** technique.

We initialize two pointers:

```java
int left = 0;
int right = numbers.length - 1;
```

- `left` starts at the beginning of the array.
- `right` starts at the end of the array.

For each iteration, we calculate:

```java
int sum = numbers[left] + numbers[right];
```

There are three possible situations:

1. **Sum equals target:** Return the two indices, adding `1` to each.
2. **Sum is greater than target:** Move `right` one position left to decrease the sum.
3. **Sum is smaller than target:** Move `left` one position right to increase the sum.

We repeat this process until we find the target.

## 💻 Java Solution

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int sum = numbers[left] + numbers[right];

            if (sum == target)
                return new int[]{left + 1, right + 1};

            else if (sum > target)
                right--;

            else
                left++;
        }

        return new int[]{};
    }
}
```

## 🔍 Example — Step-by-Step Walkthrough

Consider:

```text
numbers = [2, 7, 11, 15]
target = 9
```

Initially:

```text
Index:    0   1   2   3
Values:  [2,  7, 11, 15]
          ↑           ↑
         left        right
```

### Step 1

Calculate:

```text
2 + 15 = 17
```

Since `17 > 9`, the sum is too large.

Move `right` one position left.

### Step 2

Now:

```text
Index:    0   1   2   3
Values:  [2,  7, 11, 15]
          ↑       ↑
         left    right
```

Calculate:

```text
2 + 11 = 13
```

Again, `13 > 9`.

Move `right` left.

### Step 3

Now:

```text
Index:    0   1   2   3
Values:  [2,  7, 11, 15]
          ↑   ↑
         left right
```

Calculate:

```text
2 + 7 = 9
```

We found the target!

The Java indices are:

```text
left = 0
right = 1
```

Since the problem requires 1-based indexing:

```java
return new int[]{left + 1, right + 1};
```

**Final Output:**

```text
[1, 2]
```

## 🧩 Why Does Moving the Pointers Work?

The key reason is that the array is **sorted in ascending order**.

```text
Small --------------------→ Large
```

### When the Sum Is Too Large

```java
if (sum > target)
    right--;
```

We need a smaller sum.

Moving `right` left gives us a number that is smaller than or equal to the previous one.

### When the Sum Is Too Small

```java
else
    left++;
```

We need a larger sum.

Moving `left` right gives us a number that is greater than or equal to the previous one.

This allows us to eliminate impossible pairs without checking every combination.

## 🔄 Why Two Pointers Instead of HashMap?

The original **Two Sum** problem usually involves an unsorted array, where a HashMap is a common solution.

However, this problem gives us an important advantage:

**The input array is already sorted.**

Because of this, we can use Two Pointers without additional data structures.

| Approach | Time | Extra Space |
|---|---|---|
| HashMap | O(n) average | O(n) |
| Two Pointers | O(n) | O(1) |

Both approaches can achieve linear time, but Two Pointers uses **constant extra space**.

This also satisfies the problem's constant-space requirement.

## ⚠️ Understanding 1-Based Indexing

Java arrays normally use **0-based indexing**.

For example:

```text
Array:  [2, 7, 11, 15]
Index:   0  1   2   3
```

However, LeetCode 167 requires **1-based indexing**:

```text
Array:  [2, 7, 11, 15]
Index:   1  2   3   4
```

Therefore, we must return:

```java
return new int[]{left + 1, right + 1};
```

Instead of:

```java
return new int[]{left, right};
```

Forgetting this detail would produce an incorrect answer even if we found the correct pair.

## ⏱️ Complexity Analysis

### Time Complexity — O(n)

Each pointer moves in only one direction:

- `left` moves from left to right.
- `right` moves from right to left.

Each iteration moves at least one pointer, so we process at most O(n) positions.

**Time Complexity: O(n)**

### Space Complexity — O(1)

We only use a few variables:

- `left`
- `right`
- `sum`

No additional data structure grows with the input size.

**Auxiliary Space Complexity: O(1)**

## 🧠 Key Takeaways

- A sorted array is a strong hint to consider **Two Pointers**.
- Start one pointer at each end of the array.
- If the sum is too large, move the right pointer left.
- If the sum is too small, move the left pointer right.
- The sorted order allows us to eliminate impossible pairs efficiently.
- Two Pointers achieves O(n) time with O(1) auxiliary space.
- Always check whether a problem requires **0-based or 1-based indexing**.
- A sorted array can eliminate the need for a HashMap.

### ⭐ Pattern to Remember

```text
Two Sum + Unsorted Array
          ↓
        HashMap
          ↓
    Time: O(n)
    Space: O(n)


Two Sum + Sorted Array
          ↓
      Two Pointers
          ↓
    Time: O(n)
    Space: O(1)
```

**Pattern Learned:** Sorted Array + Two Pointers + Opposite-Direction Traversal

## 🔗 LeetCode Problem

[167. Two Sum II – Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)
