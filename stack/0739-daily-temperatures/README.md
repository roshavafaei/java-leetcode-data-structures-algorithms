# 🌡️ 739. Daily Temperatures

**Difficulty:** Medium 🟡  
**Topic:** Stack / Monotonic Stack / Array  
**Language:** Java

## 📌 Problem Description

Given an array of daily temperatures, return an array `answer` where:

- `answer[i]` represents the number of days we must wait after day `i` to get a **warmer temperature**.
- If there is no warmer day in the future, `answer[i]` should be `0`.

### Example

**Input:**

```text
temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
```

**Output:**

```text
answer = [1, 1, 4, 2, 1, 1, 0, 0]
```

For example:

- Day `0` has temperature `73`. The next warmer day is day `1`, so the answer is `1`.
- Day `2` has temperature `75`. The next warmer day is day `6`, so the answer is `4`.
- Day `6` has temperature `76`. No warmer day exists afterward, so the answer is `0`.

## 💡 Approach — Monotonic Stack

We use a **Monotonic Stack** to keep track of days whose next warmer temperature has not been found yet.

Instead of storing temperatures directly, we store their **indices**.

Why?

Because we need to calculate the number of days between two temperatures.

For every temperature:

1. Check whether the stack is empty.
2. Compare the current temperature with the temperature at the index on top of the stack.
3. If the current temperature is warmer, we have found the answer for that previous day.
4. Pop the previous index and calculate the number of days between them.
5. Continue while the current temperature is warmer than the temperature at the top.
6. Push the current index onto the stack.

The stack keeps indices whose temperatures are in **monotonically decreasing (non-increasing) order** from bottom to top.

### Why Store Indices Instead of Temperatures?

Consider:

```text
temperatures = [73, 71, 69, 72]
```

When we reach temperature `72` at index `3`, we need to calculate how long each previous day waited.

For temperature `69` at index `2`:

```text
answer[2] = 3 - 2 = 1
```

For temperature `71` at index `1`:

```text
answer[1] = 3 - 1 = 2
```

If we only stored the temperatures, we would not directly know their original positions.

By storing indices, we can access both:

```java
temperatures[stack.peek()]
```

And:

```java
i - prevIndex
```

## 💻 Java Solution

```java
import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {

            while (!stack.empty() &&
                   temperatures[i] > temperatures[stack.peek()]) {

                int prevIndex = stack.pop();
                answer[prevIndex] = i - prevIndex;
            }

            stack.push(i);
        }

        return answer;
    }
}
```

## 🔍 Example — Step-by-Step Walkthrough

Consider:

```text
temperatures = [73, 71, 69, 72]
```

Initially:

```text
stack = []
answer = [0, 0, 0, 0]
```

### Step 1: Process Temperature 73

```text
i = 0
temperature = 73
```

The stack is empty, so we push index `0`.

```text
stack = [0]
```

No answer has been found yet.

### Step 2: Process Temperature 71

```text
i = 1
temperature = 71
```

Compare with the temperature at the top of the stack:

```text
71 > 73 → false
```

Since the current temperature is not warmer, push index `1`.

```text
stack = [0, 1]
```

### Step 3: Process Temperature 69

```text
i = 2
temperature = 69
```

Compare:

```text
69 > 71 → false
```

Push index `2`.

```text
stack = [0, 1, 2]
```

The temperatures represented by the stack are:

```text
[73, 71, 69]
```

### Step 4: Process Temperature 72

```text
i = 3
temperature = 72
```

Now the current temperature is warmer than some previous temperatures.

**First comparison:**

The top of the stack is index `2`.

```text
temperatures[2] = 69

72 > 69 → true
```

Pop index `2` and calculate:

```text
answer[2] = 3 - 2 = 1
```

Now:

```text
stack = [0, 1]
answer = [0, 0, 1, 0]
```

**Second comparison:**

The top of the stack is index `1`.

```text
temperatures[1] = 71

72 > 71 → true
```

Pop index `1` and calculate:

```text
answer[1] = 3 - 1 = 2
```

Now:

```text
stack = [0]
answer = [0, 2, 1, 0]
```

**Third comparison:**

The top of the stack is index `0`.

```text
temperatures[0] = 73

72 > 73 → false
```

We stop popping because `72` is not warmer than `73`.

Finally, push index `3`.

```text
stack = [0, 3]
```

**Final Output:**

```text
[0, 2, 1, 0]
```

Days `0` and `3` have no warmer temperature afterward, so their answers remain `0`.

## 🔄 Understanding the Monotonic Stack

A monotonic stack maintains elements in a specific order.

In this problem, the stack stores indices whose temperatures are in **non-increasing order**.

For example:

```text
Temperatures: [73, 71, 69]

Stack indices: [0, 1, 2]
Stack values:  [73, 71, 69]
```

When a warmer temperature arrives, it removes previous colder temperatures from the top.

For example, when `72` arrives:

```text
Before:

Stack values: [73, 71, 69]

72 > 69 → pop
72 > 71 → pop
72 > 73 → false

After:

Stack values: [73, 72]
```

This allows us to find the next warmer day efficiently without checking every future day separately.

## 🧩 Why Do We Use a while Loop Instead of if?

Consider:

```text
temperatures = [73, 71, 69, 72]
```

When we reach `72`, it is warmer than both `69` and `71`.

If we used an `if` statement, we would only pop one index.

However, the `while` loop allows us to process **multiple previous days** using the same current temperature.

```java
while (!stack.empty() &&
       temperatures[i] > temperatures[stack.peek()]) {

    int prevIndex = stack.pop();
    answer[prevIndex] = i - prevIndex;
}
```

The loop continues until:

- The stack becomes empty, or
- The temperature at the top is greater than or equal to the current temperature.

This is what makes the monotonic stack approach work correctly.

## 🌱 Why Are Some Answers Still Zero?

We initialize the answer array as:

```java
int[] answer = new int[temperatures.length];
```

In Java, an integer array is automatically initialized with zeros.

For example:

```text
[0, 0, 0, 0]
```

We only update an answer when we find a warmer day.

```java
answer[prevIndex] = i - prevIndex;
```

If an index remains in the stack after processing the entire array, it means no warmer temperature was found for that day.

Its answer correctly remains `0`.

Therefore, we do not need an additional loop to handle the remaining indices.

## ⏱️ Complexity Analysis

Let `n` be the number of temperatures.

### Time Complexity — O(n)

Although the solution contains a `while` loop inside a `for` loop, the overall time complexity is **O(n)**.

Why?

Because each index is:

- Pushed onto the stack exactly once.
- Popped from the stack at most once.

Therefore, the total number of stack operations is linear.

**Time Complexity: O(n)**

### Space Complexity — O(n)

We use:

- An answer array containing `n` elements.
- A stack that may contain up to `n` indices.

In the worst case, temperatures are decreasing:

```text
[80, 75, 70, 65, 60]
```

No index gets popped, so the stack contains every index.

**Space Complexity: O(n)**

## 🧠 Key Takeaways

- A monotonic stack helps solve problems involving the next greater or smaller element.
- We store indices instead of values when we need to calculate distances between elements.
- The stack keeps track of days whose next warmer temperature has not been found.
- A warmer temperature can resolve multiple previous days.
- The `while` loop allows us to pop all previous colder temperatures.
- Each index is pushed once and popped at most once.
- A nested `while` loop does not necessarily mean O(n²) time complexity.
- The answer array starts with zeros, so unresolved days require no extra processing.
- Monotonic stacks reduce repeated comparisons and make the solution efficient.

### ⭐ Pattern to Remember

```java
for (int i = 0; i < temperatures.length; i++) {

    while (!stack.empty() &&
           temperatures[i] > temperatures[stack.peek()]) {

        int prevIndex = stack.pop();
        answer[prevIndex] = i - prevIndex;
    }

    stack.push(i);
}
```

This pattern is useful for problems involving **Next Greater Element**, **Next Smaller Element**, and similar array problems.

**Pattern Learned:** Array + Monotonic Stack + Next Greater Element

## 🔗 LeetCode Problem

[739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)
