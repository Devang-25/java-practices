package stringpattern;

public class LexicalOrder {
	void Print(int n)
	{
	    for(int i = 1; i <= 9; i ++){
	        int j = 1;
	        while( j <= n){
	            for(int m = 0; m < j ; ++ m){
	                if(m + j * i <= n){
	                    System.out.println( m + j * i);
	                }
	            }
	            j *= 10;
	        }
	    }
	}
	
	public static void main(String[] args) {
		new LexicalOrder().Print(1102);
	}

}
