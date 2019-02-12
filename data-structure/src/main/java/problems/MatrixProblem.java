package problems;

public class MatrixProblem {

	static void printMatrixInSpiral(int[][] mat) {
		if (mat == null || mat.length == 0)
			return;
		printMatrixInSpiral(mat, mat.length, mat[0].length);
	}

	static void printMatrixInSpiral(int[][] mat, int n, int m) {
		int l = 0; // starting row index
		int k = 0; // starting column index

		while (l < n && k < m) {
			for (int i = k; i < m; i++) {
				System.out.print(mat[l][i] + " ");
			}
			l++;

			for (int i = l; i < n; i++) {
				System.out.print(mat[i][m - 1] + " ");
			}

			m--;
			for (int i = m - 1; i >= k && l < n; i--) {
				System.out.print(mat[n - 1][i] + " ");
			}

			n--;

			for (int i = n - 1; i >= l && k < m; i--) {
				System.out.print(mat[i][k] + " ");
			}
			k++;

		}
	}

	
	static void rotateMatrixAntiCW(int[][] mat, int n, int m) {
		for(int i=0; i<n/2 ; i++) {
			for(int j=i; j<m-i-1; j++) {
				int t= mat[i][j];
				mat[i][j]= mat[j][n-1-i];
				mat[j][n-1-i]= mat[n-1-i][m-1-j];
				mat[n-1-i][m-1-j]= mat[m-1-j][i];
				mat[m-1-j][i]=t;
			}
		}
	}
	
	static void printMAtrix(int[][] mat, int n, int m) {
		for(int i=0; i<n ; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotateMatrixAntiCW(matrix, 3,3);
		printMAtrix(matrix, 3,3);
		
//		printMatrixInSpiral(matrix);
//
//		System.out.println();
	int a[][] = { { 1, 2, 3, 4 }, { 7, 8, 9, 10}, { 13, 14, 15, 16 }, {19,20,21,22} };
//		printMatrixInSpiral(a);
	
	rotateMatrixAntiCW(a, 4,4);
	printMAtrix(a, 4,4);
	}

}
