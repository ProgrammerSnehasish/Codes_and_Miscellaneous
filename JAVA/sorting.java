//Sortings using interface Sortable
import java.util.Scanner;
class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the first array(For Bubble Sort):");
        int size1 = scanner.nextInt();
        int[] array1 = new int[size1];
        System.out.println("Enter the elements of the first array(For Bubble Sort):");
        for (int i = 0; i < size1; i++) {
            array1[i] = scanner.nextInt();
        }
        System.out.println("Enter the size of the second array(For Selection Sort):");
        int size2 = scanner.nextInt();
        int[] array2 = new int[size2];
        System.out.println("Enter the elements of the second array(For Selection Sort):");
        for (int i = 0; i < size2; i++) {
            array2[i] = scanner.nextInt();
        }
        Sortable bubbleSorter = new BubbleSort();
        bubbleSorter.sort(array1);
        System.out.println("BubbleSort result:");
        for (int num : array1) {
            System.out.print(num + " ");
        }
        System.out.println();
        Sortable selectionSorter = new SelectionSort();
        selectionSorter.sort(array2);
        System.out.println("SelectionSort result:");
        for (int num : array2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
interface Sortable{
    void sort(int[] array);
}
class BubbleSort implements Sortable {
    public void sort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j + 1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
class SelectionSort implements Sortable {
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
