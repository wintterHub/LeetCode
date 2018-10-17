package leetcode;

import java.util.TreeMap;

//#456
public class OneThreeTwoPattern {

	public static void main(String[] args) {
		int[] nums = { 1, 3, 2 };
		boolean find132pattern = find132pattern(nums);
		System.out.println(find132pattern);
	}

	public static boolean find132pattern(int[] nums) {
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
		for (int num : nums) {
			tm.put(num, tm.getOrDefault(num, 0) + 1);
		}
		int min = Integer.MAX_VALUE;
		for (int num : nums) {
			int count = tm.get(num);
			if (count > 1) {
				tm.put(num, count - 1);
			} else {
				tm.remove(num);
			}
			if (num <= min) {
				min = num;
			} else {
				Integer target = tm.higherKey(min);
				if (target != null && target < num) {
					return true;
				}
			}
		}
		return false;
	}

	/* 复杂度过高 */
	@Deprecated
	public static boolean find132pattern1(int[] nums) {
		int _i = 0;
		int _j = 1;
		int _k = 2;
		int len = nums.length;

		while (_i < len - 2) {
			_j = _i + 1;
			_k = _j + 1;
			while (_j < len - 1) {
				if (nums[_i] < nums[_j]) {
					_k = _j + 1;
					while (_k < len) {
						if (nums[_k] > nums[_i] && nums[_k] < nums[_j]) {
							return true;
						}
						_k++;
					}
				}
				_j++;
			}
			_i++;
		}
		return false;
	}

}
