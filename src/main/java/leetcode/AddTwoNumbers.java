package leetcode;

//#2
public class AddTwoNumbers {

	// Definition for singly-linked list.
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		ListNode l1 = new AddTwoNumbers().new ListNode(5);
//		ListNode l12 = new AddTwoNumbers().new ListNode(4);
//		ListNode l13 = new AddTwoNumbers().new ListNode(3);
//		l1.next = l12;
//		l12.next = l13;

		ListNode l2 = new AddTwoNumbers().new ListNode(5);
//		ListNode l22 = new AddTwoNumbers().new ListNode(6);
//		ListNode l23 = new AddTwoNumbers().new ListNode(4);
//		l2.next = l22;
//		l22.next = l23;

		ListNode ln = new AddTwoNumbers().addTwoNumbers(l1, l2);

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
		int carry = 0;
		ListNode ln = new AddTwoNumbers().new ListNode(0);
		ListNode ln2 = ln;
		while (l1 != null || l2 != null || carry == 1) {
			if (l1 == null) {
				l1 = new AddTwoNumbers().new ListNode(0);
			}
			if (l2 == null) {
				l2 = new AddTwoNumbers().new ListNode(0);
			}
			int count = l1.val + l2.val + carry;
			carry = 0;
			if (count > 9) {
				carry = 1;
			}
			ln.val = count % 10;
			if (l1.next != null || l2.next != null || carry == 1) {
				ln.next = new AddTwoNumbers().new ListNode(0);
				ln = ln.next;
			}
			l1 = l1.next;
			l2 = l2.next;
		}
		return ln2;
	}

}
