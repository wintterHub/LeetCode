package leetcode;

public class WordDictionary {

//	["at"],["and"],["an"],["add"],["a"],[".at"],["bat"],[".at"],["an."],["a.d."],["b."],["a.d"],["."]

	public static void main(String[] args) {
		WordDictionary dictionary = new WordDictionary();
		dictionary.addWord("at");
		dictionary.addWord("and");
		dictionary.addWord("an");
		dictionary.addWord("add");
		System.out.println(dictionary.search("a"));
		System.out.println(dictionary.search(".at"));
		dictionary.addWord("bat");
		System.out.println(dictionary.search(".at"));
		System.out.println(dictionary.search("an."));
		System.out.println(dictionary.search("a.d."));
		System.out.println(dictionary.search("b."));
		System.out.println(dictionary.search("a.d"));
		System.out.println(dictionary.search("."));
	}

	class DicTree {
		boolean isWord;
		DicTree[] childs;

		public DicTree() {
			isWord = false;
			childs = new DicTree[26];
		}
	}

	DicTree root = null;

	public WordDictionary() {
		root = new DicTree();
	}

	public void addWord(String word) {
		DicTree node = root;
		for (char c : word.toCharArray()) {
			int i = c - 'a';
			if (node.childs[i] == null) {
				node.childs[i] = new DicTree();
			}
			node = node.childs[i];
		}
		node.isWord = true;
	}

	public boolean search(String word) {
		return find(root, word, 0);
	}

	public boolean find(DicTree node, String word, int level) {
		char[] charArray = word.toCharArray();
		if (charArray.length <= level) {
			return node.isWord;
		}
		if (word.charAt(level) == '.') {
			for (DicTree n : node.childs) {
				if (n != null && find(n, word, level + 1)) {
					return true;
				}
			}
		} else {
			int i = word.charAt(level) - 'a';
			if (node.childs[i] != null && find(node.childs[i], word, level + 1)) {
				return true;
			}
		}
		return false;
	}

	// 尝试用非递归解决，未成功
//	public boolean search(String word) {
//		DicTree node = root;
//		Queue<DicTree> queue = new LinkedList<>();
//		char[] charArray = word.toCharArray();
//		if (charArray[0] == '.') {
//			for (DicTree n : node.childs) {
//				queue.add(n);
//			}
//		} else {
//			int j = charArray[0] - 'a';
//			queue.add(node.childs[j]);
//		}
//		while (queue.size() > 0) {
//			DicTree poll = queue.poll();
//			if (poll != null && poll.level == charArray.length && poll.isWord == true) {
//				return true;
//			}
//			for (int i = 0; i < 26; i++) {
//				char c = charArray[poll.childs[i].level];
//				if (charArray[c] == '.') {
//					for (DicTree n : node.childs) {
//						queue.add(n);
//					}
//				} else {
//					int j = c - 'a';
//					queue.add(poll.childs[j]);
//				}
//			}
//		}
//		return false;
//	}
}
