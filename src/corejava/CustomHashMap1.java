//Write a program to detect and remove duplicates from a List without using Set

package corejava;

import java.util.Objects;

public class CustomHashMap1<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private static final int TREEIFY_THRESHOLD = 8;
    private static final int UNTREEIFY_THRESHOLD = 6;
    private static final int MIN_TREEIFY_CAPACITY = 64;

    private Node<K, V>[] table;
    private int size;

    // =========================================================
    // Normal Linked List Node
    // =========================================================

    private static class Node<K, V> {

        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // =========================================================
    // Red-Black Tree Node
    // =========================================================

    private static class TreeNode<K, V> extends Node<K, V> {

        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> parent;

        boolean red = true;

        TreeNode(K key, V value) {
            super(key, value);
        }
    }

    // =========================================================
    // Constructor
    // =========================================================

    @SuppressWarnings("unchecked")
    public CustomHashMap1() {
        table = new Node[DEFAULT_CAPACITY];
    }

    // =========================================================
    // PUT
    // =========================================================

    public void put(K key, V value) {

        int index = index(key);

        Node<K, V> first = table[index];

        // Empty bucket
        if (first == null) {

            table[index] = new Node<>(key, value);
            size++;

            resizeIfRequired();

            return;
        }

        // Bucket is already a Red-Black Tree
        if (first instanceof TreeNode) {

            TreeNode<K, V> root = (TreeNode<K, V>) first;

            TreeNode<K, V> existing = findTreeNode(root, key);

            if (existing != null) {
                existing.value = value;
                return;
            }

            root = insertTreeNode(root, key, value);

            table[index] = root;

            size++;

            resizeIfRequired();

            return;
        }

        // =====================================================
        // Linked List
        // =====================================================

        Node<K, V> current = first;

        int count = 1;

        while (true) {

            if (Objects.equals(current.key, key)) {

                current.value = value;
                return;
            }

            if (current.next == null) {
                break;
            }

            current = current.next;
            count++;
        }

        // Add new node
        current.next = new Node<>(key, value);

        size++;

        /*
         * count represents the number of existing nodes.
         * After insertion we have count + 1 nodes.
         */
        if (count + 1 >= TREEIFY_THRESHOLD) {

            if (table.length >= MIN_TREEIFY_CAPACITY) {

                table[index] = convertToTree(table[index]);

            } else {

                resize();
            }
        }

        resizeIfRequired();
    }

    // =========================================================
    // GET
    // =========================================================

    public V get(K key) {

        int index = index(key);

        Node<K, V> first = table[index];

        if (first == null) {
            return null;
        }

        // Red-Black Tree
        if (first instanceof TreeNode) {

            TreeNode<K, V> node =
                    findTreeNode((TreeNode<K, V>) first, key);

            return node == null ? null : node.value;
        }

        // Linked List
        Node<K, V> current = first;

        while (current != null) {

            if (Objects.equals(current.key, key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    // =========================================================
    // REMOVE
    // =========================================================

    public V remove(K key) {

        int index = index(key);

        Node<K, V> first = table[index];

        if (first == null) {
            return null;
        }

        // =====================================================
        // Red-Black Tree
        // =====================================================

        if (first instanceof TreeNode) {

            TreeNode<K, V> root =
                    (TreeNode<K, V>) first;

            TreeNode<K, V> node =
                    findTreeNode(root, key);

            if (node == null) {
                return null;
            }

            V oldValue = node.value;

            root = deleteTreeNode(root, node);

            size--;

            /*
             * For simplicity we convert a small tree
             * back to a linked list.
             */
            if (countTreeNodes(root) <= UNTREEIFY_THRESHOLD) {

                table[index] = convertToList(root);

            } else {

                table[index] = root;
            }

            return oldValue;
        }

        // =====================================================
        // Linked List
        // =====================================================

        Node<K, V> current = first;
        Node<K, V> previous = null;

        while (current != null) {

            if (Objects.equals(current.key, key)) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;

                return current.value;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    // =========================================================
    // HASH
    // =========================================================

    private int index(K key) {

        if (key == null) {
            return 0;
        }

        int hash = key.hashCode();

        hash ^= (hash >>> 16);

        return (hash & 0x7fffffff) % table.length;
    }

    // =========================================================
    // CONVERT LINKED LIST → RED BLACK TREE
    // =========================================================

    private TreeNode<K, V> convertToTree(Node<K, V> head) {

        TreeNode<K, V> root = null;

        Node<K, V> current = head;

        while (current != null) {

            root = insertTreeNode(
                    root,
                    current.key,
                    current.value
            );

            current = current.next;
        }

        return root;
    }

    // =========================================================
    // TREE SEARCH
    // =========================================================

    @SuppressWarnings("unchecked")
    private TreeNode<K, V> findTreeNode(
            TreeNode<K, V> root,
            K key) {

        TreeNode<K, V> current = root;

        while (current != null) {

            int comparison = compareKeys(key, current.key);

            if (comparison == 0) {

                if (Objects.equals(key, current.key)) {
                    return current;
                }

                /*
                 * Same hash/comparison but different key.
                 * Search both sides.
                 */
                TreeNode<K, V> leftResult =
                        findTreeNode(current.left, key);

                if (leftResult != null) {
                    return leftResult;
                }

                current = current.right;

            } else if (comparison < 0) {

                current = current.left;

            } else {

                current = current.right;
            }
        }

        return null;
    }

    // =========================================================
    // KEY COMPARISON
    // =========================================================

    @SuppressWarnings("unchecked")
    private int compareKeys(K key1, K key2) {

        if (key1 == key2) {
            return 0;
        }

        if (key1 == null) {
            return -1;
        }

        if (key2 == null) {
            return 1;
        }

        if (key1 instanceof Comparable) {

            try {

                return ((Comparable<Object>) key1)
                        .compareTo(key2);

            } catch (ClassCastException ignored) {
                // Fall through
            }
        }

        /*
         * Deterministic fallback.
         */
        int hash1 = key1.hashCode();
        int hash2 = key2.hashCode();

        if (hash1 != hash2) {
            return Integer.compare(hash1, hash2);
        }

        return Integer.compare(
                System.identityHashCode(key1),
                System.identityHashCode(key2)
        );
    }

    // =========================================================
    // RED-BLACK TREE INSERT
    // =========================================================

    private TreeNode<K, V> insertTreeNode(
            TreeNode<K, V> root,
            K key,
            V value) {

        TreeNode<K, V> newNode =
                new TreeNode<>(key, value);

        if (root == null) {

            newNode.red = false;

            return newNode;
        }

        TreeNode<K, V> current = root;
        TreeNode<K, V> parent = null;

        int cmp = 0;

        while (current != null) {

            parent = current;

            cmp = compareKeys(key, current.key);

            if (cmp < 0) {

                current = current.left;

            } else {

                current = current.right;
            }
        }

        newNode.parent = parent;

        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        return fixAfterInsertion(root, newNode);
    }

    // =========================================================
    // FIX RED-BLACK TREE AFTER INSERT
    // =========================================================

    private TreeNode<K, V> fixAfterInsertion(
            TreeNode<K, V> root,
            TreeNode<K, V> node) {

        node.red = true;

        while (node != root
                && colorOf(parentOf(node))) {

            TreeNode<K, V> parent = parentOf(node);
            TreeNode<K, V> grandParent = parentOf(parent);

            if (parent == leftOf(grandParent)) {

                TreeNode<K, V> uncle =
                        rightOf(grandParent);

                if (colorOf(uncle)) {

                    setBlack(parent);
                    setBlack(uncle);
                    setRed(grandParent);

                    node = grandParent;

                } else {

                    if (node == rightOf(parent)) {

                        node = parent;

                        root = rotateLeft(root, node);

                        parent = parentOf(node);
                        grandParent = parentOf(parent);
                    }

                    setBlack(parent);
                    setRed(grandParent);

                    root = rotateRight(root, grandParent);
                }

            } else {

                TreeNode<K, V> uncle =
                        leftOf(grandParent);

                if (colorOf(uncle)) {

                    setBlack(parent);
                    setBlack(uncle);
                    setRed(grandParent);

                    node = grandParent;

                } else {

                    if (node == leftOf(parent)) {

                        node = parent;

                        root = rotateRight(root, node);

                        parent = parentOf(node);
                        grandParent = parentOf(parent);
                    }

                    setBlack(parent);
                    setRed(grandParent);

                    root = rotateLeft(root, grandParent);
                }
            }
        }

        setBlack(root);

        return root;
    }

    // =========================================================
    // ROTATE LEFT
    // =========================================================

    private TreeNode<K, V> rotateLeft(
            TreeNode<K, V> root,
            TreeNode<K, V> x) {

        if (x == null) {
            return root;
        }

        TreeNode<K, V> y = x.right;

        x.right = y.left;

        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {

            root = y;

        } else if (x == x.parent.left) {

            x.parent.left = y;

        } else {

            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;

        return root;
    }

    // =========================================================
    // ROTATE RIGHT
    // =========================================================

    private TreeNode<K, V> rotateRight(
            TreeNode<K, V> root,
            TreeNode<K, V> x) {

        if (x == null) {
            return root;
        }

        TreeNode<K, V> y = x.left;

        x.left = y.right;

        if (y.right != null) {
            y.right.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {

            root = y;

        } else if (x == x.parent.right) {

            x.parent.right = y;

        } else {

            x.parent.left = y;
        }

        y.right = x;
        x.parent = y;

        return root;
    }

    // =========================================================
    // DELETE
    // =========================================================

    private TreeNode<K, V> deleteTreeNode(
            TreeNode<K, V> root,
            TreeNode<K, V> node) {

        /*
         * For a production-quality implementation,
         * deletion requires the complete Red-Black
         * deletion balancing algorithm.
         *
         * Here we rebuild the tree after deletion.
         *
         * This keeps the example focused on the important
         * HashMap concept: treeification of collisions.
         */

        TreeNode<K, V> newRoot = null;

        newRoot = rebuildWithout(
                node,
                root,
                newRoot
        );

        if (newRoot != null) {
            newRoot.red = false;
        }

        return newRoot;
    }

    private TreeNode<K, V> rebuildWithout(
            TreeNode<K, V> target,
            TreeNode<K, V> current,
            TreeNode<K, V> newRoot) {

        if (current == null) {
            return newRoot;
        }

        if (current != target) {

            newRoot = insertTreeNode(
                    newRoot,
                    current.key,
                    current.value
            );
        }

        newRoot = rebuildWithout(
                target,
                current.left,
                newRoot
        );

        newRoot = rebuildWithout(
                target,
                current.right,
                newRoot
        );

        return newRoot;
    }

    // =========================================================
    // TREE → LINKED LIST
    // =========================================================

    private Node<K, V> convertToList(
            TreeNode<K, V> root) {

        Node<K, V> head = null;
        Node<K, V> tail = null;

        if (root == null) {
            return null;
        }

        /*
         * In-order traversal.
         */
        if (root.left != null) {

            Node<K, V> left =
                    convertToList(root.left);

            head = left;

            tail = left;

            while (tail.next != null) {
                tail = tail.next;
            }
        }

        Node<K, V> current =
                new Node<>(root.key, root.value);

        if (head == null) {
            head = current;
        } else {
            tail.next = current;
        }

        tail = current;

        if (root.right != null) {

            Node<K, V> right =
                    convertToList(root.right);

            tail.next = right;
        }

        return head;
    }

    // =========================================================
    // COUNT TREE NODES
    // =========================================================

    private int countTreeNodes(TreeNode<K, V> root) {

        if (root == null) {
            return 0;
        }

        return 1
                + countTreeNodes(root.left)
                + countTreeNodes(root.right);
    }

    // =========================================================
    // RESIZE
    // =========================================================

    private void resizeIfRequired() {

        if (size > table.length * LOAD_FACTOR) {
            resize();
        }
    }

    private void resize() {

        Node<K, V>[] oldTable = table;

        @SuppressWarnings("unchecked")
        Node<K, V>[] newTable =
                new Node[oldTable.length * 2];

        table = newTable;

        /*
         * Reinsert every key/value.
         */
        for (Node<K, V> bucket : oldTable) {

            if (bucket == null) {
                continue;
            }

            if (bucket instanceof TreeNode) {

                reinsertTree(
                        (TreeNode<K, V>) bucket
                );

            } else {

                Node<K, V> current = bucket;

                while (current != null) {

                    put(current.key, current.value);

                    current = current.next;
                }
            }
        }
    }

    private void reinsertTree(
            TreeNode<K, V> root) {

        if (root == null) {
            return;
        }

        put(root.key, root.value);

        reinsertTree(root.left);

        reinsertTree(root.right);
    }

    // =========================================================
    // RED-BLACK HELPERS
    // =========================================================

    private TreeNode<K, V> parentOf(
            TreeNode<K, V> node) {

        return node == null ? null : node.parent;
    }

    private TreeNode<K, V> leftOf(
            TreeNode<K, V> node) {

        return node == null ? null : node.left;
    }

    private TreeNode<K, V> rightOf(
            TreeNode<K, V> node) {

        return node == null ? null : node.right;
    }

    private boolean colorOf(
            TreeNode<K, V> node) {

        return node != null && node.red;
    }

    private void setRed(
            TreeNode<K, V> node) {

        if (node != null) {
            node.red = true;
        }
    }

    private void setBlack(
            TreeNode<K, V> node) {

        if (node != null) {
            node.red = false;
        }
    }

    // =========================================================
    // SIZE
    // =========================================================

    public int size() {
        return size;
    }

    // =========================================================
    // DISPLAY
    // =========================================================

    public void display() {

        for (int i = 0; i < table.length; i++) {

            Node<K, V> node = table[i];

            if (node == null) {
                continue;
            }

            System.out.print("Bucket " + i + ": ");

            if (node instanceof TreeNode) {

                System.out.print("[RED-BLACK TREE] ");

                displayTree((TreeNode<K, V>) node);

            } else {

                while (node != null) {

                    System.out.print(
                            "[" + node.key
                                    + "="
                                    + node.value
                                    + "] -> "
                    );

                    node = node.next;
                }

                System.out.println("null");
            }
        }
    }

    private void displayTree(
            TreeNode<K, V> root) {

        if (root == null) {
            return;
        }

        displayTree(root.left);

        System.out.print(
                "[" + root.key
                        + "=" + root.value
                        + ", "
                        + (root.red ? "RED" : "BLACK")
                        + "] "
        );

        displayTree(root.right);

        if (root.parent == null) {
            System.out.println();
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        CustomHashMap<Integer, String> map =
                new CustomHashMap<>();

        for (int i = 1; i <= 20; i++) {

            map.put(i, "Value-" + i);
        }

        System.out.println("Size = " + map.size());

        System.out.println("Get 10 = " + map.get(10));

        map.put(10, "Updated-Value");

        System.out.println(
                "Get 10 after update = "
                        + map.get(10)
        );

        System.out.println(
                "Remove 10 = "
                        + map.remove(10)
        );

        System.out.println(
                "Get 10 = "
                        + map.get(10)
        );

        System.out.println(
                "Size = "
                        + map.size()
        );

        map.display();
    }
}
