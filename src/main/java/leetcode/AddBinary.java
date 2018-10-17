package leetcode;

//#67
public class AddBinary {

	public static void main(String[] args) {
//		System.out.println('1' & '1');
//		System.out.println(1 << 1);
//		System.out.println('1' ^ '1');
//		System.out.println(new AddBinary().addBinary(
//				"10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101",
//				"110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"));
		System.out.println(new AddBinary().addBinary("0", "0"));
	}

	public String addBinary(String a, String b) {
		if (!b.contains("1")) {
			if (a.contains("1")) {
				return a.substring(a.indexOf("1"));
			} else {
				return a;
			}
		}
		StringBuffer sb = null;
		if (a.length() < b.length()) {
			sb = new StringBuffer();
			for (int i = 0; i < b.length() - a.length(); i++) {
				sb.append("0");
			}
			sb.append(a);
			a = sb.toString();
		} else if (a.length() > b.length()) {
			sb = new StringBuffer();
			for (int i = 0; i < a.length() - b.length(); i++) {
				sb.append("0");
			}
			sb.append(b);
			b = sb.toString();
		}

		String sum = "", carry = "";
		StringBuffer sumsb = new StringBuffer();
		StringBuffer carrysb = new StringBuffer();
		for (int i = 0; i < a.length(); i++) {
			sumsb.append(a.charAt(i) ^ b.charAt(i));
			carrysb.append(Integer.parseInt(a.charAt(i) + "") & Integer.parseInt(b.charAt(i) + ""));
		}

		sum = sumsb.toString();
		carrysb.append("0");
		carry = carrysb.toString();
		return addBinary(sum, carry);
	}
}
