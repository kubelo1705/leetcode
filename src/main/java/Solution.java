import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
            while(number!=0) {
                usedNumbers[number--]++;
            }
        } else {
            while (number!=0) {
                int unitColumn = number % 10;
                if (unitColumn == 0) {
                    usedNumbers[tenColumn]++;
                    usedNumbers[unitColumn]++;
                    tenColumn--;
                } else {
                    if(tenColumn!=0) {
                        usedNumbers[tenColumn]++;
                    }
                    usedNumbers[unitColumn]++;
                }
                number--;
            }
        }
        return usedNumbers;
    }

    public static boolean isMatch(String s, String p) {
        for (int i = 0; i < p.length(); i++) {
            if(p.charAt(i)=='.'){
                continue;
            }else if(p.charAt(i)=='*') {
                if(i-1>=0){

                }else {
                    continue;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        scanner.nextLine();
        int[] numbers=new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i]=scanner.nextInt();
            scanner.nextLine();
        }
        for (int i = 0; i < numbers.length; i++) {
            int[] result=countUsedNumber(numbers[i]);
            for (int j : result) {
                System.out.print(j+"\t");
            }
            System.out.println();
        }

    }
}