
# 703. Kth Largest Element in a Stream

**Difficulty:** Easy  
**Topic:** Heap, Priority Queue  
**Language:** Java

**LeetCode:** https://leetcode.com/problems/kth-largest-element-in-a-stream/

## Problem Description

Design a class that finds the Kth largest element in a stream of integers.

The class should support two operations:

- `KthLargest(int k, int[] nums)` — Initializes the object with `k` and an initial array of numbers.
- `add(int val)` — Adds a new number to the stream and returns the Kth largest element among all numbers received so far.

## Example

**Input:**
```text
k = 3
nums = [4, 5, 8, 2]
```

**Operations:**
```text
add(3)  → 4
add(5)  → 5
add(10) → 5
add(9)  → 8
add(4)  → 8
```

**Output:**
```text
[4, 5, 5, 8, 8]
```

## Approach: Min Heap

We use a Min Heap implemented with Java's `PriorityQueue`.

The main idea is to keep only the `k` largest elements in the heap.

Since the smallest element is always at the root of a Min Heap, the root represents the Kth largest element when the heap contains `k` elements.

### Algorithm

1. Create a Min Heap using `PriorityQueue<Integer>`.
2. Insert the initial numbers into the heap.
3. Whenever the heap size exceeds `k`, remove the smallest element using `poll()`.
4. For each call to `add(val)`:
   - Insert `val` into the heap.
   - Remove the smallest element if the heap size exceeds `k`.
   - Return the root using `peek()`.

## Java Solution

```java
import java.util.PriorityQueue;

class KthLargest {
    private int k;
    private PriorityQueue<Integer> minHeap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);

            if (minHeap.size() > k)
                minHeap.poll();
        }
    }

    public int add(int val) {
        minHeap.add(val);

        if (minHeap.size() > k)
            minHeap.poll();

        return minHeap.peek();
    }
}
```

## Complexity Analysis

Let `n` be the length of the initial array.

**Time Complexity:**

- Constructor: `O(n log k)`
- `add()`: `O(log k)`
- `peek()`: `O(1)`

**Space Complexity:**

- `O(k)` — The heap stores at most `k` elements.

## Why Min Heap Instead of Max Heap?

A Min Heap allows us to efficiently maintain the `k` largest elements.

When the heap size exceeds `k`, we remove the smallest element.

As a result, the smallest element remaining in the heap is the Kth largest element overall.

Using a Max Heap would make it easy to access the largest element, but not the Kth largest element while maintaining only `k` elements.

## Key Takeaways

- Java's `PriorityQueue` implements a Min Heap by default.
- `add()` inserts a new element.
- `poll()` removes the smallest element.
- `peek()` returns the smallest element without removing it.
- A Min Heap of size `k` efficiently tracks the Kth largest element in a stream.
- Choosing the appropriate data structure can significantly improve algorithm efficiency.
