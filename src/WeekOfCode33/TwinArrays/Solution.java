package WeekOfCode33.TwinArrays;

import java.util.Scanner;
import java.util.TreeSet;

public class Solution {

    static int twinArrays(int[] ar1, int[] ar2) {
        // Complete this function
        TreeSet<Integer> possibilities = new TreeSet<>();
        possibilities.add(ar1[0] + ar2[0]);
        for (int i = 0; i < ar1.length; i++) {
            if (ar1[i] > possibilities.first()) continue;
            for (int j = 0; j < ar2.length; j++) {
                if (ar2[j] > possibilities.first()) continue;
                if (i != j)
                    possibilities.add(ar1[i] + ar2[j]);
            }
        }
        return possibilities.first();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar1 = new int[n];
        for (int ar1_i = 0; ar1_i < n; ar1_i++) {
            ar1[ar1_i] = in.nextInt();
        }
        int[] ar2 = new int[n];
        for (int ar2_i = 0; ar2_i < n; ar2_i++) {
            ar2[ar2_i] = in.nextInt();
        }
        int result = twinArrays(ar1, ar2);
        System.out.println(result);
    }
}
