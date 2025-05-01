//Smallest Digit
import java.util.Scanner;
class SmallestDigitFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a 5-digit number: ");
        int number = scanner.nextInt();
        if (number < 10000 || number > 99999) {
            System.out.println("The number is not a 5-digit number.");
        } else {
            int smallestDigit = findSmallestDigit(number);
            System.out.println("The smallest digit in the number is: " + smallestDigit);
        }
        
        scanner.close();
    }
    private static int findSmallestDigit(int number) {
        int smallestDigit = 9;
        while (number > 0) {
            int digit = number % 10;
            if (digit < smallestDigit) {
                smallestDigit = digit;
            }
            number /= 10; 
        }        
        return smallestDigit;
    }
}
