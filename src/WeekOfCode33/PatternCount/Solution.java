package WeekOfCode33.PatternCount;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int patternCount(String s){
        // Complete this function
        int count = 0;
        Pattern pattern = Pattern.compile("10+1");
        Matcher matcher = pattern.matcher(s);
        int i = 0;
        while (matcher.find(i)){
            count++;
            i = matcher.end()-1;

        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = patternCount(s);
            System.out.println(result);
        }
    }
}
