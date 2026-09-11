package Strings;
//Group a list of strings into anagram groups.
import java.util.*;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] words) {

        Map<String, List<String>> map = new HashMap<>();

        for (String word : words) {

            char[] chars = word.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);

            map.computeIfAbsent(key, k -> new ArrayList<>())
               .add(word);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {

        String[] words = {
            "eat", "tea", "tan", "ate",
            "nat", "bat"
        };

        System.out.println(groupAnagrams(words));
    }
}
