/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode prev = null, next = null, tail = head, temp = head;
        int n = 0;
        while(tail.next != null) {
            tail = tail.next;
            n++;
        }
        n++;
        k = k % n;
        if(k==0){
            return head;
        }
        int a = n - k;
        tail.next = head;
        while(a-->1){
            prev = temp;
            temp = temp.next;
        }
        head = temp.next;
        temp.next = null;
        return head;
    }
}