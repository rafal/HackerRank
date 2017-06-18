package WeekOfCode33.TransformToPalindrome;

/**
 * Created by Rafal on 02017-06-14.
 */

import java.util.*;

public class Solution {
    static ArrayList<HashSet<Integer>> connections = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();
        for (int a0 = 0; a0 < k; a0++) {
            int x = in.nextInt();
            int y = in.nextInt();
            addConnections(x, y);
        }
        int[] a = new int[m];
        for (int a_i = 0; a_i < m; a_i++) {
            a[a_i] = in.nextInt();
        }
        ArrayList<HashSet<Integer>> multiNumbers = new ArrayList<>();
        outer:
        for (int number : a
                ) {
            for (HashSet<Integer> set : connections
                    ) {
                if (set.contains(number)) {
                    multiNumbers.add(set);
                    continue outer;
                }
            }
            multiNumbers.add(new HashSet<>(Collections.singletonList(number)));
        }
        System.out.println(countMaxPalindrom(multiNumbers));
    }

    static void addConnections(int x, int y) {
        boolean connectionFound = false;
        for (Set<Integer> set : connections
                ) {
            if (set.contains(x) || set.contains(y)) {
                set.add(x);
                set.add(y);
                connectionFound = true;
            }
        }
        if (!connectionFound) connections.add(new HashSet<>(Arrays.asList(x,y)));
    }

    static int countMaxPalindrom(ArrayList<HashSet<Integer>> multiNumbers) {
        int n = multiNumbers.size();
        int i, j, cl;
        int L[][] = new int[n][n]; // results of subproblems
        for (i = 0; i < n; i++) {
            L[i][i] = 1;
        }
        for (cl = 2; cl <= n; cl++) {
            for (i = 0; i < n - cl + 1; i++) {
                Set<Integer> first = multiNumbers.get(i);
                j = i + cl - 1;
                Set<Integer> sec = multiNumbers.get(j);
                if ((!Collections.disjoint(first, sec)) && cl == 2)
                    L[i][j] = 2;
                else if (!Collections.disjoint(first, sec))
                    L[i][j] = L[i + 1][j - 1] + 2;
                else L[i][j] = Math.max(L[i][j - 1], L[i + 1][j]);
            }
        }
        return L[0][n - 1];
    }
}
