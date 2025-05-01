//String Programme.
import java.util.Scanner;

public class MyString {
    private String value;
    public MyString() {
        this.value = "";
    }
    public MyString(String value) {
        this.value = value;
    }
    public void concatenate(MyString other) {
        this.value += other.value;
    }
    public void display() {
        System.out.println(this.value);
    }
    public int countVowels() {
        int count = 0;
        for (char c : value.toCharArray()) {
            if (isVowel(c)) {
                count++;
            }
        }
        return count;
    }
    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    public int findWordOccurrence(String word) {
        int count = 0;
        int index = 0;
        while ((index = value.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while(choice != 7) {
        System.out.println("Choose an option:\n1. Create an uninitialized string.\n2. Create a string with initial value.\n3. Concatenate two strings.\n4. Display the string.\n5. Count vowels in the string.\n6. Find the occurrence of a word in the string.\n7. Exit\n");
        System.out.print("Enter your choice (1-7): ");
        choice = scanner.nextInt();
        scanner.nextLine();
        MyString str = new MyString();
        switch (choice) {
            case 1:
                System.out.println("Uninitialized string created.");
                break;
            case 2:
                System.out.print("Enter the initial value of the string: ");
                String initialValue = scanner.nextLine();
                str = new MyString(initialValue);
                System.out.println("String initialized with value: " + initialValue);
                break;
            case 3:
                System.out.print("Enter the first string: ");
                MyString str1 = new MyString(scanner.nextLine());
                System.out.print("Enter the second string: ");
                MyString str2 = new MyString(scanner.nextLine());
                str1.concatenate(str2);
                System.out.println("Concatenated string: " + str1.value);
                break;
            case 4:
                System.out.print("Enter the string to display: ");
                str = new MyString(scanner.nextLine());
                System.out.println("Displayed string: ");
                str.display();
                break;
            case 5:
                System.out.print("Enter the string to count vowels: ");
                str = new MyString(scanner.nextLine());
                System.out.println("Number of vowels: " + str.countVowels());
                break;
            case 6:
                System.out.print("Enter the string: ");
                str = new MyString(scanner.nextLine());
                System.out.print("Enter the word to find: ");
                String word = scanner.nextLine();
                System.out.println("Occurrences of '" + word + "': " + str.findWordOccurrence(word));
                break;
	   case 7:
	   	System.out.println("Exiting...\n");
	   	break;
            default:
                System.out.println("Invalid choice.");
        }
        }
    }
}
