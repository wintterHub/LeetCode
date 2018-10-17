package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//#15
public class ThreeSum {

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		ThreeSum sum = new ThreeSum();
		List<List<Integer>> threeSums = sum.threeSum(new int[] { -2, 0, 1, 1, 2 });
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
		System.out.print("[");
		for (List<Integer> threeSum : threeSums) {
			System.out.print("[");
			for (Integer i : threeSum) {
				System.out.print(i + ",");
			}
			System.out.println("],");
		}
		System.out.println("]");
	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> resultList = new ArrayList<>();
		Arrays.sort(nums);
		int target = 0;
		int a, b, c;
		for (a = 0; a < nums.length - 2; a++) {
			if (a > 0 && nums[a] == nums[a - 1]) {
				continue;
			}
			b = a + 1;
			c = nums.length - 1;
			while (b < c) {
				int sum = nums[a] + nums[b] + nums[c];
				if (sum < target) {
					b++;
					while (b < c && nums[b] == nums[b - 1]) {
						b++;
					}
				} else if (sum > target) {
					c--;
					while (b < c && nums[c] == nums[c + 1]) {
						c--;
					}
				} else {
					List<Integer> intList = new ArrayList<Integer>();
					intList.add(nums[a]);
					intList.add(nums[b]);
					intList.add(nums[c]);
					resultList.add(intList);
					b++;
					c--;
					while (b < c && nums[b] == nums[b - 1]) {
						b++;
					}
					while (b < c && nums[c] == nums[c + 1]) {
						c--;
					}
				}
			}
		}
		return resultList;
	}

	/* 复杂度过高，重复计算过多 */
	public List<List<Integer>> threeSum(List<List<Integer>> list, int[] nums, int step) {
		if (step == 3) {
			if (nums[0] + nums[1] + nums[2] == 0) {
				List<Integer> intList = new ArrayList<Integer>();
				intList.add(nums[0]);
				intList.add(nums[1]);
				intList.add(nums[2]);
				intList.sort((val1, val2) -> val1.compareTo(val2));
				if (!list.contains(intList)) {
					list.add(intList);
				}
			}
		} else {
			for (int i = step; i < nums.length; i++) {
				int temp = nums[i];
				nums[i] = nums[step];
				nums[step] = temp;
				threeSum(list, nums, step + 1);
				temp = nums[i];
				nums[i] = nums[step];
				nums[step] = temp;
			}
		}
		return list;
	}
}
