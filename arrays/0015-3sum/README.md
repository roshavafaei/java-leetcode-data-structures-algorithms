# 🎯 15. 3Sum

**Difficulty:** Medium 🟡  
**Topic:** Array / Sorting / Two Pointers  
**Language:** Java

## 📌 Problem Description

Given an integer array `nums`, return all **unique triplets** whose sum equals zero.

Each triplet must satisfy:

```text
nums[i] + nums[j] + nums[k] == 0
```

The three indices must be different, and the result must not contain duplicate triplets.

### Example

**Input:**

```text
nums = [-1, 0, 1, 2, -1, -4]
```

**Output:**

```text
[
    [-1, -1, 2],
    [-1, 0, 1]
]
```

Both triplets sum to zero, and each appears only once.

## 💡 Approach — Sorting + Two Pointers

Instead of checking every combination using three nested loops, we can solve this problem using **Sorting + Two Pointers**.

First, sort the array:

```text
Original: [-1, 0, 1, 2, -1, -4]
Sorted:   [-4, -1, -1, 0, 1, 2]
```

Then, for each index `i`:

1. Fix `nums[i]` as the first number.
2. Set `left = i + 1` and `right = nums.length - 1`.
3. Calculate the sum of the three numbers.
4. Move the pointers depending on the sum.
5. Skip duplicates to avoid repeated triplets.

The pointer movement follows three rules:

| Condition | Action |
|---|---|
| `sum == 0` | Save the triplet and move both pointers |
| `sum < 0` | Move `left++` to increase the sum |
| `sum > 0` | Move `right--` to decrease the sum |

## 💻 Java Solution

```java
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    result.add(Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                    ));

                    left++;
                    right--;

                    while (left < right &&
                           nums[left] == nums[left - 1])
                        left++;

                    while (left < right &&
                           nums[right] == nums[right + 1])
                        right--;

                } else if (sum < 0) {

                    left++;

                } else {

                    right--;
                }
            }
        }

        return result;
    }
}
```

## 🔍 Step-by-Step Explanation

### Step 1: Sort the Array

```java
Arrays.sort(nums);
```

Sorting allows us to use Two Pointers efficiently.

Since the numbers are ordered:

- Moving `left` right gives us a larger or equal value.
- Moving `right` left gives us a smaller or equal value.

This helps us adjust the sum without checking every possible triplet.

### Step 2: Fix the First Number

```java
for (int i = 0; i < nums.length - 2; i++)
```

We select one number using `i`.

Then initialize:

```java
int left = i + 1;
int right = nums.length - 1;
```

For example:

```text
[-4, -1, -1, 0, 1, 2]
  ↑   ↑              ↑
  i  left           right
```

The two pointers search for a pair that makes the total sum zero.

### Step 3: Calculate the Sum

```java
int sum = nums[i] + nums[left] + nums[right];
```

There are three possibilities.

**If `sum == 0`:**

```java
result.add(Arrays.asList(
    nums[i], nums[left], nums[right]
));
```

We found a valid triplet. Save it, then move both pointers inward.

**If `sum < 0`:**

```java
left++;
```

The sum is too small, so we need a larger number.

**If `sum > 0`:**

```java
right--;
```

The sum is too large, so we need a smaller number.

## 🧩 Handling Duplicate Triplets

The problem requires **unique triplets**, so skipping duplicates is essential.

### 1. Skip Duplicate `i` Values

```java
if (i > 0 && nums[i] == nums[i - 1])
    continue;
```

If the current fixed number is the same as the previous one, we skip it.

This prevents us from repeating the same search with the same starting value.

### 2. Skip Duplicate `left` Values

After finding a valid triplet, we move `left` forward:

```java
left++;
```

Then skip repeated values:

```java
while (left < right && nums[left] == nums[left - 1])
    left++;
```

For example:

```text
[-2, 0, 0, 0, 2]
```

After using the first `0`, we skip the remaining duplicate zeros instead of producing the same triplet again.

### 3. Skip Duplicate `right` Values

Similarly, after moving `right` backward:

```java
right--;
```

We skip duplicates:

```java
while (left < right && nums[right] == nums[right + 1])
    right--;
```

**Important difference:**

```text
left  → compare with left - 1
right → compare with right + 1
```

This is because `left` moves forward while `right` moves backward.

## 🔄 Example Walkthrough

**Input:**

```text
nums = [-1, 0, 1, 2, -1, -4]
```

After sorting:

```text
[-4, -1, -1, 0, 1, 2]
```

When `i` points to the first `-1`:

```text
[-4, -1, -1, 0, 1, 2]
      ↑   ↑          ↑
      i  left       right
```

Calculate:

```text
-1 + (-1) + 2 = 0
```

We found:

```text
[-1, -1, 2]
```

Add it to the result and move both pointers.

Now:

```text
-1 + 0 + 1 = 0
```

We found another triplet:

```text
[-1, 0, 1]
```

After skipping duplicates and completing the search, the final result is:

```text
[
    [-1, -1, 2],
    [-1, 0, 1]
]
```

## ⏱️ Complexity Analysis

### Time Complexity — O(n²)

Sorting takes:

**O(n log n)**

The outer loop runs O(n) times, and for each fixed number, the Two Pointers search takes at most O(n).

Therefore:

```text
O(n log n) + O(n²) = O(n²)
```

**Time Complexity: O(n²)**

### Space Complexity — O(1) Auxiliary Space

Ignoring the output and the sorting implementation, the algorithm only uses a few variables:

- `i`
- `left`
- `right`
- `sum`

The Two Pointers search itself uses **O(1)** extra space.

Java's `Arrays.sort(int[])` may use additional stack space, so the complete implementation's auxiliary space is not strictly O(1).

The output list also requires space proportional to the number of triplets found.

## 🧠 Key Takeaways

- Sorting makes the **Two Pointers** technique possible.
- Fix one number and search for the other two using opposite-direction pointers.
- If the sum is too small, move `left++`.
- If the sum is too large, move `right--`.
- If the sum is zero, save the triplet and move both pointers.
- Skip duplicate values for `i`, `left`, and `right`.
- Sorting + Two Pointers reduces the time complexity from O(n³) to **O(n²)**.

### ⭐ Pattern to Remember

```text
       Sort the Array
              ↓
      Fix One Number (i)
              ↓
   left →         ← right
              ↓
       Calculate Sum
              ↓
    ┌─────────┼─────────┐
    ↓         ↓         ↓
 sum < 0   sum == 0   sum > 0
    ↓         ↓         ↓
  left++   Save Triplet right--
              ↓
       Skip Duplicates
```

**Pattern Learned:** Sorting + Two Pointers + Duplicate Handling

## 🔗 LeetCode Problem

[15. 3Sum](https://leetcode.com/problems/3sum/)
