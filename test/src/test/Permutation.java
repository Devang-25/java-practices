package test;

public class Permutation {
	
	static void permute(String str, int l, int r) {
		
		if(l==r) {
			System.out.println(str);
		} else {
			for(int j=l; j<=r ; j++) {
				str= swap(str, l, j);
				permute(str, l+1, r);
				str= swap(str, l, j);
			}
		}
		
	}
	
	static String swap(String str, int i, int j) {
		char[] ch= str.toCharArray();
		char c= ch[i];
		ch[i]= ch[j];
		ch[j]= c;
		return str= String.valueOf(ch);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		permute("abc", 0, 2);

	}

}
