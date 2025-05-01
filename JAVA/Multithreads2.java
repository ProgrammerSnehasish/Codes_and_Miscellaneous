//Multi Threads Example(Odd Even).
import java.util.Scanner;
import java.lang.Runnable;
class MultiThreadDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the range to print numbers: ");
        int range = scanner.nextInt();
        scanner.close();
        Thread thread1 = new Thread(new PrintNumber(range));
        Thread thread2 = new Thread(new PrintNumber(range));
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

class PrintNumber implements Runnable {
    private final int range;
    public PrintNumber(int range) {
        this.range = range;
    }
    public void run() {
        synchronized (System.out) {
            System.out.println("Printing Numbers up to " + range + "...");
            for (int i = 1; i <= range; i++) {
                    System.out.print(i + " ");
            }
            System.out.println(); 
        }
    }
}
