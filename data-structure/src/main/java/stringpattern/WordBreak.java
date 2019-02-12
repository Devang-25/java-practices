package stringpattern;

public class WordBreak {
	
	// string can be segmented into space separated  
	// words in dictionary 
	  
	/* A utility function to check whether a word is  
	  present in dictionary or not. An array of strings  
	  is used for dictionary.  Using array of strings for 
	  dictionary is definitely not a good idea. We have  
	  used for simplicity of the program*/
	boolean dictionaryContains(String word) 
	{ 
	    String dictionary[] = {"mobile","samsung","sam","sung", 
	                            "man","mango","icecream","and", 
	                             "go","i","like","ice","cream"}; 
	    int size = dictionary.length; 
	    for (int i = 0; i < size; i++) 
	        if (dictionary[i].equals(word)) 
	           return true; 
	    return false; 
	} 
	  
	// returns true if string can be segmented into space  
	// separated words, otherwise returns false 
	boolean wordBreakRecursive(String str) 
	{ 
	    int size = str.length(); 
	  
	    // Base case 
	    if (size == 0)  return true; 
	  
	    // Try all prefixes of lengths from 1 to size 
	    for (int i=1; i<=size; i++) 
	    { 
	        // The parameter for dictionaryContains is  
	        // str.substr(0, i) which is prefix (of input  
	        // string) of length 'i'. We first check whether  
	        // current prefix is in  dictionary. Then we  
	        // recursively check for remaining string 
	        // str.substr(i, size-i) which is suffix of   
	        // length size-i 
	        if (dictionaryContains( str.substring(0, i) ) && 
	        		wordBreakRecursive( str.substring(i, size) )) 
	            return true; 
	    } 
	  
	    // If we have tried all prefixes and  
	    // none of them worked 
	    return false; 
	} 
	  
	
	boolean wordBreakDP(String s) 
	{ 
	    int n = s.length(); 
	    if (n == 0) 
	        return true; 
	  
	    // Create the DP table to store results of subroblems. 
	    // The value dp[i] will be true if str[0..i] can be 
	    // segmented into dictionary words, otherwise false. 
	    boolean[] dp= new boolean[n+1]; // Initialize all values 
	    // as false. 
	  
	    // matched_index array represents the indexes for which 
	    // dp[i] is true. Initially only -1 element is present 
	    // in this array. 
	    int[] matched_index= new int[n+1];
	    for(int i=0; i<n+1; i++) {
	    	matched_index[i]=-1;
	    }
	    
	  int k=matched_index.length-1;
	    for (int i = 0; i < n; i++) { 
	        int msize = matched_index.length; 
	  
	        // Flag value which tells that a substring matches 
	        // with given words or not. 
	        boolean f = false; 
	  
	        // Check all the substring from the indexes matched 
	        // earlier. If any of that substring matches than 
	        // make flag value = 1; 
	        for (int j = msize - 1; j >= 0; j--) { 
	  
	            // sb is substring starting from matched_index[j] 
	            // + 1  and of length i - matched_index[j] 
	            String sb = s.substring(matched_index[j] + 1, i+1); 
	  
	            if (dictionaryContains(sb)) { 
	                f = true; 
	                break; 
	            } 
	        } 
	  
	        // If substring matches than do dp[i] = 1 and 
	        // push that index in matched_index array. 
	        if (f) { 
	            dp[i] = true; 
	            matched_index[k--]=i; 
	        } 
	    } 
	    return dp[n - 1]; 
	} 
	// Driver program to test above functions 
	public static void main(String[] args) {
		WordBreak wb= new WordBreak();
		
		System.out.println(System.currentTimeMillis());
		System.out.println(wb.wordBreakRecursive("ilikesamsung")); 
		System.out.println(wb.wordBreakRecursive("iiiiiiii")); 
		System.out.println(wb.wordBreakRecursive("")); 
		System.out.println(wb.wordBreakRecursive("ilikelikeimangoiii"));  
		System.out.println(wb.wordBreakRecursive("samsungandmango")); 
		System.out.println(wb.wordBreakRecursive("samsungandmangok")); 
		System.out.println(System.currentTimeMillis());
		
		System.out.println(wb.wordBreakDP("ilikesamsung")); 

	}

}
