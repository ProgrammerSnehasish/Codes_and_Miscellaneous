//Multi Threads Example(Odd Even).
import java.util.Scanner;
import java.lang.Runnable;
class MultiThreadDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the range to print odd and even numbers: ");
        int range = scanner.nextInt();
        scanner.close();

        Thread thread1 = new Thread(new PrintOdd(range));
        Thread thread2 = new Thread(new PrintEven(range));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Threads have finished execution.");
    }
}

class PrintOdd implements Runnable {
    private final int range;
    public PrintOdd(int range) {
        this.range = range;
    }
    public void run() {
        synchronized (System.out) {
            System.out.println("Printing Odd Numbers up to " + range + "...");
            for (int i = 1; i <= range; i++) {
                if (i % 2 != 0) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
    }
}
class PrintEven implements Runnable {
    private final int range;

    public PrintEven(int range) {
        this.range = range;
    }
    public void run() {
        synchronized (System.out) {
            System.out.println("Printing Even Numbers up to " + range + "...");
            for (int i = 1; i <= range; i++) {
                if (i % 2 == 0) {
                    System.out.print(i + " ");
                }
            }
            System.out.println(); 
        }
    }
}
