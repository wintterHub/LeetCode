package leetcode;

import java.util.HashMap;
import java.util.Map;

//#415
public class AddStrings {

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(new AddStrings().addStrings("408", "5"));
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
	}

	public String addStrings(String num1, String num2) {
		char[] charNumber = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int[] number = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Map<Character, Integer> map = new HashMap<>();
		for (int i : number) {
			map.put(charNumber[i], i);
		}

		int len = num1.length() > num2.length() ? num1.length() - num2.length() : num2.length() - num1.length();
		if (num1.length() > num2.length()) {
			for (int i = 0; i < len; i++) {
				num2 = "0" + num2;
			}
		} else {
			for (int i = 0; i < len; i++) {
				num1 = "0" + num1;
			}
		}

		int carry = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = num1.length() - 1; i >= 0; i--) {
			int count1 = map.get(num1.charAt(i));
			int count2 = map.get(num2.charAt(i));
			int count = count1 + count2 + carry;
			carry = 0;
			if (count > 9) {
				carry = 1;
			}
			sb.insert(0, count % 10 + "");
			if (i == 0 && carry == 1) {
				sb.insert(0, "1");
			}
		}
		return sb.toString();
	}

}
