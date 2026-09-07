package LinkedList;
//Detect and remove a cycle in a linked list.
public class detectloopinlinkedlist {
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
        // Create a sample linked list with a cycle: 1 -> 2 -> 3 -> 4 -> 5 -> 3 (cycle)
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = head.next.next; // Creating a cycle

        if (hasCycle(head)) {
            System.out.println("Cycle detected in the linked list.");
            removeCycle(head);
            System.out.println("Cycle removed from the linked list.");
        } else {
            System.out.println("No cycle detected in the linked list.");
        }
    }

    // Method to detect if a linked list has a cycle using Floyd's Cycle Detection Algorithm
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move slow pointer by 1
            fast = fast.next.next;     // Move fast pointer by 2

            if (slow == fast) {       // Cycle detected
                return true;
            }
        }
        return false;                // No cycle
    }

    // Method to remove the cycle from the linked list
    public static void removeCycle(ListNode head) {
        if (head == null) return;

        ListNode slow = head;
        ListNode fast = head;

        // First, detect the cycle
        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        // Find the start of the cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Find the node just before the start of the cycle and set its next to null
        while (fast.next != slow) {
            fast = fast.next;
        }
        fast.next = null;
    }
}
