package WeekOfCode34.OnceInATram;

/**
 * Created by Rafal on 02017-07-17.
 */

import java.util.Scanner;

public class Solution {

    static String onceInATram(int x) {
        // Complete this function
        for (int i = ++x; i < 999999; i++) {
            //System.out.println(" i = "+i);
            String s = Integer.toString(i);
            int[] xs = new int[6];
            for (int j = 0; j < 6; j++) {
                xs[j] = s.charAt(j) - '0';
            }
            //System.out.println("xs = "+Arrays.toString(xs));
            int lsum = 0, rsum = 0;
            for (int j = 0; j < 3; j++) {
                lsum += xs[j];
                rsum += xs[5 - j];
            }
            //System.out.println("lsum = "+lsum+" rsum = "+rsum);
            if (lsum == rsum) return Integer.toString(i);
        }
        return Integer.toString(x);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        String result = onceInATram(x);
        System.out.println(result);
    }
}
