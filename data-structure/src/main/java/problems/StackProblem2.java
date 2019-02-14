package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackProblem2 {
	
	List<String> convertPrefixToPostfix(List<String> operations){
		if(operations== null || operations.size()==0) return operations;
		
		int n= operations.size();
		List<String> results= new ArrayList<String>();
		
		for(String op: operations) {
			results.add(convertPrefixToPostfix(op));
		}
		return results;
	}
	
	boolean isOperator1(char c) {
		String ops= "+-/*";
		
		return ops.lastIndexOf(c)>=0;
	}
	
	String convertPrefixToPostfix(String operation) {
		Stack<Character> st= new Stack<Character>();
		int n= operation.length();
		
		String result="";
		for(int i=0; i<n; i++) {
			char c= operation.charAt(i);
			if(isOperator(c)) {
				
				if(i>0 && !isOperator(operation.charAt(i-1))) {
					while(st.size()>0) {
						result +=st.pop();
					}
				}
				st.push(c);
			}else {
				result +=c;
				
			}
			//System.out.println(result);
		}
		while(st.size()>0) {
			result +=st.pop();
		}
		return result;
	}
	
	
	static boolean isOperator(char x)  
	{ 
	    switch (x)  
	    { 
	        case '+': 
	        case '-': 
	        case '/': 
	        case '*': 
	        return true; 
	    } 
	    return false; 
	} 
	  
	// Convert prefix to Postfix expression 
	static String preToPost(String pre_exp) 
	{ 
	  
	    Stack<String> s= new Stack<String>(); 
	  
	    // length of expression 
	    int length = pre_exp.length(); 
	  
	    // reading from right to left 
	    for (int i = length - 1; i >= 0; i--)  
	    { 
	  
	        // check if symbol is operator 
	        if (isOperator(pre_exp.charAt(i)))  
	        { 
	  
	            // pop two operands from stack 
	            String op1 = s.peek(); s.pop(); 
	            String op2 = s.peek(); s.pop(); 
	  
	            // concat the operands and operator 
	            String temp = op1 + op2 + pre_exp.charAt(i); 
	  
	            // Push String temp back to stack 
	            s.push(temp); 
	        } 
	  
	        // if symbol is an operand 
	        else
	        { 
	            // push the operand to the stack 
	            s.push( pre_exp.charAt(i)+""); 
	        } 
	    } 
	  
	    // stack contains only the Postfix expression 
	    return s.peek(); 
	} 
	
	
	 static int Prec(char ch) 
	    { 
	        switch (ch) 
	        { 
	        case '+': 
	        case '-': 
	            return 1; 
	       
	        case '*': 
	        case '/': 
	            return 2; 
	       
	        case '^': 
	            return 3; 
	        } 
	        return -1; 
	    } 
	       
	    // The main method that converts given infix expression 
	    // to postfix expression.  
	    static String infixToPostfix(String exp) 
	    { 
	        // initializing empty String for result 
	        String result = new String(""); 
	          
	        // initializing empty stack 
	        Stack<Character> stack = new Stack<Character>(); 
	          
	        for (int i = 0; i<exp.length(); ++i) 
	        { 
	            char c = exp.charAt(i); 
	              
	             // If the scanned character is an operand, add it to output. 
	            if (Character.isLetterOrDigit(c)) 
	                result += c; 
	               
	            // If the scanned character is an '(', push it to the stack. 
	            else if (c == '(') 
	                stack.push(c); 
	              
	            //  If the scanned character is an ')', pop and output from the stack  
	            // until an '(' is encountered. 
	            else if (c == ')') 
	            { 
	                while (!stack.isEmpty() && stack.peek() != '(') 
	                    result += stack.pop(); 
	                  
	                if (!stack.isEmpty() && stack.peek() != '(') 
	                    return "Invalid Expression"; // invalid expression                 
	                else
	                    stack.pop(); 
	            } 
	            else // an operator is encountered 
	            { 
	                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) 
	                    result += stack.pop(); 
	                stack.push(c); 
	            } 
	       
	        } 
	       
	        // pop all the operators from the stack 
	        while (!stack.isEmpty()) 
	            result += stack.pop(); 
	       
	        return result; 
	    } 

	public static void main(String[] args) {
		StackProblem2 sp= new StackProblem2();
		System.out.println( sp.preToPost("++c*abd"));

	}

}
