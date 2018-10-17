package leetcode;

/**
 * #542
 * 
 * //01 矩阵（中等）
 * 
 * @date 2018年6月24日
 * @description 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。 两个相邻元素间的距离为 1。 示例 1: 输入: 0 0
 *              0 0 1 0 0 0 0 输出: 0 0 0 0 1 0 0 0 0 示例 2: 输入: 0 0 0 0 1 0 1 1 1
 *              输出: 0 0 0 0 1 0 1 2 1 注意: 给定矩阵的元素个数不超过 10000。 给定矩阵中至少有一个元素是 0。
 *              矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 */
public class OneZeroMatrix {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 } };
		int[][] updateMatrix = updateMatrix(matrix);

		for (int[] arr : matrix) {
			for (int i : arr) {
				System.out.print(i + " ");
			}
			System.out.println("");
		}

		System.out.println("");

		for (int[] arr : updateMatrix) {
			for (int i : arr) {
				System.out.print(i + "  ");
			}
			System.out.println("");
		}
	}

	// 0 0 0 0 1 0 0 0 0 0
	// 0 0 0 1 0 1 0 0 0 0
	// 0 0 1 0 0 0 1 0 0 0
	// 0 1 0 0 0 0 0 1 0 0
	// 1 0 0 0 1 0 0 0 1 0
	// 0 1 0 0 0 0 0 1 0 0
	// 0 0 1 0 0 0 1 0 0 0
	// 0 0 0 1 0 1 0 0 0 0
	// 0 0 0 0 1 0 0 0 0 0
	// 0 0 0 0 0 0 0 0 0 0

	/**
	 * 基本思路：遍历二维数组，如果是0直接跳过，如果是1则判断走(1,2,3...n)步的所有点的中是否有0，有则结束循环并保存此时的步数值
	 */

	public static int[][] updateMatrix(int[][] matrix) {
		int yLength = matrix.length;
		int xLength = matrix[0].length;

		int maxStep = xLength + yLength - 2;
		int[][] newArr = new int[yLength][xLength];

		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix[y].length; x++) {
				if (matrix[y][x] == 1) {
					for (int stepCount = 1; stepCount <= maxStep; stepCount++) {
						if (hasZero(x, y, stepCount, matrix)) {
							newArr[y][x] = stepCount;
							break;
						}
					}
				} else {
					newArr[y][x] = 0;
				}
			}
		}
		return newArr;
	}

	public static boolean hasZero(int x, int y, int stepCount, int[][] matrix) {
		int _x = x;
		int _y = y + stepCount;
		int yLength = matrix.length;
		int xLength = matrix[0].length;
		for (int i = 1; i <= stepCount * 4; i++) {
			if (_x <= x && _y > y) {
				if (_x < 0 || _y < 0 || _x > xLength - 1 || _y > yLength - 1) {
					_y--;
					_x--;
					continue;
				}
				if (matrix[_y--][_x--] == 0) {
					return true;
				}
				continue;
			}
			if (_x < x && _y <= y) {
				if (_x < 0 || _y < 0 || _x > xLength - 1 || _y > yLength - 1) {
					_y--;
					_x++;
					continue;
				}
				if (matrix[_y--][_x++] == 0) {
					return true;
				}
				continue;
			}
			if (_x >= x && _y < y) {
				if (_x < 0 || _y < 0 || _x > xLength - 1 || _y > yLength - 1) {
					_y++;
					_x++;
					continue;
				}
				if (matrix[_y++][_x++] == 0) {
					return true;
				}
				continue;
			}
			if (_x > x && _y >= y) {
				if (_x < 0 || _y < 0 || _x > xLength - 1 || _y > yLength - 1) {
					_y++;
					_x--;
					continue;
				}
				if (matrix[_y++][_x--] == 0) {
					return true;
				}
				continue;
			}
		}
		return false;
	}

}
