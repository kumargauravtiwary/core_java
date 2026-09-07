package LinkedList;
//Find the Nth node from the end of a linked list in a single pass.
public class NthNode {
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

        int n = 2; // Find the 2nd node from the end
        ListNode nthNode = findNthFromEnd(head, n);
        if (nthNode != null) {
            System.out.println("The " + n + "th node from the end is: " + nthNode.val);
        } else {
            System.out.println("The linked list has fewer than " + n + " nodes.");
        }
    }

    // Method to find the Nth node from the end of a linked list in a single pass
    public static ListNode findNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) return null;

        ListNode firstPointer = head;
        ListNode secondPointer = head;

        // Move the first pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (firstPointer == null) return null; // If n is greater than the length of the list
            firstPointer = firstPointer.next;
        }

        // Move both pointers until the first pointer reaches the end
        while (firstPointer != null) {
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next;
        }

        return secondPointer; // This will be the Nth node from the end
}
}
