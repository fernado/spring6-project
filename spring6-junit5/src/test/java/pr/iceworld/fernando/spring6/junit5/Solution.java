package pr.iceworld.fernando.spring6.junit5;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        //int[] nums = {1,2,3,4,5,6,7};

        //rotate(nums, 3);
        //int[] nums = {3, 2, 4};
        //System.out.println(twoSum(nums, 6));
        System.out.println(isValid("()"));
    }
    public static int maxProfit(int[] prices) {
        int count = 0;
        if (prices.length >= 3 * 1000 || prices.length < 1) {
            return 0;
        }        

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < 0 || prices[i] > 10000) {
                return 0;
            }
            if (prices[i] < prices[i+1]) {
                count = count + (prices[i+1] - prices[i]);
            }
 
        }
        return count;
    }

    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int i = 0;
        int j = 0;
        boolean flag = false;
        for (int k = 0; k < length - 1; k++) {
            for (int l = 1; l < length; l++) {
                if (nums[k] + nums[l] == target) {
                    i = k;
                    j = l;
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        return new int[] {i, j};
    }


    //{1,2,3,4,5,6,7}
    //[5,6,7,1,2,3,4]
    public static void rotate(int[] nums, int k) {
        int length = nums.length;
        int temp = 0;
        int newK = k % length;
        int movedIndex = 0;
        //temp = nums[length - 1];
        //for (int i = 0; i < length - 1; i++) {
        //    nums[length - 1 - i] = nums[length - 2 - i];
        //}
        //nums[0] = temp;

        int j = 0;
        while (j < length - 1) {
            temp = nums[movedIndex++];
            nums[newK++] = temp;
        }

        for (int i = 0; i < length - 1; i++) {
            System.out.print(nums[i] + " ");
        }
    }


    public static boolean isValid(String s) {
        if (s.length() % 2 > 0) return false;
        char[] aa = {'(', ')'};
        char[] bb = {'[', ']'};
        char[] cc = {'{', '}'};
        LinkObj link = new LinkObj(s.length() / 2);
        if (isRight(s.charAt(0), aa, bb, cc)) return false;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (isLeft(s.charAt(i), aa, bb, cc)) {
                link.push(s.charAt(i));
            } else {
                if (s.charAt(i) == aa[1] && link.current() == aa[0]) {
                    link.pop();
                } else if (s.charAt(i) == bb[1] && link.current() == bb[0]) {
                    link.pop();
                } else if (s.charAt(i) == cc[1] && link.current() == cc[0]) {
                    link.pop();
                }
            }
        }
        if (link.first() == '0') return true;
        return false;

    }

    static class LinkObj {
        char[] cs;
        int i = 0;
        private LinkObj(int size) {
            cs = new char[size];
            for (int i = 0; i < cs.length - 1; i++) {
                cs[i] = '0';
            }
        }
        public void push(char c) {
            cs[i] = c;
            i++;
        }

        public char pop() {
            char result = i > 0 ? cs[i-1] : cs[0];
            if (i > 0) {
                cs[i-1] = '0';
            }
            cs[0] = '0';
            return result;
        }

        public char current() {
            return i > 0 ? cs[i-1] : cs[0];
        }

        public char first() {
            return cs[0];
        }

    }

    private static boolean isLeft(char c, char[] aa, char[] bb, char[] cc) {
        return c == aa[0] || c == bb[0] || c == cc[0];
    }

    private static boolean isRight(char c, char[] aa, char[] bb, char[] cc) {
        return c == aa[1] || c == bb[1] || c == cc[1];
    }
}



