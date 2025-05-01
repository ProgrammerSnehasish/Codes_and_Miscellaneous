//Abbreviate name.
import java.util.Scanner;
class ShortFormName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the full name: ");
        String fullName = scanner.nextLine();
        scanner.close();
        String shortForm = getShortForm(fullName);
        System.out.println("Short form: " + shortForm);
    }
     private static String getShortForm(String name) {
        String[] parts = name.split(" ");
        if (parts.length < 2) {
            return name; 
        }
        StringBuilder shortName = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            shortName.append(parts[i].charAt(0)).append('.');
        }
        shortName.append(parts[parts.length - 1]);
        return shortName.toString();
    }
}
