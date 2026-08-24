package corejava;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


class TrieNode {

    private final Map<Character, TrieNode> children =
        new TreeMap<>();

    private boolean isEndOfWord;

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }
}

class Trie {

    private final TrieNode root = new TrieNode();

    /**
     * Insert a word into the Trie.
     */
    public void insert(String word) {

        if (word == null || word.isBlank()) {
            return;
        }

        word = word.toLowerCase();

        TrieNode current = root;

        for (char ch : word.toCharArray()) {

            current.getChildren()
                    .putIfAbsent(ch, new TrieNode());

            current = current.getChildren().get(ch);
        }

        current.setEndOfWord(true);
    }

    /**
     * Search for an exact word.
     */
    public boolean search(String word) {

        if (word == null || word.isBlank()) {
            return false;
        }

        TrieNode node = findNode(word.toLowerCase());

        return node != null && node.isEndOfWord();
    }

    /**
     * Check whether any word starts with the given prefix.
     */
    public boolean startsWith(String prefix) {

        if (prefix == null || prefix.isBlank()) {
            return false;
        }

        return findNode(prefix.toLowerCase()) != null;
    }

    /**
     * Return autocomplete suggestions.
     */
    public List<String> autocomplete(String prefix) {

        List<String> suggestions = new ArrayList<>();

        if (prefix == null || prefix.isBlank()) {
            return suggestions;
        }

        prefix = prefix.toLowerCase();

        TrieNode prefixNode = findNode(prefix);

        if (prefixNode == null) {
            return suggestions;
        }

        collectWords(
                prefixNode,
                prefix,
                suggestions
        );

        return suggestions;
    }

    /**
     * Find the Trie node corresponding to a word/prefix.
     */
    private TrieNode findNode(String text) {

        TrieNode current = root;

        for (char ch : text.toCharArray()) {

            TrieNode next =
                    current.getChildren().get(ch);

            if (next == null) {
                return null;
            }

            current = next;
        }

        return current;
    }

    /**
     * DFS traversal to collect complete words.
     */
    private void collectWords(
            TrieNode node,
            String currentWord,
            List<String> suggestions) {

        if (node.isEndOfWord()) {
            suggestions.add(currentWord);
        }

        for (Map.Entry<Character, TrieNode> entry
                : node.getChildren().entrySet()) {

            char ch = entry.getKey();

            collectWords(
                    entry.getValue(),
                    currentWord + ch,
                    suggestions
            );
        }
    }
}

public class TrieAutocompleteDemo {

    public static void main(String[] args) {

        Trie trie = new Trie();

        // Insert dictionary words
        trie.insert("java");
        trie.insert("javascript");
        trie.insert("javadoc");
        trie.insert("jar");
        trie.insert("junit");
        trie.insert("spring");
        trie.insert("spring boot");
        trie.insert("spring cloud");
        trie.insert("spring security");
        trie.insert("docker");
        trie.insert("kubernetes");

        // Exact search
        System.out.println("Search java: "
                + trie.search("java"));

        System.out.println("Search jav: "
                + trie.search("jav"));

        // Prefix search
        System.out.println("Starts with 'jav': "
                + trie.startsWith("jav"));

        System.out.println("Starts with 'xyz': "
                + trie.startsWith("xyz"));

        // Autocomplete
        System.out.println("\nAutocomplete 'jav':");

        List<String> suggestions =
                trie.autocomplete("jav");

        suggestions.forEach(System.out::println);

        System.out.println("\nAutocomplete 'spring':");

        trie.autocomplete("spring")
                .forEach(System.out::println);
    }
}
