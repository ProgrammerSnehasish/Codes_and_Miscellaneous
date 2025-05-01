//Linked List.
import java.io.*;
import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMenu:\n1. Append a value\n2. Insert a value at a position\n3. Remove a value\n4. Reverse the list\n5. Search for a value\n6. Display the list\n0. Exit\n");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter a value to append: ");
                    int value = scanner.nextInt();
                    list.append(value);
                    System.out.println("Appended " + value);
                    break;
                case 2:
                    System.out.print("Enter a value to insert: ");
                    value = scanner.nextInt();
                    System.out.print("Enter the position to insert at: ");
                    int position = scanner.nextInt();
                    list.insert(position, value);
                    System.out.println("Inserted " + value + " at position " + position);
                    break;
                case 3:
                    System.out.print("Enter the value to remove: ");
                    value = scanner.nextInt();
                    Node removedNode = list.remove(value);
                    if (removedNode != null) {
                        System.out.println("Removed value " + value);
                    } else {
                        System.out.println("Value not found");
                    }
                    break;
                case 4:
                    list.reverse();
                    System.out.println("List has been reversed.");
                    break;
                case 5:
                    System.out.print("Enter a value to search: ");
                    value = scanner.nextInt();
                    Node foundNode = list.search(value);
                    if (foundNode != null) {
                        System.out.println("Value " + value + " found.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    break;
                case 6:
                    System.out.println("Current List: " + list);
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
class Node {
    private int data;
    private Node next;
    Node(int data) {
        this.data = data;
        this.next = null;
    }
    void setData(int data) {
        this.data = data;
    }
    int getData() {
        return data;
    }
    void setNext(Node next) {
        this.next = next;
    }
    Node getNext() {
        return next;
    }
}
class LinkedList {
    private Node head;
    LinkedList() {
        head = null;
    }
    void append(int value) {
        Node p = new Node(value);
        if (head == null) {
            head = p;
        } else {
            Node q = head;
            while (q.getNext() != null) {
                q = q.getNext();
            }
            q.setNext(p);
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node q = head;
        while (q != null) {
            sb.append(q.getData()).append(" -> ");
            q = q.getNext();
        }
        sb.append("null");
        return sb.toString();
    }
    Node search(int value) {
        Node q = head;
        while (q != null) {
            if (q.getData() == value) {
                return q;
            }
            q = q.getNext();
        }
        return null;
    }
    Node remove(int value) {
        if (head == null) {
            return null;
        }
        if (head.getData() == value) {
            Node temp = head;
            head = head.getNext();
            return temp;
        }
        Node prev = head;
        Node curr = head.getNext();
        while (curr != null) {
            if (curr.getData() == value) {
                prev.setNext(curr.getNext());
                curr.setNext(null);
                return curr;
            }
            prev = curr;
            curr = curr.getNext();
        }
        return null;
    }
    void insert(int pos, int value) {
        Node newNode = new Node(value);
        if (pos == 0) {
            newNode.setNext(head);
            head = newNode;
            return;
        }
        Node prev = head;
        for (int i = 0; i < pos - 1 && prev != null; i++) {
            prev = prev.getNext();
        }
        if (prev == null) {
            System.out.println("Position out of bounds");
            return;
        }
        newNode.setNext(prev.getNext());
        prev.setNext(newNode);
    }
    void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }
}
