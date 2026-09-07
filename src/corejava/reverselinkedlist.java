package corejava;

//Reverse a singly linked list iteratively and recursively
public class reverselinkedlist {
    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original Linked List:");
        printList(head);

        // Reverse the linked list iteratively
        ListNode reversedIterative = reverseIterative(head);
        System.out.println("Reversed Linked List (Iterative):");
        printList(reversedIterative);

        // Reverse the linked list recursively
        ListNode reversedRecursive = reverseRecursive(reversedIterative);
        System.out.println("Reversed Linked List (Recursive):");
        printList(reversedRecursive);
    }

    // Method to reverse a linked list iteratively
    public static ListNode reverseIterative(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextTemp = current.next; // Store the next node
            current.next = prev;               // Reverse the link
            prev = current;                    // Move prev to current
            current = nextTemp;                // Move to the next node
        }
        return prev; // New head of the reversed list
    }

    // Method to reverse a linked list recursively
    public static ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head; // Base case: empty list or single node
        }
        
        ListNode newHead = reverseRecursive(head.next); // Reverse the rest of the list
        head.next.next = head; // Make the next node point to the current node
        head.next = null;      // Set the current node's next to null
        
        return newHead; // Return the new head of the reversed list
    }

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}
