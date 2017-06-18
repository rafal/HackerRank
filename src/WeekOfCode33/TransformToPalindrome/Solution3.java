package WeekOfCode33.TransformToPalindrome;

import java.util.*;

/**
 * Created by Rafal on 02017-06-18.
 */
public class Solution3 {
    static Map<Integer, Set<Integer>> transformations = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int k = in.nextInt();
        int k = 1000000;
//        int m = in.nextInt();
        int m = 1000;
        Random random = new Random();
        for (int a0 = 0; a0 < k; a0++) {
//            int x = in.nextInt();
            int x = random.nextInt(100000+1);
//            int y = in.nextInt();
            int y = random.nextInt(100000+1);
            addTransformation(x, y);
        }
        int[] numbers = new int[m];
        for (int a_i = 0; a_i < m; a_i++) {
//            numbers[a_i] = in.nextInt();
            numbers[a_i] = random.nextInt(100000+1);
        }
        if (numbers.length == 1) {
            System.out.println(1);
            return;
        }
//        System.out.println("transformations = "+ transformations);
        List<Set<Integer>> multiNumbers = new ArrayList<>();

        //!!!!!!!!!!!!!!!!!!
        List<Integer> maxs = new ArrayList<>();
        List<Integer> numberList = new ArrayList<>();
        for (int i : numbers
                ) {
            numberList.add(i);
        }
//        System.out.println("numberList = "+numberList);
        List<List<Integer>> subLists = new ArrayList<>();
        for (int i = 0; i < numbers.length - 1; i++) {
            List<Integer> subList = new ArrayList<>();
            subList = numberList.subList(i, numberList.size());
//            System.out.println("sublist ="+subList);
            List<Integer> subxList = new ArrayList<>();
            subxList.addAll(subList);
            subLists.add(subxList);
//            System.out.println("Found sublist " +subxList);
        }
//        System.out.println("Sublists = "+subLists);
        // UP IS OK!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        for (int number : numbers
                ) {
            if (transformations.containsKey(number)) {
                Set<Integer> set = transformations.get(number);
                Set<Integer> newSet = new HashSet<>();
                newSet.addAll(set);
                for (Integer j : set
                        ) {
                    newSet.addAll(transformations.get(j));
                }
                multiNumbers.add(newSet);
            } else {
                multiNumbers.add(new HashSet<>(Arrays.asList(number)));
            }
        }

        // new impl end
//        for (List<Integer> subbList : subLists
//                ) {
//            for (int number : subbList
//                    ) {
//                if (transformations.containsKey(number)) {
//                    Set<Integer> set = transformations.get(number);
//                    Set<Integer> newSet = new HashSet<>();
//                    newSet.addAll(set);
//                    for (Integer j : set
//                            ) {
//                        newSet.addAll(transformations.get(j));
//                    }
//                    multiNumbers.add(newSet);
//                } else {
//                    multiNumbers.add(new HashSet<>(Arrays.asList(number)));
//                }
//            }
//            maxs.add(countMaxPalindrom(multiNumbers));
////            System.out.println(multiNumbers);
//            multiNumbers.clear();
//        }
//        int maxPalindrom = Collections.max(maxs);
        int maxPalindrom = countMaxPalindrom2(multiNumbers);

//        System.out.println("Max Palindrome Length = " + maxPalindrom);
        System.out.println(maxPalindrom);
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

    static int countMaxPalindrom(List<Set<Integer>> list) {
        int pairs = 0;
        int start = -1;
        int end = list.size();
        boolean lastPairNextToEachOther = false;
        for (int i = 0; i < list.size(); i++) {
            if (i < start) continue;
            for (int j = list.size() - 1; j > i; j--) {
                if (i <= start || j >= end) continue;
//                System.out.println("Comparing items " + list.get(i) + " & " + list.get(j));
                if (!Collections.disjoint(list.get(i), list.get(j))) {
                    pairs++;
//                    System.out.println("Found a pair");
                    start = i;
                    end = j;
                    if ((i + 1) == j) lastPairNextToEachOther = true;
                }
            }
        }
        return (lastPairNextToEachOther) ? pairs * 2 : pairs * 2 + 1;
    }

    static int countMaxPalindrom2(List<Set<Integer>> multiNumbers) {
        int n = multiNumbers.size();
        int i, j, cl;
        int L[][] = new int[n][n]; // results of subproblems
        for (i = 0; i < n; i++) {
            L[i][i] = 1;
        }
        for (cl = 2; cl <= n; cl++) {
            for (i = 0; i < n - cl + 1; i++) {
                j = i + cl - 1;
                if ((!Collections.disjoint(multiNumbers.get(i), multiNumbers.get(j))) && cl == 2)
                    L[i][j] = 2;
                else if (!Collections.disjoint(multiNumbers.get(i), multiNumbers.get(j)))
                    L[i][j] = L[i + 1][j - 1] + 2;
                else L[i][j] = Math.max(L[i][j - 1], L[i + 1][j]);
            }
        }
        return L[0][n - 1];
    }
}