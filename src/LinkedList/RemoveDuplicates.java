package LinkedList;
//Remove duplicates from an unsorted linked list
public class RemoveDuplicates {
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
        // Create a sample unsorted linked list: 1 -> 3 -> 2 -> 3 -> 4 -> 2
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(2);

        // Remove duplicates from the linked list
        head = removeDuplicates(head);

        // Print the linked list after removing duplicates
        System.out.print("Linked list after removing duplicates: ");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    // Method to remove duplicates from an unsorted linked list
    public static ListNode removeDuplicates(ListNode head) {
        if (head == null) return null;

        java.util.HashSet<Integer> seen = new java.util.HashSet<>();
        ListNode current = head;
        seen.add(current.val);

        while (current != null && current.next != null) {
            if (seen.contains(current.next.val)) {
                // Skip the duplicate node
                current.next = current.next.next;
            } else {
                // Add the value to the set and move to the next node
                seen.add(current.next.val);
                current = current.next;
            }
        }

        return head; // Return the modified linked list without duplicates
    }
}
