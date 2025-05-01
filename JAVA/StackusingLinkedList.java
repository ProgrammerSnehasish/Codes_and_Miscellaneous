//Stack using Linkedlist(Using Generic Class).
import java.io.*;
import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMenu:\n1. Push a value\n2. Pop a value\n3. Peek at the top value\n4. Display the stack\n0. Exit\n");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter a value to push: ");
                    String value = scanner.next();
                    stack.push(value);
                    System.out.println("Pushed " + value);
                    break;
                case 2:
                    Node<String> poppedNode = stack.pop();
                    if (poppedNode != null) {
                        System.out.println("Popped value " + poppedNode.getData());
                    } else {
                        System.out.println("Stack is empty");
                    }
                    break;
                case 3:
                    Node<String> topNode = stack.peek();
                    if (topNode != null) {
                        System.out.println("Top value is " + topNode.getData());
                    } else {
                        System.out.println("Stack is empty");
                    }
                    break;
                case 4:
                    System.out.println("Current Stack: " + stack);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
        scanner.close();
    }
}
class Node<T> {
    private T data;
    private Node <T> next;
    Node(T data) {
        this.data = data;
        this.next = null;
    }
    T getData() {
        return data;
    }
    void setNext(Node<T> next) {
        this.next = next;
    }
    Node<T> getNext() {
        return next;
    }
}
class Stack<T> {
    private Node<T> top;
    Stack() {
        top = null;
    }
    void push(T value) {
        Node<T> newNode = new Node<T>(value);
        newNode.setNext(top);
        top = newNode;
    }
    Node<T> pop() {
        if (top == null) {
            return null;
        }
        Node<T> temp = top;
        top = top.getNext();
        return temp;
    }
    Node<T> peek() {
        return top;
    }
    public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<T> current = top;
		while (current != null) {
		    sb.append(current.getData());
		    if (current.getNext() != null) {
		        sb.append("->");
		    }
		    current = current.getNext();
		}
		return sb.toString();
	}
}

