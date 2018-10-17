package leetcode;

import java.util.LinkedList;
import java.util.Queue;

//#623
public class AddOneRowToTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new AddOneRowToTree().new TreeNode(4);
		TreeNode tn21 = new AddOneRowToTree().new TreeNode(2);
		TreeNode tn22 = new AddOneRowToTree().new TreeNode(6);
		TreeNode tn31 = new AddOneRowToTree().new TreeNode(3);
		TreeNode tn32 = new AddOneRowToTree().new TreeNode(1);
		TreeNode tn33 = new AddOneRowToTree().new TreeNode(5);
		root.left = tn21;
		root.right = tn22;
		tn21.left = tn31;
		tn21.right = tn32;
		tn22.left = tn33;

//		TreeNode root = new AddOneRowToTree().new TreeNode(4);
//		TreeNode tn21 = new AddOneRowToTree().new TreeNode(2);
//		TreeNode tn31 = new AddOneRowToTree().new TreeNode(3);
//		TreeNode tn32 = new AddOneRowToTree().new TreeNode(1);
//		root.left = tn21;
//		tn21.left = tn31;
//		tn21.right = tn32;

		TreeNode addOneRow = new AddOneRowToTree().addOneRow(root, 1, 1);

		printTree(addOneRow);

	}

	public static void printTree(TreeNode node) {
		if (node == null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(node);

		while (!queue.isEmpty()) {
			TreeNode queueTop = queue.poll();
			System.out.println(queueTop.val);
			if (queueTop.left != null) {
				queue.offer(queueTop.left);
			}
			if (queueTop.right != null) {
				queue.offer(queueTop.right);
			}
		}
	}

	public TreeNode addOneRow(TreeNode root, int v, int d) {
		return traversingTree(root, 1, v, d);
	}

	public static TreeNode traversingTree(TreeNode root, int level, int v, int d) {
		if (root == null) {
			return root;
		} else {
			if (d == 1) {
				TreeNode tn = new AddOneRowToTree().new TreeNode(v);
				tn.left = root;
				return tn;
			} else if (level == d - 1) {
				TreeNode tnl = new AddOneRowToTree().new TreeNode(v);
				tnl.left = root.left;
				root.left = tnl;
				TreeNode tnr = new AddOneRowToTree().new TreeNode(v);
				tnr.right = root.right;
				root.right = tnr;
				return root;
			} else {
				traversingTree(root.left, level + 1, v, d);
				traversingTree(root.right, level + 1, v, d);
				return root;
			}
		}
	}

}
