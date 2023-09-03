import java.util.Scanner;
import java.util.Stack;

public class InfixToPostfixConverter {
	
	
	
	public static StringBuilder convertToPostFix(StringBuilder infix)
	{
		Stack<Character> charStck = new Stack<>();
		StringBuilder strbldr = new StringBuilder("");
		
		charStck.push('('); //Add a left parenthesi
		infix.append(')'); //Append right parnethsi
		
		
		for (int count = 0;  !charStck.isEmpty(); ++count) {
			if (Character.isDigit(infix.charAt(count)))
			{
				strbldr.append(infix.charAt(count) + " ");
				
			}
			else if (infix.charAt(count) ==  '(')
			{
				charStck.push('(');
				
			}
			else if (isOperator(infix.charAt(count))) 
			{
				while(isOperator(charStck.peek()) && precedence(infix.charAt(count), charStck.peek()))
				{
					strbldr.append(charStck.pop() + " ");
				}
				charStck.push(infix.charAt(count));
				
			}
			else if (infix.charAt(count) == ')')
			{
				while (charStck.peek() != '(')
				{
					strbldr.append(charStck.pop() + " ");
				}
				charStck.pop();
			}
		}
		return strbldr;
				
	}
	
	
	
	//Methods For checking if input character is a operator
	private static boolean isOperator(char o) {
	      return o == '+' || o == '-' || o == '*' ||
	         o == '/' || o == '^' || o == '%';
	   }
	
	
	
	
	//method for setting presedence for the operators(order they should happen)
	   private static boolean precedence(char op1, char op2) 
	   {
	      if (op1 == '^') 
	      {
	         return false;
	      }
	      else if (op2 == '^') 
	      {
	         return true;
	      }
	      else if (op1 == '*' || op1 == '/' || op1 == '%') 
	      {
	         return false;
	      }
	      else if (op2 == '*' || op2 == '/' || op2 == '%') 
	      {
	         return true;
	      }
	      else 
	      {
	         return false;
	      }
	   }



// Main Method

public static void main(String[] args) 
{
  
   Scanner keyboard = new Scanner(System.in);
   System.out.printf("Please enter an infix expression:\n");
   StringBuilder infix = new StringBuilder(keyboard.nextLine());
   System.out.printf("\nThe original infix expression is:\n%s\n", infix);


   StringBuilder postfix = convertToPostFix(infix);

   System.out.printf("The expression in postfix notation is:\n%s\n", postfix);
}  
}
