package leetcode;

import java.util.Stack;

//#2
public class AddTwoNumbers_ii {

	// Definition for singly-linked list.
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		ListNode l1 = new AddTwoNumbers_ii().new ListNode(5);
		ListNode l12 = new AddTwoNumbers_ii().new ListNode(4);
		ListNode l13 = new AddTwoNumbers_ii().new ListNode(3);
		l1.next = l12;
		l12.next = l13;

		ListNode l2 = new AddTwoNumbers_ii().new ListNode(5);
		ListNode l22 = new AddTwoNumbers_ii().new ListNode(6);
		ListNode l23 = new AddTwoNumbers_ii().new ListNode(4);
		l2.next = l22;
		l22.next = l23;

		ListNode ln = new AddTwoNumbers_ii().addTwoNumbers(l1, l2);

		while (ln != null) {
			if (ln.next == null) {
				System.out.print(ln.val);
			} else {
				System.out.print(ln.val + " -> ");
			}
			ln = ln.next;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Stack<Integer> s1 = new Stack<>();
		Stack<Integer> s2 = new Stack<>();
		while (l1 != null) {
			s1.push(l1.val);
			l1 = l1.next;
		}
		while (l2 != null) {
			s2.push(l2.val);
			l2 = l2.next;
		}

		int carry = 0;
		ListNode ln = new AddTwoNumbers_ii().new ListNode(0);
		ListNode ln2 = ln;
		Stack<Integer> s3 = new Stack<>();
		while (!s1.isEmpty() || !s2.isEmpty() || carry == 1) {
			if (s1.isEmpty()) {
				s1.push(0);
			}
			if (s2.isEmpty()) {
				s2.push(0);
			}
			int count = s1.pop() + s2.pop() + carry;
			carry = 0;
			if (count > 9) {
				carry = 1;
			}
			s3.push(count % 10);
		}

		while (!s3.isEmpty()) {
			ln.val = s3.pop();
			if (!s3.isEmpty()) {
				ln.next = new AddTwoNumbers_ii().new ListNode(0);
				ln = ln.next;
			}
		}
		return ln2;
	}

}
