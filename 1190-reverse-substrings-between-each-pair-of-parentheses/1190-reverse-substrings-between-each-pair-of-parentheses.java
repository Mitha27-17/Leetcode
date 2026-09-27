import java.util.*;

class Solution {
    public String reverseParentheses(String s) {

        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == '(') {
                stack.push(i);
            }

            else if (arr[i] == ')') {

                int start = stack.pop();
                reverse(arr, start + 1, i - 1);
            }
        }

        StringBuilder result = new StringBuilder();

        for (char c : arr) {
            if (c != '(' && c != ')') {
                result.append(c);
            }
        }

        return result.toString();
    }

    private void reverse(char[] arr, int left, int right) {

        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}