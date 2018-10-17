package leetcode;

//#258
public class AddDigits {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		System.out.println(new AddDigits().addDigits(999));
	}

	public int addDigits(int num) {
		return (num - 1) % 9 + 1;
	}

}
