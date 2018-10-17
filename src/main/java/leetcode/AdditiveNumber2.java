package leetcode;

//#306
public class AdditiveNumber2 {

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(new AdditiveNumber2().isAdditiveNumber("199100199") + "=" + "true");
		System.out.println(new AdditiveNumber2().isAdditiveNumber("199001200") + "=" + "false");
		System.out.println(new AdditiveNumber2().isAdditiveNumber("112358") + "=" + "true");
		System.out.println(new AdditiveNumber2().isAdditiveNumber("12122436") + "=" + "true");
		System.out.println(new AdditiveNumber2().isAdditiveNumber("101") + "=" + "true");
		System.out.println(new AdditiveNumber2().isAdditiveNumber("111122335588143") + "=" + "true");
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
	}

	public boolean isAdditiveNumber(String num) {
		int len = num.length();
		// 用i来记录第二个数的起点为位置，用j来记录第三个数的地点位置。
		for (int i = 1; i <= (len - 1) / 2; i++) { // 第三个数的位数一定不小于第一个数和第二个数的位数
			if (num.charAt(0) == '0' && i >= 2)
				break;
			for (int j = i + 1; len - j >= j - i && len - j >= i; j++) {
				if (num.charAt(i) == '0' && j - i >= 2)
					break;
				long a = Long.parseLong(num.substring(0, i));
				long b = Long.parseLong(num.substring(i, j));
				String rem = num.substring(j); // 剩余的那个字符串；
				if (isAdaptive(a, b, rem))
					return true;
			}
		}
		return false;
	}

	public boolean isAdaptive(long a, long b, String rem) {
		if (rem.equals(""))
			return true;
		long c = a + b;
		String temp = c + "";
		if (!rem.startsWith(temp))
			return false;
		return isAdaptive(b, c, rem.substring(temp.length()));
	}
}
