//Student Details.
import java.util.Scanner;
class Student {
    private int roll;
    private String name;
    public Student acceptValue(int roll, String name) {
        this.roll = roll;
        this.name = name;
        return this;
    }
    public void display() {
        System.out.println("Roll Number: " + roll);
        System.out.println("Name: " + name);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter roll number: ");
        int roll = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        new Student().acceptValue(roll, name).display();
        scanner.close();
    }
}

