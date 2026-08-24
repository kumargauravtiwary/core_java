package java2;

import java.util.*;

public class MergeSortedLists {

    public static List<Integer> merge(
            List<Integer> list1,
            List<Integer> list2) {

        List<Integer> result =
                new ArrayList<>(list1.size() + list2.size());

        int i = 0;
        int j = 0;

        // Compare elements from both lists
        while (i < list1.size() && j < list2.size()) {

            if (list1.get(i) <= list2.get(j)) {
                result.add(list1.get(i));
                i++;
            } else {
                result.add(list2.get(j));
                j++;
            }
        }

        // Add remaining elements from list1
        while (i < list1.size()) {
            result.add(list1.get(i));
            i++;
        }

        // Add remaining elements from list2
        while (j < list2.size()) {
            result.add(list2.get(j));
            j++;
        }

        return result;
    }

    public static void main(String[] args) {

        List<Integer> list1 =
                Arrays.asList(1, 3, 5, 7, 9);

        List<Integer> list2 =
                Arrays.asList(2, 4, 6, 8, 10);

        List<Integer> merged = merge(list1, list2);

        System.out.println("List 1 : " + list1);
        System.out.println("List 2 : " + list2);
        System.out.println("Merged : " + merged);
    }
}
