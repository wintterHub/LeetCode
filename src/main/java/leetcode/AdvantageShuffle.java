package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AdvantageShuffle {

	public static void main(String[] args) {
		test(new int[] { 2, 7, 11, 15 }, new int[] { 1, 10, 4, 11 }, new int[] { 2, 11, 7, 15 });
		test(new int[] { 12, 24, 8, 32 }, new int[] { 13, 25, 32, 11 }, new int[] { 24, 32, 8, 12 });
		test(new int[] { 2, 0, 4, 1, 2 }, new int[] { 1, 3, 0, 0, 2 }, new int[] { 2, 4, 2, 1, 0 });
	}

	private static void test(int[] A, int[] B, int[] testArr) {
		int[] intArr1 = new AdvantageShuffle().advantageCount(A, B);
		System.err.println(Arrays.equals(intArr1, testArr));
		System.out.print("[");
		for (int i : intArr1) {
			System.out.print(i + ",");
		}
		System.out.print("]");
	}

	public int[] advantageCount(int[] A, int[] B) {
		Arrays.sort(A);
		Queue<Integer> queue = new LinkedList<>();
		int[] result = new int[A.length];
		for (int i = A.length - 1; i >= 0; i--) {
			int index = findMaxIndex(A[i], B);
			if (index >= 0) {
				result[index] = A[i];
			} else {
				queue.add(A[i]);
			}
		}
		for (int i = 0; i < result.length; i++) {
			if (result[i] == 0) {
				result[i] = queue.poll();
			}
		}
		return result;
	}

	public int findMaxIndex(int a, int[] B) {
		int maxIndex = -1;
		int max = -1;
		for (int i = 0; i < B.length; i++) {
			if (a > B[i] && B[i] > max && B[i] != -1) {
				maxIndex = i;
				max = B[i];
			}
		}
		if (maxIndex != -1) {
			B[maxIndex] = -1;
		}
		return maxIndex;
	}

}
