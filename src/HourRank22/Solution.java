package HourRank22;

/**
 * Created by Rafal on 02017-07-02.
 */

import java.util.Scanner;

public class Solution {

    static int smallestSizeSubsequence(int n, int[] A) {
        // Return the size of the smallest subsequence to remove
        int numberOfOdds = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] % 2 != 0) numberOfOdds++;
        }
        if (numberOfOdds % 2 != 0 && n < 2) return -1;
        return (numberOfOdds % 2 == 0) ? 0 : 1;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for (int A_i = 0; A_i < n; A_i++) {
            A[A_i] = in.nextInt();
        }
        int result = smallestSizeSubsequence(n, A);
        System.out.println(result);
    }
}
