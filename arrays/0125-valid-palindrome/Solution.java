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
