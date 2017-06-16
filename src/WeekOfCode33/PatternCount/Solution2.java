package WeekOfCode33.PatternCount;

import java.util.Scanner;

public class Solution2 {

    static int patternCount(String s) {
        int count = 0;
        if (s.length()<3) return count;
        for (int i = 0; i < s.length(); i++) {
            System.out.println("i = " + i);
            if (s.length()-1==i) break;
            if (s.charAt(i)=='1'&&s.charAt(i+1)=='0'&&i<s.length()-2){
                for (i = i+2; i < s.length(); i++) {
                    System.out.println("i = " + i);
                    if (s.charAt(i)=='0') {
                        System.out.println("s[i] = "+s.charAt(i)+"continue");
                        continue;
                    }
                    else if (s.charAt(i)=='1') {
                        count++;
                        i--;
                        break;
                    }
                    else break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            String s = in.next();
            int result = patternCount(s);
            System.out.println(result);
        }
    }
}
