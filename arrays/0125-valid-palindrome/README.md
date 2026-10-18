# 🔤 125. Valid Palindrome

**Difficulty:** Easy 🟢  
**Topic:** String / Two Pointers  
**Language:** Java

## 📌 Problem Description

Given a string `s`, determine whether it is a **valid palindrome**.

A string is considered a palindrome after:

- Converting uppercase letters to lowercase.
- Ignoring all non-alphanumeric characters.
- Keeping both letters and numbers.

A palindrome reads the same forward and backward.

### Example

**Input:**

```text
s = "A man, a plan, a canal: Panama"
```

After ignoring spaces and symbols and converting to lowercase:

```text
amanaplanacanalpanama
```

**Output:**

```text
true
```

The cleaned string reads the same in both directions.

## 💡 Approach — Two Pointers

I solved this problem using the **Two Pointers** pattern.

One pointer starts at the beginning of the string, and the other starts at the end.

```text
left → A man, a plan, a canal: Panama ← right
```

We move both pointers toward the center while comparing valid characters.

The algorithm works as follows:

1. Convert the string to lowercase and then to a character array.
2. Initialize `left` at the beginning and `right` at the end.
3. Skip characters that are not letters or digits.
4. Compare the valid characters at both pointers.
5. If they differ, return `false`. Otherwise, move both pointers inward.

If the loop finishes without finding a mismatch, return `true`.

## 💻 Java Solution

```java
class Solution {
    public boolean isPalindrome(String s) {

        char[] sCharArray = s.toLowerCase().toCharArray();

        int left = 0;
        int right = sCharArray.length - 1;

        while (left < right) {

            if (!Character.isLetterOrDigit(sCharArray[left])) {
                left++;
                continue;
            }

            if (!Character.isLetterOrDigit(sCharArray[right])) {
                right--;
                continue;
            }

            if (sCharArray[left] != sCharArray[right])
                return false;

            left++;
            right--;
        }

        return true;
    }
}
```

## 🔍 Step-by-Step Explanation

### Step 1: Handle Uppercase and Lowercase

Uppercase and lowercase letters should be treated equally.

For example, `'A'` and `'a'` should be considered the same character.

```java
char[] sCharArray = s.toLowerCase().toCharArray();
```

This converts the string to lowercase and creates a character array, making comparisons easier.

### Step 2: Initialize Two Pointers

```java
int left = 0;
int right = sCharArray.length - 1;
```

The pointers start at opposite ends and move toward the center.

We continue while:

```java
while (left < right)
```

There is no need to compare characters after the pointers meet or cross.

### Step 3: Skip Invalid Characters

The problem only considers letters and digits.

Java provides:

```java
Character.isLetterOrDigit(character)
```

For example:

| Character | Result |
|---|---|
| `'A'` | `true` |
| `'7'` | `true` |
| `'!'` | `false` |
| `' '` | `false` |
| `','` | `false` |

If the left character is invalid:

```java
if (!Character.isLetterOrDigit(sCharArray[left])) {
    left++;
    continue;
}
```

If the right character is invalid:

```java
if (!Character.isLetterOrDigit(sCharArray[right])) {
    right--;
    continue;
}
```

This allows us to ignore spaces and special characters without checking each symbol manually.

### Step 4: Compare Valid Characters

Once both pointers point to valid characters, we compare them:

```java
if (sCharArray[left] != sCharArray[right])
    return false;
```

If the characters differ, the string is not a palindrome.

Otherwise, move both pointers:

```java
left++;
right--;
```

If all comparisons succeed, return `true`.

## 🧩 Why Do We Use `continue`?

The `continue` statement skips the remaining code in the current loop iteration and starts the next iteration.

For example:

```java
if (!Character.isLetterOrDigit(sCharArray[left])) {
    left++;
    continue;
}
```

After moving `left`, we need to check whether the new character is valid before comparing it.

The process is:

```text
Invalid Character
       ↓
Move Pointer
       ↓
    continue
       ↓
Next Loop Iteration
       ↓
Check Character Again
```

Without `continue`, the program could immediately compare characters before validating the new pointer position.

## 🔄 Example Walkthrough

**Input:**

```text
s = "A man, a plan, a canal: Panama"
```

First, convert the string to lowercase:

```text
"a man, a plan, a canal: panama"
```

Then start comparing from both ends.

- Compare `'a'` and `'a'` → Match.
- Skip spaces and punctuation when encountered.
- Compare `'m'` and `'m'` → Match.
- Continue moving toward the center.
- All valid character pairs match.

The effective string is:

```text
amanaplanacanalpanama
```

**Final Output:**

```text
true
```

## ⏱️ Complexity Analysis

### Time Complexity — O(n)

Each pointer moves through the string in only one direction.

Converting the string to lowercase and creating the character array also takes O(n) time.

**Time Complexity: O(n)**

### Space Complexity — O(n)

This implementation creates additional data using:

```java
s.toLowerCase().toCharArray()
```

Therefore:

**Auxiliary Space Complexity: O(n)**

We could reduce the extra space to **O(1)** by using `s.charAt()` and converting individual characters with `Character.toLowerCase()` instead of creating a new character array.

## 🧠 Key Takeaways

- **Two Pointers** is useful for comparing characters from opposite ends.
- `Character.isLetterOrDigit()` checks whether a character is a letter or number.
- Numbers are valid characters in this problem.
- `continue` skips the rest of the current iteration.
- `toLowerCase()` simplifies case-insensitive comparisons.
- We can ignore special characters without manually listing them.
- Two Pointers avoids creating a separate cleaned and reversed string.
- This approach takes O(n) time.

### ⭐ Pattern to Remember

```text
       Input String
            ↓
    Convert to Lowercase
            ↓
left →             ← right
            ↓
  Skip Invalid Characters
            ↓
  Compare Valid Characters
            ↓
     Move Toward Center
            ↓
     true / false
```

**Pattern Learned:** Two Pointers + String Traversal + Character Validation

## 🔗 LeetCode Problem

[125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
