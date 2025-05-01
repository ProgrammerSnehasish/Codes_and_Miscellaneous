//Infix to Postfix using Stack using Linkedlist(Using Generic Class).
import java.io.*;
import java.util.Scanner;
class InfixToPostfix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an infix expression: ");
        String infix = scanner.nextLine();
        String postfix = infixToPostfix(infix);
        System.out.println("Postfix expression: " + postfix);
        scanner.close();
}
public static int priority(char c) {
	switch(c) {
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
public static boolean isOperand(char c) {
	return Character.isLetter(c);
}
public static String infixToPostfix(String exp) {
    Stack<Character> stack = new Stack<>();
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < exp.length(); i++) {
	char c = exp.charAt(i);
    if (isOperand(c)) {
       result.append(c);
     }else if (c == '(') {
      	stack.push(c);
     }else if (c == ')') {
        while (!stack.isEmpty() && stack.peek().getData() != '(') {
            result.append(stack.pop().getData());
         }
         stack.pop();
     }else {
         while (!stack.isEmpty() && priority(c) <= priority(stack.peek().getData())) {
             result.append(stack.pop().getData());
         }
         stack.push(c);
       }
     }
     while (!stack.isEmpty()) {
       result.append(stack.pop().getData());
      }
	  return result.toString();
    }
}
