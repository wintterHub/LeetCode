package leetcode;

//#306
public class AdditiveNumber {

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(new AdditiveNumber().isAdditiveNumber("199100199") + "=" + "true");
		System.out.println(new AdditiveNumber().isAdditiveNumber("199001200") + "=" + "false");
		System.out.println(new AdditiveNumber().isAdditiveNumber("112358") + "=" + "true");
		System.out.println(new AdditiveNumber().isAdditiveNumber("12122436") + "=" + "true");
		System.out.println(new AdditiveNumber().isAdditiveNumber("101") + "=" + "true");
		System.out.println(new AdditiveNumber().isAdditiveNumber("111122335588143") + "=" + "true");
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
	}

	public boolean isAdditiveNumber(String num) {
		String numCopy = num;
		int a = 0, b = 0, c = 0;
		StringBuffer s1 = new StringBuffer(), s2 = new StringBuffer();
		String s3 = "";
		StringBuffer temp1, temp2;
		while (a < num.length()) {
			s1.append(num.charAt(a));
			temp1 = s1;
			for (b = a + 1; b < num.length(); b++) {
				s2.append(num.charAt(b));
				temp2 = s2;
				if (s2.length() != 1 && s2.indexOf("0") == 0) {
					break;
				}

				StringBuffer sbadd = add(s1, s2);
				c = b + 1 + sbadd.length();
				if (c <= num.length()) {
					s3 = num.substring(b + 1, c);
				} else {
					break;
				}
				if (sbadd.toString().equals(s3)) {
					if (c == num.length()) {
						return true;
					} else {
						num = num.substring(a + 1);
						s1 = s2;
						s2 = new StringBuffer().append(s3);
						s3 = "";
						a = s1.length() - 1;
						b = a + s2.length();
						c = b + 1;

						if (find(s1, s2, s3, a, b, c, num)) {
							return true;
						}

						// find执行后值会改变，所以需要恢复成find执行前的值
						num = numCopy;
						s1 = temp1;
						a = s1.length() - 1;
						b = a + 1;
						s2 = temp2;
					}
				}

			}
			s2 = new StringBuffer();
			a++;
		}

		return false;
	}

	public boolean find(StringBuffer s1, StringBuffer s2, String s3, int a, int b, int c, String num) {
		StringBuffer sbadd = add(s1, s2);
		c = b + 1 + sbadd.length();
		if (c <= num.length()) {
			s3 = num.substring(b + 1, c);
		} else {
			return false;
		}
		if (sbadd.toString().equals(s3)) {
			if (c == num.length()) {
				return true;
			} else {
				num = num.substring(a + 1);
				s1 = s2;
				s2 = new StringBuffer().append(s3);
				s3 = "";
				a = s1.length() - 1;
				b = a + s2.length();
				c = b + 1;
				return find(s1, s2, s3, a, b, c, num);
			}
		}
		return false;
	}

	public StringBuffer add(StringBuffer sb1, StringBuffer sb2) {
		int len1 = sb1.length();
		int len2 = sb2.length();
		int zeroCount = 0;
		for (int i = 0; i < Math.abs(len1 - len2); i++) {
			if (len1 < len2) {
				sb1.insert(0, 0);
			} else if (len1 > len2) {
				sb2.insert(0, 0);
			}
			zeroCount++;
		}

		StringBuffer sb = new StringBuffer();
		int carry = 0;
		int index = Math.max(sb1.length(), sb2.length()) - 1;
		while (index >= 0 || carry == 1) {
			if (index < 0 && carry == 1) {
				carry = 0;
				sb.insert(0, 1);
			} else {
				int count = Integer.parseInt(sb1.charAt(index) + "") + Integer.parseInt(sb2.charAt(index) + "") + carry;
				carry = 0;
				if (count > 9) {
					carry = 1;
				}
				sb.insert(0, count % 10);
				index--;
			}
		}

		if (sb1.indexOf("0") == 0) {
			sb1 = sb1.delete(0, zeroCount);
		} else if (sb2.indexOf("0") == 0) {
			sb2 = sb2.delete(0, zeroCount);
		}
		return sb;
	}
}
