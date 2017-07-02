/**
 * Created by Rafal on 02017-06-20.
 */

import java.util.Scanner;

public class Solution2 {
    static int minimumChocolateMoves(int n, int[] X) {
        int wrongNumbers = 0;
        int wrongOnEvenPositions = 0;
        int wrongOnOddPositions = 0;
        int goodSurplus = 0;
        int wrongOnEvenPositionsSum = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                if (X[i] % 2 != 0) {
                    wrongOnEvenPositions++;
                    wrongOnEvenPositionsSum += X[i];
                    wrongNumbers++;
                } else if (X[i] > 2) goodSurplus += X[i] - 2;

            }
            if (i % 2 != 0) {
                if (X[i] % 2 == 0) {
                    wrongOnOddPositions++;
                    wrongNumbers++;
                } else if (X[i] > 1) goodSurplus += X[i] - 1;
            }
        }

//        System.out.println("wrong numbers = "+wrongNumbers);
//        System.out.println("wrong on even = "+wrongOnEvenPositions);
//        System.out.println("wrong on even sum = "+wrongOnEvenPositionsSum);
//        System.out.println("wrong on odd = "+wrongOnOddPositions);
        if (wrongNumbers == 0) return 0;
        if (wrongNumbers % 2 != 0) return -1;
        if (wrongOnEvenPositions == wrongOnOddPositions)
            return wrongOnEvenPositions;
        if (wrongOnOddPositions == wrongNumbers) return wrongOnOddPositions / 2;
        if (wrongOnEvenPositionsSum == wrongOnEvenPositions && wrongOnEvenPositions == wrongNumbers && goodSurplus >= wrongNumbers)
            return wrongOnEvenPositions;
        if (wrongOnEvenPositions == wrongNumbers) return wrongOnEvenPositions / 2;
        for (int i = 0; i < ((wrongOnEvenPositions > wrongOnOddPositions) ? wrongOnOddPositions : wrongOnEvenPositions); i++) {
            wrongOnEvenPositions--;
            wrongOnOddPositions--;
            wrongNumbers -= 2;
        }
        if (wrongNumbers == 0) return 0;
        if (wrongOnEvenPositions == wrongOnOddPositions)
            return wrongOnEvenPositions;
        if (wrongOnOddPositions == wrongNumbers) return wrongOnOddPositions / 2;
        if (wrongOnEvenPositionsSum == wrongOnEvenPositions && wrongOnEvenPositions == wrongNumbers && goodSurplus >= wrongNumbers)
            return wrongOnEvenPositions;
        if (wrongOnEvenPositions == wrongNumbers) return wrongOnEvenPositions / 2;
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //  Return the minimum number of chocolates that need to be moved, or -1 if it's impossible.
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int n = in.nextInt();
            int[] X = new int[n];
            for (int X_i = 0; X_i < n; X_i++) {
                X[X_i] = in.nextInt();
            }
            int result = minimumChocolateMoves(n, X);
            System.out.println(result);
        }
    }
}
