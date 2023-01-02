import java.util.*;

class Solution {
    /* https://leetcode.com/problems/two-sum/ */
    public int[] twoSum(int[] nums, int target) {
        Map vistedNums = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int neededNum = target - nums[i];
            if (vistedNums.containsKey(neededNum)) {
                return new int[]{(int) vistedNums.get(neededNum), i};
            } else {
                vistedNums.put(nums[i], i);
            }
        }
        return new int[]{};
    }

    /* https://leetcode.com/problems/add-two-numbers/ */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode currentNode = result;
        ListNode longerList = null;
        int remember = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + remember;
            if (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            if (sum >= 10) {
                currentNode.val = sum - 10;
                remember = 1;
            } else {
                currentNode.val = sum;
                remember = 0;
            }
            ListNode newNode = new ListNode();
            currentNode.next = newNode;

            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null) {
            longerList = l1;
        }
        if (l2 != null) {
            longerList = l2;
        }
        while (remember == 1) {
            currentNode = currentNode.next;
            if (longerList != null) {
                int sum = remember + longerList.val;
                if (sum >= 10) {
                    currentNode.val = sum - 10;
                    remember = 1;
                } else {
                    currentNode.val = sum;
                    remember = 0;
                }
                longerList = longerList.next;
            } else {
                currentNode.val = remember;
                remember = 0;
            }
            ListNode newNode = new ListNode();
            currentNode.next = newNode;
        }
        currentNode.next = longerList;
        return result;
    }

    /* https://leetcode.com/problems/longest-substring-without-repeating-characters/ */
    public static int lengthOfLongestSubstring(String s) {
        int maxLengthOfSubstring = 0;
        int lengthOfSubstring = 0;
        String visitedSubstring = "";
        for (int i = 0; i < s.length(); i++) {
            String character = s.substring(i, i + 1);
            if (visitedSubstring.contains(character)) {
                int position = visitedSubstring.indexOf(character);
                visitedSubstring = visitedSubstring.substring(position + 1) + character;
                if (lengthOfSubstring > maxLengthOfSubstring) {
                    maxLengthOfSubstring = lengthOfSubstring;
                }
                lengthOfSubstring -= position;
            } else {
                lengthOfSubstring++;
                visitedSubstring += character;
            }
        }
        if (lengthOfSubstring > maxLengthOfSubstring) {
            maxLengthOfSubstring = lengthOfSubstring;
        }
        return maxLengthOfSubstring;
    }

    /* https://leetcode.com/problems/median-of-two-sorted-arrays/ */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int shorter = Math.min(nums1.length, nums2.length);
        int longer = Math.max(nums1.length, nums2.length);

        int[] mergedArray = new int[longer + shorter];
        double neededIndex = ((double) longer + shorter) / 2;

        int indexMerged = 0;
        int index1 = 0;
        int index2 = 0;

        while (index1 < nums1.length && index2 < nums2.length && indexMerged <= neededIndex + 1) {
            if (nums1[index1] > nums2[index2]) {
                mergedArray[indexMerged] = nums2[index2];
                index2++;
            } else {
                mergedArray[indexMerged] = nums1[index1];
                index1++;
            }
            indexMerged++;
        }

        while (index1 < nums1.length && indexMerged <= neededIndex + 1) {
            mergedArray[indexMerged] = nums1[index1];

            index1++;
            indexMerged++;
        }

        while (index2 < nums2.length && indexMerged <= neededIndex + 1) {
            mergedArray[indexMerged] = nums2[index2];
            index2++;
            indexMerged++;
        }

        if (neededIndex == (int) neededIndex) {
            return (double) (mergedArray[(int) neededIndex] + mergedArray[(int) neededIndex - 1]) / 2;
        } else {
            return mergedArray[(int) neededIndex];
        }
    }

    /* https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/ */
    public char findKthBit(int loopNum, int position) {
        String result = "0";
        while (--loopNum != 0) {
            String reverse = new StringBuilder(result.replace('1', 'x')
                    .replace('0', '1')
                    .replace('x', '0'))
                    .reverse()
                    .toString();
            result = result + "1" + reverse;
        }
        return result.charAt(position - 1);
    }

    /* https://leetcode.com/problems/longest-palindromic-substring/ */
    public static String longestPalindrome(String s) {
        String longestPalindrome = "";
        int left;
        int right;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            left = i;
            right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            int lengthOdd = right - left - 1;

            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            int lengthEven = right - left - 1;

            int length = Math.max(lengthEven, lengthOdd);
            if (length > max) {
                max = length;
                longestPalindrome = s.substring(i - (length - 1) / 2, i + length / 2 + 1);
            }
        }
        return longestPalindrome;
    }

    /* https://leetcode.com/problems/zigzag-conversion/ */
    public static String convert(String s, int numRows) {
        String[] patterns = new String[numRows];
        for (int i = 0; i < patterns.length; i++) {
            patterns[i] = "";
        }
        String result = "";
        int step = 0;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            patterns[index + step] += s.substring(i, i + 1);
            if (index + step == numRows - 1) {
                if (step == 0) {
                    step = Math.max(numRows - 2, 0);
                }
                index = 0;
                continue;
            }
            if (step > 0) {
                step--;
                index = 0;
            } else {
                index++;
            }
        }
        for (String s1 : patterns) {
            result += s1;
        }
        return result;
    }

    /* https://leetcode.com/problems/reverse-integer/ */
    public static int reverse(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return result == (int) result ? (int) result : 0;
    }

    /*
    Count used numbers when mark page number in paper
    Example:
    -input: 5 => mark 1 2 3 4 5
    -output: 0 1 1 1 1 1 0 0 0 0
             0 1 2 3 4 5 6 7 8 9
     */
    public static int[] countUsedNumber(int number) {
        int[] usedNumbers = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int tenColumn = number / 10;
        if (tenColumn == 0) {
            while (number != 0) {
                usedNumbers[number--]++;
            }
        } else {
            while (number != 0) {
                int unitColumn = number % 10;
                if (unitColumn == 0) {
                    usedNumbers[tenColumn]++;
                    usedNumbers[unitColumn]++;
                    tenColumn--;
                } else {
                    if (tenColumn != 0) {
                        usedNumbers[tenColumn]++;
                    }
                    usedNumbers[unitColumn]++;
                }
                number--;
            }
        }
        return usedNumbers;
    }

    /* https://leetcode.com/problems/regular-expression-matching/ */
    public static boolean isMatch(String s, String p) {
        return s.matches(p);
    }

    /* https://leetcode.com/problems/container-with-most-water/ */
    public static int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        for (int i = height.length - 1; i > 0; i--) {
            max = Math.max(max, Math.min(height[left], height[right]) * i);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    /* https://leetcode.com/problems/integer-to-roman/ */
    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int[] values = new int[]{1000, 500, 100, 50, 10, 5, 1};
        char[] chars = new char[]{'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int[] specialValues = new int[]{900, 400, 90, 40, 9, 4, 1000};//1000 is added for index in this array equal with other array
        String[] specialChars = new String[]{"CM", "CD", "XC", "XL", "IX", "IV", ""};
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                result.append(chars[i]);
                num -= values[i];
            }
            if (num >= specialValues[i]) {
                result.append(specialChars[i]);
                num -= specialValues[i];
            }
            if (num == 0) {
                break;
            }
        }
        return result.toString();
    }

    /* https://leetcode.com/problems/substring-with-concatenation-of-all-words/ */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int lengthOfSubStrings = words[0].length() * words.length;

        for (int i = 0; i <= s.length() - lengthOfSubStrings; i++) {
            StringBuilder checkString = new StringBuilder(s.substring(i, i + lengthOfSubStrings));
            for (int j = 0; j < words.length; j++) {
                int indexOfSubString = findIndexOfSubString(checkString, words[j]);
                if (indexOfSubString != -1) {
                    checkString = checkString.delete(indexOfSubString, indexOfSubString + words[j].length());
                } else {
                    break;
                }
            }
            if (checkString.length() == 0) {
                result.add(i);
            }
        }

        return result;
    }

    public static int findIndexOfSubString(StringBuilder s, String subString) {
        for (int i = 0; i < s.length(); i += subString.length()) {
            if (s.substring(i, i + subString.length()).equals(subString)) {
                return i;
            }
        }
        return -1;
    }

    /* https://leetcode.com/problems/roman-to-integer/ */
    public static int romanToInt(String s) {
        int sum = 0;
        while (s.length() > 0) {
            int first = findValueOfRomanInInt(s.charAt(0));
            int second = 0;

            if (s.length() > 1) {
                second = findValueOfRomanInInt(s.charAt(1));
            }

            if (second > first) {
                sum += (second - first);
                s = s.substring(2);
            } else {
                sum += first;
                s = s.substring(1);
            }
        }
        return sum;
    }

    public static int findValueOfRomanInInt(char roman) {
        char[] romans = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] values = new int[]{1, 5, 10, 50, 100, 500, 1000};
        for (int i = 0; i < romans.length; i++) {
            if (romans[i] == roman) {
                return values[i];
            }
        }
        return 0;
    }

    /* https://leetcode.com/problems/longest-common-prefix/ */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }

        }
        return prefix;
    }

    /* https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ */
    public static int maxProfit(int[] prices) {
        int min = prices[0], maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - min);
        }

        return Math.max(0, maxProfit);
    }

    /* https://leetcode.com/problems/contains-duplicate/ */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}