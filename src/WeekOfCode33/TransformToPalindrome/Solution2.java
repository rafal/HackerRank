package WeekOfCode33.TransformToPalindrome;

import java.util.*;

/**
 * Created by Rafal on 02017-06-15.
 */
public class Solution2 {
    public static void main(String[] args) {
        int[][] numbers = {new int[]{1, 2, 3}, new int[]{1}};
        Map<Integer, Integer> freqs = new HashMap<>();
        int count;
        for (int[] array : numbers
                ) {
            for (int i : array
                    ) {
                count = (freqs.containsKey(i)) ? freqs.get(i) : 0;
                freqs.put(i, count + 1);
            }
        }
        int pairs = 0;
        for (int i = 0; i < numbers.length / 2; i++) {
            int[] arr1 = numbers[i];
            int[] arr2 = numbers[numbers.length - 1 - i];
            HashSet<Integer> tmp = new HashSet<>();
            for (int ii : arr1
                    ) {
                tmp.add(ii);
            }
            for (int ii : arr2
                    ) {
                if (tmp.contains(ii)) {pairs++; break;}
            }
        }
        int maxFreq = Collections.max(freqs.values());
        if (pairs * 2 > maxFreq) {
            System.out.println("More pairs");
            System.out.println(pairs * 2);
        } else {
            System.out.println("More freq");
            System.out.println(maxFreq);
        }
    }

}
