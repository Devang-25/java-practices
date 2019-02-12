package stringpattern;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
	
	
	boolean isAnagramSubString(String s1, String s2) {
		int l1= s1.length();
		int l2= s2.length();
		Map<Character, Integer> map= new HashMap<Character, Integer>();
		int mismatchCount=0;
		// to look at max iterations
		
		int it=0;
		
		for(int i=0; i< s2.length(); i++) {
			int value1= map.get(s1.charAt(i)) == null ? 0 : map.get(s1.charAt(i));
			int value2= map.get(s2.charAt(i)) == null ? 0 : map.get(s2.charAt(i));
			map.put(s1.charAt(i), ++value1);
			map.put(s2.charAt(i), --value2);
			it++;
		}
		for(Character c: map.keySet()) {
			mismatchCount +=Math.abs(map.get(c));
		}
		
		if(mismatchCount==0) return true;
		for(int i=1; i< l1-l2+1; i++) {
			int value1= map.get(s1.charAt(i-1));
			mismatchCount = value1>0 ? mismatchCount-1 : mismatchCount+1;
			map.put(s1.charAt(i-1), --value1);
			int value= map.get(s1.charAt(i+l2-1)) == null ? 0 : map.get(s1.charAt(i+l2-1));
			mismatchCount = value<0 ? mismatchCount-1 : mismatchCount+1;
			map.put(s1.charAt(i+l2-1), ++value);
			it++;
			if(mismatchCount==0) {
				System.out.println("total iteration: " + it);
				return true;
			}
		}
		System.out.println("total iteration: " + it);
		return false;
	}
	
	
	// O(n) approach to check two string are anagram
	boolean isAnagram(String s1, String s2) {
		// considers 256 ASCII characters. 
		// This includes standard ASCII characters(0-127) and 
		// Extended ASCII characters(128-255)
		int MAX_CHARS= 256;
		char[] chars= new char[MAX_CHARS];
		for(int i=0; i< s1.length(); i++) {
			chars[s1.charAt(i)-'a']++;
		}
		for(int i=0; i< s2.length(); i++) {
			chars[s2.charAt(i)-'a']--;
		}
		for(int i=0; i< MAX_CHARS; i++) {
			if(chars[i]!=0) return false;
		}
		return true;
		
	}
	
	// O(n) approach to check two string characters mismatch length
	int getDiffCharCounts(String s1, String s2) {
		int MAX_CHARS= 256;
		int count=0;
		char[] chars= new char[MAX_CHARS];
		for(int i=0; i< s1.length(); i++) {
			chars[s1.charAt(i)-'a']++;
		}
		for(int i=0; i< s2.length(); i++) {
			chars[s2.charAt(i)-'a']--;
		}
		for(int i=0; i< MAX_CHARS; i++) {
			if(chars[i]!=0) count= Math.abs(chars[i]);
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		Anagram a= new Anagram();
		System.out.println(a.isAnagramSubString("abbaababbbbbaac", "aacc"));

	}

}
