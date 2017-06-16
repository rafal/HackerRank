package WeekOfCode33.TransformToPalindrome;

/**
 * Created by Rafal on 02017-06-14.
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import static java.util.Collections.swap;

public class Solution {
    static List<Set<Integer>> connections = new LinkedList<>();

    static int howManyCharsToPalindrome(List<Integer> list) {
        int n = list.size();
//        System.out.println("n = "+n);
//        System.out.println("n/2 = "+(int)Math.round((double)n/2));
        int charsToPalindrome = n - (int) Math.round((double) n / 2);
//        System.out.println("charsToPalindrome = "+charsToPalindrome);
        for (int i = n - 1, j = 0; i > n / 2 && j < n; i--, j++) {
//            System.out.println("Comparing "+list.get(i)+" to "+list.get(j));
            if (list.get(i).equals(list.get(j))) charsToPalindrome--;
        }
        return charsToPalindrome;
    }

    static void addConnections2(int x, int y) {
        boolean connectionFound = false;
        for (Set<Integer> set : connections
                ) {
            if (set.contains(x) || set.contains(y)) {
                set.add(x);
                set.add(y);
                connectionFound = true;
            }
        }
        List<Set<Integer>> setList = connections;
        outer:
        for (int i = 0; i < setList.size(); i++) {
            for (Integer ii : setList.get(i)
                    ) {
                for (int j = i + 1; j < setList.size(); j++) {
                    if (setList.get(j).contains(ii)) {
                        setList.get(i).addAll(setList.get(j));
                        setList.remove(j);
                        continue outer;
                    }
                }
            }
        }
        if (!connectionFound) {
            Set<Integer> newSet = new HashSet<>();
            newSet.add(x);
            newSet.add(y);
            setList.add(newSet);
        }
        connections = setList;
//        System.out.println(connections);
    }

//    static void addConnections(int x, int y) {
//        boolean found = false;
//        for (Set<Integer> connectionSet : connections
//                ) {
//
//            if (connectionSet.contains(x)) {
//                connectionSet.add(y);
//                found = true;
//                continue;
//            }
//            if (connectionSet.contains(y)) {
//                connectionSet.add(x);
//                found = true;
//                continue;
//            }
//            System.out.println(connections);
//
//        }
//        if (found) return;
//        HashSet<Integer> newSet = new HashSet<>();
//        newSet.add(x);
//        newSet.add(y);
//        connections.add(newSet);
//        System.out.println(connections);
//    }

    static boolean canISwap(int x, int y) {
//        addConnections(1, 2);
//        addConnections(1, 3);
//        addConnections(2, 3);
//        addConnections(2, 7);
//        addConnections(2, 7);
//        addConnections( 4,5);
//        addConnections( 6,8);
//        addConnections( 6,9);
//        addConnections( 10,5);

        for (Collection<Integer> coll : connections
                ) {
            if (coll.contains(x) && coll.contains(y)) return true;
        }
        return false;
    }

    static boolean shouldISwap(int x, int y) {
        return x != y;
    }

    static List<Integer> createPalindrome2(List<Integer> list) {
        List<Integer> reversed = new LinkedList<>();
        reversed.addAll(list);
        Collections.reverse(reversed);
//        System.out.println("list = "+list);
//        System.out.println("test = "+reversed);
        if (list.equals(reversed)){
//            System.out.println("PALINDROM GET:");
//            System.out.println(list);
            return list;
        }
        int n = list.size();
        List<Integer> half1 = new LinkedList<>();
        for (int i = 0; i < n/2; i++) {
            half1.add(list.get(i));
        }
        List<Integer> half2 = new LinkedList<>();
        for (int i = n/2; i < n; i++) {
            half2.add(list.get(i));
        }
        Collections.reverse(half2);
//        System.out.println("half1 = " + half1);
//        System.out.println("half2reversed = " + half2);
//        System.out.println("Before comparing, will stop at <" + list.get(n / 2));
        outer:
        for (int i = 0; i < n / 2; i++) {
            List<Integer> temppp = new LinkedList<>();
            temppp.addAll(list);
            Collections.reverse(temppp);
            if (list.equals(temppp)) return list;
//            System.out.println("Comparing " + half1.get(i) + " to " + half2.get(i));
            if (!half1.get(i).equals(half2.get(i))) {
//                System.out.println("They're not equal, can I swap?");
                if (canISwap(half1.get(i), half2.get(i))) {
//                    System.out.println("Yes, I can, swapping...");
                    half1.remove(i);
                    half1.add(i, half2.get(i));
//                    System.out.println("The list is now:");
//                    System.out.println(half1);
//                    System.out.println(half2);
                    if (half1.toString().equals(half2.toString())){
//                        System.out.println("FUCING EQUAL");
                        break outer;}

//                    List<Integer> half11 = new LinkedList<>();
//                    half11.addAll(half1);
//                    List<Integer> half22 = new LinkedList<>();
//                    half22.addAll(half2);
//                    Collections.reverse(half22);
//                    LinkedList<Integer> listt = new LinkedList<>();
//                    half11.addAll(half22);
//                    listt.addAll(half11);
//                    System.out.println(listt);
                }else {
//                    System.out.println("No, can't swap. Deleting "+half1.get(i));
                    half1.remove(i);
                    list.clear();
                    list.addAll(half1);
                    List<Integer> temp = new LinkedList<>();
                    temp.addAll(half2);
                    Collections.reverse(temp);
                    list.addAll(temp);
                    createPalindrome2(list);
                    List<Integer> tempp = new LinkedList<>();
                    tempp.addAll(list);
                    Collections.reverse(tempp);
                    if (list.equals(tempp)) return list;
                }
            }
        }
        Collections.reverse(half2);
        list.clear();
        list.addAll(half1);
        list.addAll(half2);
        return list;
    }
//
//    static List<Integer> createPalindrome(List<Integer> list) {
//        int charsToPalindrome = howManyCharsToPalindrome(list);
////        System.out.println("charsToPalindrome = " + charsToPalindrome);
//        int n = list.size();
////        System.out.println("n = " + n);
////        System.out.println("Original list: " + list);
////        while (charsToPalindrome > 0) {
//        for (int i = n - 1, j = 0; i >= n / 2 && j < n; i--, j++) {
////            System.out.println("Should I swap " + list.get(i)
////                    + " with " + list.get(j) + "?");
//            if (!shouldISwap(list.get(i), list.get(j))) {
////                System.out.println("No, I shouldn't, continuing to next char");
//                continue;
//            } else {
////                System.out.println("Yes, I should. But Can I?");
//                if (canISwap(list.get(i), list.get(j))) {
////                    System.out.println("Yes, I can swap.");
//                    list.remove(i);
//                    list.add(i, list.get(j));
////                    System.out.println("Swapped. List after swap:");
////                    System.out.println(list);
//                    charsToPalindrome--;
////                    System.out.println("charsToPalindrome = " + charsToPalindrome);
//                } else {
////                    System.out.println("I can't swap. Deleting " + list.get(i));
//                    list.remove(i);
//                    createPalindrome(list);
//                    break;
////                        charsToPalindrome--;
////                        System.out.println("charsToPalindrome = "+charsToPalindrome);
//                }
//            }
//        }
////        }
//        return list;
//    }

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();
        for (int a0 = 0; a0 < k; a0++) {
            int x = in.nextInt();
            int y = in.nextInt();
            addConnections2(x, y);
        }
        int[] a = new int[m];
        for (int a_i = 0; a_i < m; a_i++) {
            list.add(in.nextInt());
        }

//        list.add(1);
//        list.add(4);
//        list.add(5);
//        list.add(7);
//        list.add(9);
//        list.add(8);
//        list.add(1);
//        list.add(3);
//        list.add(10);
//        list.add(4);
//        list.add(5);
//        list.add(10);
//        list.add(2);
//        list.add(7);
//        list.add(8);

        System.out.println(createPalindrome2(list).size());
        //System.out.println(new StringBuilder("RAF").replace(0,1,"K"));


        // isPalindrome() System.out.println("FARIFAR".equals(new StringBuilder("RAFIRAF").reverse().toString()));
    }
}
