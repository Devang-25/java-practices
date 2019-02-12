package problems;

public class BitWiseOperator {
	
	//given an array, where each number repeats twice except two numbers. find these two numbers
	
	static void getDictinctNumsInDuplicateNums(int[] arr) {
		int xor=0;
		for(int i=0; i<arr.length; i++) {
			xor ^=arr[i];
		}
		System.out.println(xor);
		System.out.println(40^48);
		System.out.println(40^24);  //101000
		System.out.println(48^24);  //110000 //11000
		/* Get the rightmost set bit in set_bit_no */
		 int  set_bit_no = xor & ~(xor-1); //  01000
		 System.out.println(set_bit_no);
		 int x=0; int y=0;
		  
		  /* Now divide elements in two sets by comparing rightmost set 
		   bit of xor with bit at same position in each element. */
		  for(int i = 0; i < arr.length; i++) 
		  { 
		    if((arr[i] & set_bit_no) >0) 
		     x = x ^ arr[i]; /*XOR of first set */
		    else
		     y = y ^ arr[i]; /*XOR of second set*/
		  } 
		  System.out.println("x: "+ x + ", y: "+ y);
	}

	public static void main(String[] args) {
		int[] arr= {1,2,1,3,2,40,48,3};
		getDictinctNumsInDuplicateNums(arr);

	}

}
