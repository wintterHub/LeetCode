package leetcode;

import java.util.Arrays;

//#16
public class ThreeSumClosest {
	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(new ThreeSumClosest().threeSumClosest(new int[] { -1, 2, 1, -4 }, 1));
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
	}

	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int sum = nums[0] + nums[1] + nums[2];
		int closestNum = sum;
		int a, b, c;
		for (a = 0; a < nums.length - 2; a++) {
			if (a > 0 && nums[a] == nums[a - 1]) {
				continue;
			}
			b = a + 1;
			c = nums.length - 1;
			while (b < c) {
				sum = nums[a] + nums[b] + nums[c];
				if (Math.abs(sum - target) < Math.abs(closestNum - target)) {
					closestNum = sum;
				}
				if (sum < target) {
					b++;
				} else if (sum > target) {
					c--;
				} else {
					b++;
					c--;
				}
			}
		}
		return closestNum;
	}
}
