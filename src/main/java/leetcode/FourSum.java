package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//#18
public class FourSum {

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		FourSum sum = new FourSum();
		List<List<Integer>> threeSums = sum.fourSum(new int[] { -3, -2, -1, 0, 0, 1, 2, 3 }, 0);
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

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> resultList = new ArrayList<>();
		Arrays.sort(nums);
		int a, b, c, d;
		boolean flag = false;
		for (a = 0; a < nums.length - 3; a++) {
			if (a > 0 && nums[a] == nums[a - 1]) {
				continue;
			}
			flag = false;
			for (b = a + 1; b < nums.length - 2; b++) {
				c = b + 1;
				d = nums.length - 1;
				if (b > 1 && nums[b] == nums[b - 1] && flag) {
					continue;
				}
				flag = true;
				while (c < d) {
					int sum = nums[a] + nums[b] + nums[c] + nums[d];
					if (sum < target) {
						c++;
						while (c < d && nums[c] == nums[c - 1]) {
							c++;
						}
					} else if (sum > target) {
						d--;
						while (c < d && nums[d] == nums[d + 1]) {
							d--;
						}
					} else {
						List<Integer> intList = new ArrayList<Integer>();
						intList.add(nums[a]);
						intList.add(nums[b]);
						intList.add(nums[c]);
						intList.add(nums[d]);
						resultList.add(intList);
						c++;
						d--;
						while (c < d && nums[c] == nums[c - 1]) {
							c++;
						}
						while (c < d && nums[d] == nums[d + 1]) {
							d--;
						}
					}
				}
			}
		}
		return resultList;
	}

}
