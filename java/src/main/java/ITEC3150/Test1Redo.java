package ITEC3150;

import java.util.Arrays;

/**
 * @author Ajitesh Sandhu
 * 
 *         Write a program that prompts the user to enter a string and displays
 *         the maximum increasingly ordered subsequence of characters. Analyze
 *         the time complexity of your program. Here is a sample run. Program it
 *         submit .java, .class, execution result. test with Engish word longer
 *         than 12 character
 * 
 *         This program using the longest common substring algorithm but is
 *         passed using a sorted and de-duped string resulting in LIS
 *         Time complexity O(n^2)
 */
public class Test1Redo {

    static String lis(String input) {
        char[] inputArr = input.toCharArray();
        char[] helper = prepare(input); // Sorted and de-duped copy of inputArr

        int m = input.length(), n = helper.length;

        int[][] lengths = new int[m + 1][n + 1];

        // Fill lengths array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //
                if (inputArr[i - 1] == helper[j - 1])
                    lengths[i][j] = lengths[i - 1][j - 1] + 1;
                else
                    lengths[i][j] = Math.max(lengths[i - 1][j], lengths[i][j - 1]);
            }
        }

        // Backtrack to get ans and print
        char[] ans = new char[lengths[m][n]];
        int i = m, j = n, k = ans.length - 1;
        while (i > 0 && j > 0) {
            if (inputArr[i - 1] == helper[j - 1]) {
                ans[k--] = inputArr[i - 1];
                i--;
                j--;
            } else if (lengths[i - 1][j] >= lengths[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return new String(ans);
    }

    // Returned sorted and de-duped array
    static char[] prepare(String in) {
        char[] temp = in.toCharArray();
        Arrays.sort(temp);
        return removeDupes(temp);
    }

    static char[] removeDupes(char[] a) {
        if (a.length == 0)
            return a;
        int w = 1; // write index
        for (int r = 1; r < a.length; r++) {
            if (a[r] != a[w - 1])
                a[w++] = a[r];
        }
        return Arrays.copyOf(a, w);
    }

    public static void main(String[] args) {
        String input = "sesquipedalophobia";
        // String input = "welcome";

        System.out.println("Input string: " + input);
        System.out.println("One of the maximum LIS is: " + lis(input));

    }

}
