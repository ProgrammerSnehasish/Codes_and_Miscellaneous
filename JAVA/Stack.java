import java.io.*;
import java.util.Scanner;

 public class Stack {
    private int capacity;
    private int top;
    private int[] arr;

    Stack(int size) {
        capacity = size;
        arr = new int[capacity];
        top = -1;
    }

    public void push(int value) {
        if(top == capacity-1) {
            System.out.println("The Stack is overflowed, can't enter data.");
            return;
        }
        arr[++top] = value;
        System.out.println(value+" pushed into Stack");
    }

    public int pop() {
        if(top == -1) {
            System.out.println("The Stack is in Underflow condition, can't delete data.");
            return -1;
        }
        return arr[top--];
    }

    public void peek() {
        if(top == -1) {
            System.out.println("The stack is empty.");
            return;
        }
        System.out.println("The peek element of the stack is: "+arr[top]);
    }

    public void display() {
        if(top ==-1) {
            System.out.println("There is no data in the stack.");
            return;
        }
        System.out.println("The stack is:");
        for(int i=top;i>=0;i--){
            System.out.print(arr[i]);
        }
        System.out.print("\n");
    }
//  }

//  class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Max capacity of Stack:");
        int size = scan.nextInt();
        Stack s = new Stack(size);

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.pop();
        s.peek();
        s.display();
    }
 }