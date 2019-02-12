package stringpattern;

//JAVA program for implementation of KMP pattern 
//searching algorithm 

class KMP {

	void KMPSearch1(String pat, String txt) {
		int M= pat.length();
		int N= txt.length();
		int [] lps= new int [M];
		computeLPSArray(pat, M, lps);
		int i=0, j=0;
		while(i<N) {
			if(txt.charAt(i)== txt.charAt(j)) {
				i++;
				j++;
			}
			if(j==M) {
				System.out.println("found at i "+ i);
				j= lps[j-1];
			}else if(i<N && txt.charAt(i)!= pat.charAt(j)) {
				if(j!=0) {
					j= lps[j-1];
				}else {
					i++;
				}}}
		for(int k=0; k<M; k++) {
			System.out.println(lps[k]);
		}
	}
	void computeLPSArray1(String pat, int M, int lps[]) {
		int i=1;
		int j=0;
		lps[0]=0;
		while(i<M) {
			if(pat.charAt(j)== pat.charAt(i)) {
				lps[i]=++j;
				i++;
			}else {
				if(j!=0) {
					j=lps[j-1];
				} else {
					i++;
				}
			}
		}
	}

	void KMPSearch(String pat, String txt) 
    { 
        int M = pat.length(); 
        int N = txt.length(); 
  
        // create lps[] that will hold the longest 
        // prefix suffix values for pattern 
        int lps[] = new int[M]; 
        int j = 0; // index for pat[] 
  
        // Preprocess the pattern (calculate lps[] 
        // array) 
        computeLPSArray(pat, M, lps); 
  
        int i = 0; // index for txt[] 
        while (i < N) { 
            if (pat.charAt(j) == txt.charAt(i)) { 
                j++; 
                i++; 
            } 
            if (j == M) { 
                System.out.println("Found pattern "
                                   + "at index " + (i - j)); 
                j = lps[j - 1]; 
            } 
  
            // mismatch after j matches 
            else if (i < N && pat.charAt(j) != txt.charAt(i)) { 
                // Do not match lps[0..lps[j-1]] characters, 
                // they will match anyway 
                if (j != 0) 
                    j = lps[j - 1]; 
                else
                    i = i + 1; 
            } 
        } 
    } 
  
    void computeLPSArray(String pat, int M, int lps[]) 
    { 
        // length of the previous longest prefix suffix 
        int len = 0; 
        int i = 1; 
        lps[0] = 0; // lps[0] is always 0 
  
        // the loop calculates lps[i] for i = 1 to M-1 
        while (i < M) { 
            if (pat.charAt(i) == pat.charAt(len)) { 
                len++; 
                lps[i] = len; 
                i++; 
            } 
            else // (pat[i] != pat[len]) 
            { 
                // This is tricky. Consider the example. 
                // AAACAAAA and i = 7. The idea is similar 
                // to search step. 
                if (len != 0) { 
                    len = lps[len - 1]; 
  
                    // Also, note that we do not increment 
                    // i here 
                } 
                else // if (len == 0) 
                { 
                    lps[i] = len; 
                    i++; 
                } 
            } 
        } 
    } 
	// Driver program to test above function
	public static void main(String args[]) {
		String txt = "aabcabdababeabacababc";//"ABABDABACDABABCABAB";
		String pat = "ababc";//"ABABAABAB";
		new KMP().KMPSearch(pat, txt);
	}
}