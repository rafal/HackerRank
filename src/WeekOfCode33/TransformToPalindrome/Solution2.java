package WeekOfCode33.TransformToPalindrome;

import java.util.*;

/**
 * Created by Rafal on 02017-06-15.
 */
public class Solution2 {
    static List<List<Integer>> oneStepTransformations = new ArrayList<>();
    static List<List<Integer>> reversedTransformations = new ArrayList<>();
    static List<List<Integer>> twoStepsTransformations = new ArrayList<>();
    static Map<Integer, Set<Integer>> jointTransformations = new HashMap<>();
    static List<Set<Integer>> multiNumbers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();
        for (int a0 = 0; a0 < k; a0++) {
            int x = in.nextInt();
            int y = in.nextInt();
            addOneStepTransformations(x, y);
        }
        int[] numbers = new int[m];

        for (int a_i = 0; a_i < m; a_i++) {
            numbers[a_i] = in.nextInt();
        }
        if (numbers.length==1){
            System.out.println(1);
            return;
        }
//        System.out.println("Transformations = " + oneStepTransformations);
        createReversedTransformations();
//        System.out.println("Reversed transformations = " + reversedTransformations);
        addTwoStepsTransformations();
//        System.out.println("Two Steps Transformations = " + twoStepsTransformations);
        createJointTransformations();
//        System.out.println("JointTransformations = " + jointTransformations);

//        System.out.println("MultiNumbers = " + multiNumbers);
        for (int number : numbers
                ) {
            if (jointTransformations.keySet().contains(number)) {
                Set<Integer> multiNumber = new HashSet<>();
                multiNumber.addAll(jointTransformations.get(number));
                multiNumber.add(number);
                multiNumbers.add(multiNumber);
            } else {
                multiNumbers.add(new HashSet<>(Arrays.asList(number)));
            }
        }
        int maxPalindrom = countMaxPalindrom2(multiNumbers);

//        System.out.println("Max Palindrome Length = " + maxPalindrom);
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


    static void addOneStepTransformations(int x, int y) {
        oneStepTransformations.add(new ArrayList<>(Arrays.asList(x, y)));
    }

    static void addTwoStepsTransformations() {
        List<Integer> oneStepTransformation1;
        List<Integer> oneStepTransformation2;
        int transformation1TransformFrom;
        int transformation1TransformTo;
        int transformation2TransformFrom;
        int transformation2TransformTo;
        for (int i = 0; i < oneStepTransformations.size(); i++) {
            oneStepTransformation1 = oneStepTransformations.get(i);
            transformation1TransformFrom = oneStepTransformation1.get(0);
            transformation1TransformTo = oneStepTransformation1.get(1);
            for (int j = 0; j < oneStepTransformations.size() && i != j; j++) {
                oneStepTransformation2 = oneStepTransformations.get(j);
//                System.out.println("Comparing transformations " + oneStepTransformation1 + " & " + oneStepTransformation2);
                transformation2TransformFrom = oneStepTransformation2.get(0);
                transformation2TransformTo = oneStepTransformation2.get(1);
                if (transformation1TransformTo == transformation2TransformFrom) {
                    twoStepsTransformations.add(new ArrayList<>(Arrays.asList(
                            transformation1TransformFrom, transformation2TransformTo
                    )));
                } else if (transformation1TransformFrom == transformation2TransformTo) {
                    twoStepsTransformations.add(new ArrayList<>(Arrays.asList(
                            transformation2TransformFrom, transformation1TransformTo
                    )));
                }
            }
        }
    }

    static void createReversedTransformations() {
        for (List<Integer> transformation : oneStepTransformations
                ) {
            reversedTransformations.add(new ArrayList<>(Arrays.asList(
                    transformation.get(1), transformation.get(0)
            )));
        }
    }

    static void createJointTransformations() {
        for (List<Integer> transformation : oneStepTransformations
                ) {
            if (jointTransformations.containsKey(transformation.get(0))) {
                Set<Integer> tmp = new HashSet<>();
                tmp.addAll(jointTransformations.get(transformation.get(0)));
                tmp.add(transformation.get(1));
                jointTransformations.put(transformation.get(0), tmp);
            } else {
                jointTransformations.put(transformation.get(0), new HashSet<>(Arrays.asList(transformation.get(1))));
            }
        }
        for (List<Integer> transformation : reversedTransformations
                ) {
            if (jointTransformations.containsKey(transformation.get(0))) {
                Set<Integer> tmp = new HashSet<>();
                tmp.addAll(jointTransformations.get(transformation.get(0)));
                tmp.add(transformation.get(1));
                jointTransformations.put(transformation.get(0), tmp);
            } else {
                jointTransformations.put(transformation.get(0), new HashSet<>(Arrays.asList(transformation.get(1))));
            }
        }
        for (List<Integer> transformation : twoStepsTransformations
                ) {
            if (jointTransformations.containsKey(transformation.get(0))) {
                Set<Integer> tmp = new HashSet<>();
                tmp.addAll(jointTransformations.get(transformation.get(0)));
                tmp.add(transformation.get(1));
                jointTransformations.put(transformation.get(0), tmp);
            } else {
                jointTransformations.put(transformation.get(0), new HashSet<>(Arrays.asList(transformation.get(1))));
            }
        }
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
