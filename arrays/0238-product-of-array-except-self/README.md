# 🧮 238. Product of Array Except Self

**Difficulty:** Medium 🟡  
**Topic:** Array / Prefix Product / Suffix Product  
**Language:** Java

## 📌 Problem Description

Given an integer array `nums`, return an array `answer` where:

```text
answer[i] = product of all elements except nums[i]
```

The solution must:

- Run in **O(n)** time.
- Avoid using division.
- Use constant extra space, excluding the output array.

### Example

**Input:**

```text
nums = [1, 2, 3, 4]
```

**Output:**

```text
[24, 12, 8, 6]
```

**Explanation:**

```text
answer[0] = 2 × 3 × 4 = 24
answer[1] = 1 × 3 × 4 = 12
answer[2] = 1 × 2 × 4 = 8
answer[3] = 1 × 2 × 3 = 6
```

## 💡 Approach — Prefix + Suffix Products

The main challenge is calculating the product of all elements **except the current element**, without using division.

The key observation is:

```text
answer[i] = Prefix Product × Suffix Product
```

Where:

- **Prefix Product:** Product of all elements before index `i`.
- **Suffix Product:** Product of all elements after index `i`.

We solve this using two passes:

1. **Left to Right:** Calculate prefix products.
2. **Right to Left:** Multiply each prefix product by its suffix product.

This avoids checking the entire array for every index.

## 💻 Java Solution

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] answer = new int[n];

        int prefix = 1;

        // Step 1: Prefix products
        for (int i = 0; i < n; i++) {
            answer[i] = prefix;
            prefix *= nums[i];
        }

        int suffix = 1;

        // Step 2: Suffix products
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
        }

        return answer;
    }
}
```

## 🔍 Step-by-Step Explanation

### Step 1: Prefix Pass (Left → Right)

First, initialize:

```java
int prefix = 1;
```

Then iterate from left to right:

```java
for (int i = 0; i < n; i++) {
    answer[i] = prefix;
    prefix *= nums[i];
}
```

For each index, we store the product of all elements **before** it.

For example:

```text
nums = [1, 2, 3, 4]
```

| Index | Prefix Before Index | answer[i] |
|---|---|---|
| 0 | 1 | 1 |
| 1 | 1 | 1 |
| 2 | 1 × 2 | 2 |
| 3 | 1 × 2 × 3 | 6 |

After the prefix pass:

```text
answer = [1, 1, 2, 6]
```

**Important:** We assign `answer[i]` before multiplying `prefix` by `nums[i]`.

This ensures the current element is excluded.

### Step 2: Suffix Pass (Right → Left)

Next, initialize:

```java
int suffix = 1;
```

Then iterate from right to left:

```java
for (int i = n - 1; i >= 0; i--) {
    answer[i] *= suffix;
    suffix *= nums[i];
}
```

For each index:

1. Multiply its prefix product by the product of all elements to its right.
2. Update `suffix` to include the current element for the next iteration.

For example:

| Index | Prefix | Suffix | Final Value |
|---|---|---|---|
| 3 | 6 | 1 | 6 |
| 2 | 2 | 4 | 8 |
| 1 | 1 | 3 × 4 = 12 | 12 |
| 0 | 1 | 2 × 3 × 4 = 24 | 24 |

After the suffix pass:

```text
answer = [24, 12, 8, 6]
```

## 🧩 Why Do We Update After Assigning?

This is one of the most important details of the solution.

In the prefix pass:

```java
answer[i] = prefix;
prefix *= nums[i];
```

And in the suffix pass:

```java
answer[i] *= suffix;
suffix *= nums[i];
```

We always calculate the answer **before including the current element** in the running product.

Otherwise, `nums[i]` would incorrectly become part of `answer[i]`.

We also initialize both `prefix` and `suffix` to `1` because `1` is the multiplicative identity.

## 🔄 Full Example Walkthrough

**Input:**

```text
nums = [1, 2, 3, 4]
```

**Initial array:**

```text
answer = [0, 0, 0, 0]
```

**After the prefix pass:**

```text
answer = [1, 1, 2, 6]
```

**During the suffix pass:**

```text
i = 3 → answer[3] = 6 × 1  = 6
i = 2 → answer[2] = 2 × 4  = 8
i = 1 → answer[1] = 1 × 12 = 12
i = 0 → answer[0] = 1 × 24 = 24
```

**Final Output:**

```text
[24, 12, 8, 6]
```

## ⏱️ Complexity Analysis

### Time Complexity — O(n)

We traverse the array twice:

- Prefix pass: O(n)
- Suffix pass: O(n)

Therefore:

```text
O(n) + O(n) = O(n)
```

**Time Complexity: O(n)**

### Space Complexity — O(1)

We only use two running product variables:

- `prefix`
- `suffix`

The `answer` array is required for the output and is not counted as auxiliary space.

**Auxiliary Space Complexity: O(1)**

## 🧠 Key Takeaways

- Prefix products represent everything **before** the current index.
- Suffix products represent everything **after** the current index.
- Multiplying both gives the product of all elements except the current one.
- Update the running product **after** calculating `answer[i]`.
- Initializing prefix and suffix to `1` handles the boundaries correctly.
- Two passes are enough to achieve O(n) time.
- We do not need division or separate prefix and suffix arrays.
- This approach also handles arrays containing zeros.

### ⭐ Pattern to Remember

```text
         Input Array
              ↓
     Prefix Pass (→)
              ↓
   Store Left-Side Products
              ↓
     Suffix Pass (←)
              ↓
 Multiply Right-Side Products
              ↓
         Final Answer
```

**Pattern Learned:** Prefix Product + Suffix Product + Two-Pass Array Traversal

## 🔗 LeetCode Problem

[238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)
