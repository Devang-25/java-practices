package test;

//JAVA program for implementation of KMP pattern
//searching algorithm

class KMP_String_Matching {
	int KMPSearch(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();
		int lps[] = new int[M];
		computeLPSArray(pat, M, lps);
		System.out.println("lps array is ");
		for (int i = 0; i < M; i++) {
			System.out.print(lps[i] + " ");
		}
		int i=0;
		int j=0;
		while(i<N) {
			if(txt.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
				if(j==M) {
					System.out.println("\nMatched at index: " + (i-j));
					return i-j;
				}
			}else {
				if(j!=0) {
					j= lps[j-1];
				}else {
					i=i+1;
				}
			}
		}
		return -1;
	}

	void computeLPSArray(String pat, int M, int lps[]) {
		int i = 1;
		int len = 0;
		lps[0] = 0;
		while (i < M) {
			if (pat.charAt(len) == pat.charAt(i)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = len;
					i++;
				}
			}
		}
	}

	// Driver program to test above function
	public static void main(String args[]) {
		String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
		new KMP_String_Matching().KMPSearch(pat, txt);
	}
}

/*
 * 
 * lps array is 0 0 1 2 0 1 2 3 4 lps[j-1] : 2, j: 4
 * 
 * lps[j-1] : 2, j: 4 Found pattern at index 4
 * 
 * after match: lps[j-1] : 4, j: 9
 */
