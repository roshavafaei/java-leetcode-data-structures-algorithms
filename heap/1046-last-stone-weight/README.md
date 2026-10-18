# 🪨 1046. Last Stone Weight

**Difficulty:** Easy 🟢  
**Topic:** Heap / Priority Queue  
**Language:** Java

## 📌 Problem Description

You are given an array of integers `stones`, where each element represents the weight of a stone.

In each round, we select the **two heaviest stones** and smash them together.

- If both stones have the same weight, both are destroyed.
- If their weights are different, the lighter stone is destroyed, and the heavier stone gets a new weight equal to the difference.

We repeat this process until at most one stone remains.

**Goal:** Return the weight of the last remaining stone, or `0` if no stones remain.

## 💡 Approach — Max Heap

We use a **Max Heap** implemented with Java's `PriorityQueue` and `Collections.reverseOrder()`.

A Max Heap allows us to efficiently retrieve and remove the largest element.

### Algorithm

1. Create a Max Heap.
2. Insert all stone weights into the heap.
3. While the heap contains at least two stones:
   - Remove the two heaviest stones using `poll()`.
   - If their weights are equal, both are destroyed.
   - Otherwise, insert the difference back into the heap.
4. Return the remaining stone's weight, or `0` if the heap is empty.

## 💻 Java Solution

```java
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < stones.length; i++) {
            maxHeap.add(stones[i]);
        }

        while (maxHeap.size() > 1) {
            int firstLargestStone = maxHeap.poll();
            int secondLargestStone = maxHeap.poll();

            if (firstLargestStone == secondLargestStone)
                continue;

            maxHeap.add(firstLargestStone - secondLargestStone);
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}
```

## 🔍 Example

**Input:**

```text
stones = [2, 7, 4, 1, 8, 1]
```

**Step-by-step execution:**

| Step | Stones | Smash | Result |
|------|--------|-------|--------|
| 1 | [2, 7, 4, 1, 8, 1] | 8 and 7 | 1 |
| 2 | [2, 4, 1, 1, 1] | 4 and 2 | 2 |
| 3 | [2, 1, 1, 1] | 2 and 1 | 1 |
| 4 | [1, 1, 1] | 1 and 1 | Both destroyed |
| 5 | [1] | Only one stone remains | 1 |

**Output:**

```text
1
```

## ⏱️ Complexity Analysis

**Time Complexity: O(n log n)**

- Inserting all `n` stones into the heap takes `O(n log n)`.
- Each `poll()` or `add()` operation takes `O(log n)`.
- There are at most `n - 1` smashing rounds.

Therefore, the overall time complexity is **O(n log n)**.

**Space Complexity: O(n)**

The Max Heap stores up to `n` stone weights.

## 🧠 Key Takeaways

- Java's `PriorityQueue` is a Min Heap by default.
- `Collections.reverseOrder()` turns it into a Max Heap.
- `poll()` removes and returns the highest-priority element.
- `peek()` returns the highest-priority element without removing it.
- `while (maxHeap.size() > 1)` ensures that two stones are available for each round.
- A Max Heap is useful when we repeatedly need to access the largest elements.

## 🔗 LeetCode Problem

[1046. Last Stone Weight](https://leetcode.com/problems/last-stone-weight/)
