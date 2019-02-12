package problems;

import java.util.ArrayList;
import java.util.List;

public class OlaProblems {
	static final int no_of_chars = 256; 
	
	/*
	 * Q1. Find the smallest window in string 1 which contain all the characters of second string. 
	 * Q2. Find partitions of string s in which all partitions are palindrome. 
	 * Q3. Find middle of linked list 
	 * Q3 Reverse a string using stack 
	 * Q5 Find Loop in Linked List.
	 */

	static String findSmallestWindow(String str, String pat) {
		int[] pat_arr = new int[256];
		int[] str_arr = new int[256];
		int len1= str.length();
		int len2= pat.length();
		if(len1<len2) return null;

		for (int i = 0; i < pat.length(); i++) {
			pat_arr[pat.charAt(i) - 'a']++;
		}

		int start = 0;
		int startIndex = -1;
		int minLength = Integer.MAX_VALUE;
		int count = 0;
		
		int i = 0;
		for ( ;i < len1; i++) {
			str_arr[str.charAt(i) - 'a']++;
			
			// If string's char matches with pattern's char 
            // then increment count 
            if (pat_arr[str.charAt(i)] != 0 && str_arr[str.charAt(i)] <= pat_arr[str.charAt(i)] ) 
                count++; 
            if(count==len2) {
            	while(pat_arr[str.charAt(i)] ==0 || str_arr[str.charAt(i)]> pat_arr[str.charAt(i)]) {
            		if(str_arr[str.charAt(i)]> pat_arr[str.charAt(i)]) str_arr[str.charAt(i)]--;
            		start++;
            	}
            }
            if(i-start+1< minLength) {
            	minLength= i-start+1;
            	startIndex=start;
            }
		}

		if(startIndex==-1) return "NO Window";
		return str.substring(startIndex, startIndex+minLength);
		
	}

	
    
    // Function to find smallest window containing 
    // all characters of 'pat' 
    static String findSubString(String str, String pat) 
    { 
        int len1 = str.length(); 
        int len2 = pat.length(); 
      
        // check if string's length is less than pattern's 
        // length. If yes then no such window can exist 
        if (len1 < len2) 
        { 
            System.out.println("No such window exists"); 
            return ""; 
        } 
      
        int hash_pat[] = new int[no_of_chars]; 
        int hash_str[] = new int[no_of_chars]; 
      
        // store occurrence ofs characters of pattern 
        for (int i = 0; i < len2; i++) 
            hash_pat[pat.charAt(i)]++; 
      
        int start = 0, start_index = -1, min_len = Integer.MAX_VALUE; 
      
        // start traversing the string 
        int count = 0; // count of characters 
        for (int j = 0; j < len1 ; j++) 
        { 
            // count occurrence of characters of string 
            hash_str[str.charAt(j)]++; 
      
            // If string's char matches with pattern's char 
            // then increment count 
            if (hash_pat[str.charAt(j)] != 0 && 
                hash_str[str.charAt(j)] <= hash_pat[str.charAt(j)] ) 
                count++; 
      
            // if all the characters are matched 
            if (count == len2) 
            { 
                // Try to minimize the window i.e., check if 
                // any character is occurring more no. of times 
                // than its occurrence in pattern, if yes 
                // then remove it from starting and also remove 
                // the useless characters. 
                while ( hash_str[str.charAt(start)] > hash_pat[str.charAt(start)] 
                    || hash_pat[str.charAt(start)] == 0) 
                { 
      
                    if (hash_str[str.charAt(start)] > hash_pat[str.charAt(start)]) 
                        hash_str[str.charAt(start)]--; 
                    start++; 
                } 
      
                // update window size 
                int len_window = j - start + 1; 
                if (min_len > len_window) 
                { 
                    min_len = len_window; 
                    start_index = start; 
                } 
            } 
        } 
      
        // If no window found 
        if (start_index == -1) 
        { 
        System.out.println("No such window exists"); 
        return ""; 
        } 
      
        // Return substring starting from start_index 
        // and length min_len 
        return str.substring(start_index, start_index + min_len); 
    } 
    
   static void findPartitionWithAllPalindrom(String str){
    	int n= str.length();
    	boolean[][] pal=new boolean[n][n];
    	List<String> palindoms= new ArrayList<String> ();
    	
    	for(int i=0; i<n; i++) {
    		pal[i][i]= true;
    		System.out.print(str.charAt(i)+ " ");
    	}
    	System.out.println();
    	for(int i=0; i<n-1; i++) {
	    	if(str.charAt(i)== str.charAt(i+1)) {
				pal[i][i+1]=true;
				
			}
    	}
    	
    	for(int i=0; i<n-1; i++) {
    		if(pal[i][i+1]) {
    			System.out.print(str.substring(i, i+2)+ " ");
    			i++;
    		}else {
				System.out.print(str.charAt(i)+ " ");
				if(i==n-2) {
					System.out.print(str.charAt(i+1)+ " ");
				}
			}
    	}
    	System.out.println();
    	for(int i=2; i<n; i++) {
    		for(int j=0; j<n-i; j++) {
    			if(str.charAt(j)== str.charAt(j+i) && pal[j+1][i+j-1]) {
    				pal[i][j]=true;
    				
    			}
    		}
    		System.out.println();
    	}
    	for(int i=2; i<n; i++) {
    		for(int j=0; j<n-i; j++) {
    			if(pal[j][i]) {
    				System.out.print(str.substring(j, i+j+1)+ " ");
    				j=j+i;
    			}else {
    				System.out.print(str.charAt(i)+ " ");
    			}
    		}
    	}
    	
    }
      
    // Driver Method 
    public static void main(String[] args) 
    { 
        String str = "this is a test string"; 
        String pat = "tist"; 
//      
//	    System.out.println("Smallest window is :\n " + 
//	                        findSubString(str, pat)); 
//	    
//	    System.out.println("Smallest window is :\n " +  findSubString("abca", "ac"));
//	    
	    findPartitionWithAllPalindrom("geeeks");
    } 

}
