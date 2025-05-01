//Time
import java.util.Scanner;
class Time {
    private int hour;
    private int minute;
    private int second;
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public Time(int totalSeconds) {
        this.hour = totalSeconds / 3600;
        this.minute = (totalSeconds % 3600) / 60;
        this.second = totalSeconds % 60;
    }
    public void display() {
        System.out.printf("%02d:%02d:%02d\n", hour, minute, second);
    }
    public Time add(Time other) {
        int totalSeconds = this.toSeconds() + other.toSeconds();
        return new Time(totalSeconds);
    }
    private int toSeconds() {
        return this.hour * 3600 + this.minute * 60 + this.second;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nChoose an option:\n1. Convert seconds to HH:MM:SS.\n2. Add two times.\n3. Exit.\n");
            System.out.print("Enter your choice (1-3): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter time in seconds: ");
                    int totalSeconds = scanner.nextInt();
                    Time time = new Time(totalSeconds);
                    System.out.print("Time converted from seconds: ");
                    time.display();
                    break;
                case 2:
                    System.out.print("Enter first time (hours minutes seconds): ");
                    int hour1 = scanner.nextInt();
                    int minute1 = scanner.nextInt();
                    int second1 = scanner.nextInt();
                    Time time1 = new Time(hour1, minute1, second1);
                    System.out.print("Enter second time (hours minutes seconds): ");
                    int hour2 = scanner.nextInt();
                    int minute2 = scanner.nextInt();
                    int second2 = scanner.nextInt();
                    Time time2 = new Time(hour2, minute2, second2);
                    Time sumTime = time1.add(time2);
                    System.out.print("Sum of the two times: ");
                    sumTime.display();
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    break;
            }

        } while (choice != 3);
    }
}

