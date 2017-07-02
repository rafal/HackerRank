///**
// * Created by Rafal on 02017-06-20.
// */
//
//import java.util.Scanner;
//
//public class Solution2 {
//    static int minimumChocolateMoves(int n, int[] X) {
//        for (int i = 0; i < n; i++) {
//            if (i % 2 == 0) {
//                if (X[i] % 2 == 0) {
//
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        //  Return the minimum number of chocolates that need to be moved, or -1 if it's impossible.
//        int q = in.nextInt();
//        for (int a0 = 0; a0 < q; a0++) {
//            int n = in.nextInt();
//            int[] X = new int[n];
//            for (int X_i = 0; X_i < n; X_i++) {
//                X[X_i] = in.nextInt();
//            }
//            int result = minimumChocolateMoves(n, X);
//            System.out.println(result);
//        }
//    }
//
//    class Numer {
//        int value;
//        int position;
//        String shouldBe;
//        boolean odd;
//        boolean even;
//        boolean underweight;
//        boolean valid;
//        int goodSurplus;
//        int badSurplus;
//
//        Numer(int pos, int value) {
//            this.value = value;
//            position = pos;
//            if (pos % 2 == 0) {
//                shouldBe = "even";
//            } else shouldBe = "odd";
//            if (value % 2 == 0) {
//                odd = false;
//                even = true;
//            }
//            valid = value % 2 == 0 && pos % 2 == 0;
//            if (val)
//
//        }
//    }
//}
