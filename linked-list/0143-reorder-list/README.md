# 🔗 143. Reorder List

**Difficulty:** Medium 🟡  
**Topic:** Linked List / Two Pointers / Fast & Slow Pointers / Reversal  
**Language:** Java

## 📌 Problem Description

Given the head of a singly linked list, reorder its nodes into a specific pattern.

The original list is:

```text
L0 → L1 → L2 → ... → Ln
```

We need to reorder it into:

```text
L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
```

**Important:** We must modify the connections between existing nodes without changing their values.

### Example

**Input:**

```text
1 → 2 → 3 → 4 → 5
```

**Output:**

```text
1 → 5 → 2 → 4 → 3
```

The first node is followed by the last node, then the second node, then the second-to-last node, and so on.

## 💡 Approach — Two Pointers + Reverse + Merge

We solve this problem in four main steps:

1. Find the middle of the linked list.
2. Split the list into two halves.
3. Reverse the second half.
4. Merge both halves by alternating nodes.

This approach allows us to reorder the list **in place**, without creating another linked list.

### Step 1: Find the Middle

We use the **Fast and Slow Pointer** technique.

- `slow` moves one node at a time.
- `fast` moves two nodes at a time.

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

When `fast` reaches the end, `slow` points to the middle node (or the second middle node for an even-length list).

For example:

```text
1 → 2 → 3 → 4 → 5
        ↑
       slow
```

### Step 2: Split the Linked List

Once we find the middle, we separate the list into two halves.

```java
ListNode secondHalfHead = slow.next;
slow.next = null;
```

The first statement saves the beginning of the second half.

The second statement disconnects the two halves.

**Before:**

```text
1 → 2 → 3 → 4 → 5
```

**After:**

```text
First half:   1 → 2 → 3
Second half:  4 → 5
```

This separation is important because we need to reverse the second half independently.

### Step 3: Reverse the Second Half

We reverse the second half using three pointers:

- `previous` — Points to the previous node.
- `current` — Points to the node currently being processed.
- `nextNode` — Temporarily stores the next node.

```java
ListNode previous = null;
ListNode current = secondHalfHead;

while (current != null) {
    ListNode nextNode = current.next;

    current.next = previous;

    previous = current;
    current = nextNode;
}
```

**Before reversing:**

```text
4 → 5 → null
```

**After reversing:**

```text
5 → 4 → null
```

At the end of the loop, `previous` points to the new head of the reversed second half.

### Step 4: Merge Both Halves

Now we merge the two lists by alternating nodes.

```text
First half:   1 → 2 → 3
Second half:  5 → 4
```

We take one node from the first half, then one from the second half, and repeat.

```java
ListNode firstHalfCurrent = head;
ListNode secondHalfCurrent = previous;

while (secondHalfCurrent != null) {
    ListNode firstHalfNext = firstHalfCurrent.next;
    ListNode secondHalfNext = secondHalfCurrent.next;

    firstHalfCurrent.next = secondHalfCurrent;
    secondHalfCurrent.next = firstHalfNext;

    firstHalfCurrent = firstHalfNext;
    secondHalfCurrent = secondHalfNext;
}
```

**Final result:**

```text
1 → 5 → 2 → 4 → 3
```

We save the next nodes before changing any connections so we do not lose access to the remaining parts of either list.

## 💻 Java Solution

```java
class Solution {
    public void reorderList(ListNode head) {

        if (head == null || head.next == null)
            return;

        // Step 1: Find the middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Split the list
        ListNode secondHalfHead = slow.next;
        slow.next = null;

        // Step 3: Reverse the second half
        ListNode previous = null;
        ListNode current = secondHalfHead;

        while (current != null) {
            ListNode nextNode = current.next;

            current.next = previous;
            previous = current;
            current = nextNode;
        }

        // Step 4: Merge both halves
        ListNode firstHalfCurrent = head;
        ListNode secondHalfCurrent = previous;

        while (secondHalfCurrent != null) {
            ListNode firstHalfNext = firstHalfCurrent.next;
            ListNode secondHalfNext = secondHalfCurrent.next;

            firstHalfCurrent.next = secondHalfCurrent;
            secondHalfCurrent.next = firstHalfNext;

            firstHalfCurrent = firstHalfNext;
            secondHalfCurrent = secondHalfNext;
        }
    }
}
```

*Note: LeetCode provides the `ListNode` class.*

## 🔍 Example — Step-by-Step Walkthrough

Consider the following linked list:

```text
1 → 2 → 3 → 4 → 5
```

### Phase 1: Find the Middle

We initialize both pointers at the head.

```text
slow = 1
fast = 1
```

They move as follows:

| Iteration | slow | fast |
|-----------|------|------|
| Start | 1 | 1 |
| 1 | 2 | 3 |
| 2 | 3 | 5 |

Since `fast.next` is now `null`, the loop stops.

The middle node is `3`.

### Phase 2: Split the List

We save node `4` as the beginning of the second half and disconnect node `3` from it.

```text
First half:   1 → 2 → 3 → null

Second half:  4 → 5 → null
```

### Phase 3: Reverse the Second Half

Initially:

```text
previous = null
current  = 4
```

**First iteration:**

Save the next node:

```text
nextNode = 5
```

Reverse the connection:

```text
4 → null
```

Move the pointers:

```text
previous = 4
current  = 5
```

**Second iteration:**

Save the next node:

```text
nextNode = null
```

Reverse the connection:

```text
5 → 4 → null
```

Move the pointers:

```text
previous = 5
current  = null
```

The loop ends.

Our two halves are now:

```text
First half:   1 → 2 → 3
Second half:  5 → 4
```

### Phase 4: Merge the Lists

We alternate nodes from both halves.

**First merge iteration:**

Connect node `1` to node `5`, then node `5` to node `2`.

```text
1 → 5 → 2 → 3
```

The remaining second-half node is `4`.

**Second merge iteration:**

Connect node `2` to node `4`, then node `4` to node `3`.

```text
1 → 5 → 2 → 4 → 3
```

The second half is now exhausted.

**Final Output:**

```text
1 → 5 → 2 → 4 → 3
```

## 🧩 Why Do We Save the Next Nodes?

One of the most important parts of this problem is managing node references correctly.

Consider:

```java
ListNode firstHalfNext = firstHalfCurrent.next;
ListNode secondHalfNext = secondHalfCurrent.next;
```

We save these references **before modifying the links**.

Why?

Because changing a node's `next` reference can disconnect us from the remaining nodes.

For example:

```java
firstHalfCurrent.next = secondHalfCurrent;
```

After this assignment, the original connection from `firstHalfCurrent` to its next node is replaced.

If we had not saved `firstHalfNext`, we could lose access to the rest of the first half.

The same idea applies when reversing a linked list:

```java
ListNode nextNode = current.next;
current.next = previous;
```

We save `nextNode` before changing `current.next`.

**Important:** Whenever modifying linked list connections, save any references you will still need before overwriting them.

## 🔄 Why Reverse the Second Half?

The required order is:

```text
L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
```

However, a singly linked list only allows us to move forward using `next`.

We cannot directly move backward from the last node.

Reversing the second half solves this problem.

**Original list:**

```text
1 → 2 → 3 → 4 → 5
```

**After splitting:**

```text
1 → 2 → 3
4 → 5
```

**After reversing:**

```text
1 → 2 → 3
5 → 4
```

Now both halves can be traversed forward while producing the required order.

This makes the final merge straightforward.

## 🌱 Why Is the Solution In-Place?

The problem requires us to reorder the existing nodes rather than create new ones.

Our solution only modifies `next` references.

We do not:

- Create new `ListNode` objects.
- Copy values into another list.
- Use an additional array or stack.

Instead, we reuse the original nodes and rearrange their connections.

This allows the algorithm to use constant auxiliary space.

## ⏱️ Complexity Analysis

Let `n` be the number of nodes in the linked list.

### Time Complexity — O(n)

The algorithm consists of three main traversals:

| Operation | Time Complexity |
|-----------|-----------------|
| Find the middle | O(n) |
| Reverse the second half | O(n) |
| Merge both halves | O(n) |

Each step processes at most `n` nodes.

Therefore:

**Time Complexity: O(n)**

### Space Complexity — O(1)

We only use a constant number of pointers:

- `slow` and `fast`
- `secondHalfHead`
- `previous`, `current`, and `nextNode`
- Pointers used during merging

No additional data structures are created.

Therefore:

**Space Complexity: O(1)**

## 🧠 Key Takeaways

- The Fast and Slow Pointer technique helps find the middle of a linked list.
- Moving `slow` by one step and `fast` by two steps allows us to find the middle in one traversal.
- Splitting a linked list requires saving the next node before disconnecting it.
- Reversing a singly linked list requires carefully updating `next` references.
- Always save the next node before modifying a connection.
- After reversing, `previous` points to the new head of the reversed list.
- Reversing the second half allows us to access nodes in the required order.
- Two linked lists can be merged by alternating their nodes.
- The original list can be reordered without changing node values.
- Combining multiple linked list techniques can solve more complex problems efficiently.

### ⭐ Pattern to Remember

```text
Find the Middle
       ↓
Split the List
       ↓
Reverse the Second Half
       ↓
Merge Alternately
       ↓
Reordered Linked List
```

**Pattern Learned:** Linked List + Fast & Slow Pointers + Reversal + In-Place Merge

## 🔗 LeetCode Problem

[143. Reorder List](https://leetcode.com/problems/reorder-list/)
