package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//#863
public class AllNodesDistanceKInBinaryTree {

	public static void main(String[] args) {
		TreeNode root = new AllNodesDistanceKInBinaryTree().new TreeNode(3);
		TreeNode tn21 = new AllNodesDistanceKInBinaryTree().new TreeNode(5);
		TreeNode tn22 = new AllNodesDistanceKInBinaryTree().new TreeNode(1);
		TreeNode tn31 = new AllNodesDistanceKInBinaryTree().new TreeNode(6);
		TreeNode tn32 = new AllNodesDistanceKInBinaryTree().new TreeNode(2);
		TreeNode tn33 = new AllNodesDistanceKInBinaryTree().new TreeNode(0);
		TreeNode tn34 = new AllNodesDistanceKInBinaryTree().new TreeNode(8);
		TreeNode tn41 = new AllNodesDistanceKInBinaryTree().new TreeNode(7);
		TreeNode tn42 = new AllNodesDistanceKInBinaryTree().new TreeNode(4);
		root.left = tn21;
		root.right = tn22;
		tn21.left = tn31;
		tn21.right = tn32;
		tn22.left = tn33;
		tn22.right = tn34;
		tn32.left = tn41;
		tn32.right = tn42;

		List<Integer> distanceK = new AllNodesDistanceKInBinaryTree().distanceK(root, tn21, 2);
		System.out.println(distanceK.toString());
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		Map<TreeNode, TreeNode> findParentMap = new HashMap<>();
		getParent(root, null, findParentMap);
		List<Integer> resultList = new ArrayList<Integer>();
		diffusion(findParentMap, target, K, resultList, new ArrayList<Integer>());
		return resultList;
	}

	// 从target节点开始扩散查找距离为K的节点
	public void diffusion(Map<TreeNode, TreeNode> findParentMap, TreeNode target, int k, List<Integer> resultList,
			List<Integer> traversedList) {
		if (traversedList.contains(target.val)) {
			return;
		}
		if (k == 0) {
			resultList.add(target.val);
			return;
		}
		k--;
		traversedList.add(target.val);// 记录已经走过的节点，防止重复访问
		if (findParentMap.get(target) != null) {
			diffusion(findParentMap, findParentMap.get(target), k, resultList, traversedList);
		}
		if (target.left != null) {
			diffusion(findParentMap, target.left, k, resultList, traversedList);
		}
		if (target.right != null) {
			diffusion(findParentMap, target.right, k, resultList, traversedList);
		}
	}

	// 获得节点的父节点映射
	public void getParent(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> findParentMap) {
		if (parent != null) {
			findParentMap.put(node, parent);
		}
		if (node.left != null) {
			getParent(node.left, node, findParentMap);
		}
		if (node.right != null) {
			getParent(node.right, node, findParentMap);
		}
	}

}
