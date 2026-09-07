package LinkedList;
//Find the intersection point of two linked lists.  
public class IntersectionofLinkedlist {
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
        // Create two linked lists that intersect: 1 -> 2 -> 3
        //                                      \
        //                                       6 -> 7
        //                                      /
        //                             4 -> 5
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        ListNode intersection = new ListNode(6);
        intersection.next = new ListNode(7);
        headA.next.next.next = intersection;

        ListNode headB = new ListNode(4);
        headB.next = new ListNode(5);
        headB.next.next = intersection;

        ListNode intersectionPoint = getIntersectionNode(headA, headB);
        if (intersectionPoint != null) {
            System.out.println("Intersection point value: " + intersectionPoint.val);
        } else {
            System.out.println("No intersection point found.");
        }
    }

    // Method to find the intersection point of two linked lists
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pointerA = headA;
        ListNode pointerB = headB;
        // Traverse both lists. When one pointer reaches the end, redirect it to the head of the other list.
        // If the lists intersect, the pointers will meet at the intersection point after at most two passes. If they do not intersect, both pointers will eventually become null at the same time. 
        while (pointerA != pointerB) {
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }

        return pointerA; // This will be the intersection point or null if no intersection
    }
}
