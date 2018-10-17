package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//#721
public class AccountsMerge {

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		AccountsMerge am = new AccountsMerge();

		String[][] strArrArr = { { "John", "johnsmith@mail.com", "john00@mail.com" },
				{ "John", "johnnybravo@mail.com" }, { "John", "johnsmith@mail.com", "john_newyork@mail.com" },
				{ "Mary", "mary@mail.com" } };

//		String[][] strArrArr = { { "John", "a@gmail.com", "b@gmail.com" }, { "John", "c@gmail.com", "d@gmail.com" },
//				{ "John", "a@gmail.com", "c@gmail.com" } };

		List<List<String>> listlist = new ArrayList<>();
		for (String[] strArr : strArrArr) {
			List<String> list = new ArrayList<>();
			for (String str : strArr) {
				list.add(str);
			}
			listlist.add(list);
		}

		List<List<String>> accountsMerges = am.accountsMerge(listlist);
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
		System.out.print("[");
		for (List<String> accountsMerge : accountsMerges) {
			System.out.print("[");
			for (String a : accountsMerge) {
				System.out.print("\"" + a + "\"" + ",");
			}
			System.out.println("],");
		}
		System.out.println("]");
	}

	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Map<String, List<Integer>> mailUserMap = new HashMap<>();// 邮箱与账户映射【johnsmith@mail.com->{0,2}】
		for (int a = 0; a < accounts.size(); a++) {
			for (int b = 0; b < accounts.get(a).size(); b++) {
				String field = accounts.get(a).get(b);
				if (b != 0) {
					if (mailUserMap.get(field) != null) {
						mailUserMap.get(field).add(a);
					} else {
						List<Integer> list = new ArrayList<>();
						list.add(a);
						mailUserMap.put(field, list);
					}
				}
			}
		}

		Map<Integer, Boolean> hasTraversingMap = new HashMap<>();// 记录账户是否已遍历
		List<List<String>> returnList = new ArrayList<>();
		for (int a = 0; a < accounts.size(); a++) {
			if (hasTraversingMap.getOrDefault(a, false)) {
				continue;
			} else {
				hasTraversingMap.put(a, true);
			}
			Queue<String> queue = new LinkedList<String>();
			for (int b = 0; b < accounts.get(a).size(); b++) {
				if (b != 0) {
					queue.offer(accounts.get(a).get(b));// 取出当前账户的邮箱添加到队列
				}
			}
			List<String> margeAccountList = new ArrayList<>();
			while (queue.size() > 0) {// 遍历队列，依据mailUserMap还会在队列中添加邮箱，这些邮箱都是当前用户的
				String queueTop = queue.poll();
				if (!margeAccountList.contains(queueTop)) {
					margeAccountList.add(queueTop);
				}
				List<Integer> mapList = mailUserMap.get(queueTop);
				for (Integer accountIndex : mapList) {// 不断的通过mailUserMap取出有关联的邮箱添加到队列
					if (!hasTraversingMap.getOrDefault(accountIndex, false)) {
						hasTraversingMap.put(accountIndex, true);
						for (int c = 0; c < accounts.get(accountIndex).size(); c++) {
							if (c != 0) {
								queue.offer(accounts.get(accountIndex).get(c));
							}
						}
					}
				}
			}
			margeAccountList.sort((val1, val2) -> val1.compareTo(val2));
			margeAccountList.add(0, accounts.get(a).get(0));
			returnList.add(margeAccountList);// 当前用户名下所有的邮箱都已合并
		}
		return returnList;
	}
}
