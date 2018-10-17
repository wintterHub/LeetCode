package leetcode;

//#650
public class TwoKeysKeyboard {

	public static void main(String[] args) {
		int minSteps = minSteps(18);
		System.out.println(minSteps);
	}

	public static int minSteps(int n) {

		int i = 2;
		int count = 0;
		while (i <= n) {
			if (n % i == 0) {
				count += i;
				n = n / i;
				i = 2;
			} else {
				i++;
			}
		}
		return count;

		// 1 A 0
		// 2 AA 2
		// 3 AAA 3
		// 4 AAAA 4
		// 5 AAAAA 5
		// 6 AAAAAA 5
		// 7 AAAAAAA 7
		// 8 AAAAAAAA 6
		// 9 AAAAAAAAA 6
		// 10 AAAAAAAAAA 7
		// 11 AAAAAAAAAAA 11
		// 12 AAAAAAAAAAAA 7
		// 13 AAAAAAAAAAAAA 13
		// 14 AAAAAAAAAAAAAA 9
		// 15 AAAAAAAAAAAAAAA 8
		// 16 AAAAAAAAAAAAAAAA 8
		// 17 AAAAAAAAAAAAAAAAA 17
		// 18 AAAAAAAAAAAAAAAAAA 8

		// 999 = 37x3x3x3,37+3+3+3 = 46
		// 18 = 2x3x3,2+3+3 = 8

		// 分解因数后对因数相加

	}

}
