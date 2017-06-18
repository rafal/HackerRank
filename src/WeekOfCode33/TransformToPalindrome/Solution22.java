package WeekOfCode33.TransformToPalindrome;

import java.util.*;

/**
 * Created by Rafal on 02017-06-15.
 */
public class Solution22 {
    static Map<Integer, Set<Integer>> transformations = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();
        for(int a0 = 0; a0 < k; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            addTransformation(x,y);
        }
        int[] numbers = new int[m];
        for(int a_i=0; a_i < m; a_i++){
            numbers[a_i] = in.nextInt();
        }
        System.out.println(transformations);
        List<Set<Integer>> multiNumbers = new ArrayList<>();
        for (int i : numbers
                ) {
            if (transformations.containsKey(i)) {
                Set<Integer> set = transformations.get(i);
                Set<Integer> newSet = new HashSet<>();
                newSet.addAll(set);
                for (Integer j : set
                        ) {
                    newSet.addAll(transformations.get(j));
                }
                multiNumbers.add(newSet);
            } else multiNumbers.add(new HashSet<>(Arrays.asList(i)));

        }
        System.out.println(multiNumbers);
        Map<Integer, Integer> freqs = new HashMap<>();
        int count;
        for (Set<Integer> set : multiNumbers
                ) {
            for (int i : set
                    ) {
                count = (freqs.containsKey(i)) ? freqs.get(i) : 0;
                freqs.put(i, count + 1);
            }
        }
        int maxPalindrom = countMaxPalindrom(multiNumbers);

        System.out.println(maxPalindrom);
    }

    static int countMaxPalindrom(List<Set<Integer>> list) {
        int pairs = 0;
        int start = -1;
        int end = list.size();
        boolean lastPairNextToEachOther = false;
        for (int i = 0; i < list.size(); i++) {
            if (i < start) continue;
            for (int j = list.size() - 1; j > i; j--) {
                if (i <= start || j >= end) continue;
                System.out.println("Comparing items " + list.get(i) + " & " + list.get(j));
                if (!Collections.disjoint(list.get(i), list.get(j))) {
                    pairs++;
                    System.out.println("Found a pair");
                    start = i;
                    end = j;
                    if ((i + 1) == j) lastPairNextToEachOther = true;
                }
            }
        }
        return (lastPairNextToEachOther) ? pairs * 2 : pairs * 2 + 1;
    }

    static void addTransformation(int x, int y) {
        boolean hasX = transformations.containsKey(x);
        boolean hasY = transformations.containsKey(y);
        if (!hasX) transformations.put(x, new HashSet<>(Arrays.asList(x, y)));
        if (!hasY) transformations.put(y, new HashSet<>(Arrays.asList(y, x)));
        Set<Integer> xs = transformations.get(x);
        Set<Integer> ys = transformations.get(y);
        xs.add(y);
        transformations.put(x, xs);
        ys.add(x);
        transformations.put(y, ys);
    }

}