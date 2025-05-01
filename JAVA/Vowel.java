//Count Vowel in a String.
import java.util.Scanner;
class VowelCounter {
    public static int countVowels(String str) {
        int count = 0;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();
        int vowelCount = countVowels(inputString);
        System.out.println("The number of vowels in the string is: " + vowelCount);
        scanner.close();
    }
}
