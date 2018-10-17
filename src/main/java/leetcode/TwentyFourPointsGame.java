package leetcode;

//#679
//24 点游戏
public class TwentyFourPointsGame {

	// #表示+-*/运算的组合，思路：由后往前推导所有算数方式，排列组合出所有牌序，两者再进行组合穷举出所有计算结果

	// a#b

	// a1#a2#b
	// a#b1#b2

	// a11#a12#a2#b
	// a1#a21#a22#b
	// a1#a2#b1#b2

	public static void main(String[] args) {
		boolean judgePoint24 = judgePoint24(new int[] { 1, 3, 4, 6 });
		System.out.println(judgePoint24);
	}

	public static boolean judgePoint24(int[] nums) {
//		return valid(nums);
		return judgePoint24(nums, 0);
	}

	public static boolean judgePoint24(int[] nums, int step) {
		if (nums.length == step + 1) {
			return valid(nums);
		} else {
			for (int i = step; i < nums.length; i++) {
				int temp = nums[i];
				nums[i] = nums[step];
				nums[step] = temp;
				if (judgePoint24(nums, step + 1)) {
					return true;
				}
				temp = nums[i];
				nums[i] = nums[step];
				nums[step] = temp;
			}
		}
		return false;
	}

	public static boolean valid(int[] nums) {
		double a = nums[0];
		double b = nums[1];
		double c = nums[2];
		double d = nums[3];
		if (valid(a + b, c, d) || valid(a, b + c, d) || valid(a, b, c + d)) {
			return true;
		}
		if (valid(a - b, c, d) || valid(a, b - c, d) || valid(a, b, c - d)) {
			return true;
		}
		if (valid(a * b, c, d) || valid(a, b * c, d) || valid(a, b, c * d)) {
			return true;
		}
		if (valid(a / b, c, d) || valid(a, b / c, d) || valid(a, b, c / d)) {
			return true;
		}
		return false;
	}

	public static boolean valid(double a, double b, double c) {
		if (valid(a + b, c) || valid(a - b, c) || valid(a * b, c) || b != 0 && valid(a / b, c)) {
			return true;
		}
		if (valid(a, b + c) || valid(a, b - c) || valid(a, b * c) || c != 0 && valid(a, b / c)) {
			return true;
		}
		return false;

	}

	public static boolean valid(double a, double b) {
		if (Math.abs(a + b - 24.0) < 0.0001 || Math.abs(a - b - 24.0) < 0.0001 || Math.abs(a * b - 24.0) < 0.0001
				|| b != 0 && Math.abs(a / b - 24.0) < 0.0001) {
			return true;
		}
		return false;

	}

}
