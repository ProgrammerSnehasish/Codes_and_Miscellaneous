//Sorting.

import java.util.Scanner;

public class Sort {
    public static final int NUM_ELEMENTS = 10;

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static int indexSmallest(int[] arr, int start) {
        int indexMin = start;
        for (int i = start + 1; i < arr.length; i++) {
            if (arr[i] < arr[indexMin]) {
                indexMin = i;
            }
        }
        return indexMin;
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int j = indexSmallest(arr, i);
            swap(arr, i, j);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                int toInsert = arr[i];
                int j = i;
                do {
                    arr[j] = arr[j - 1];
                    j = j - 1;
                } while (j > 0 && toInsert < arr[j - 1]);
                arr[j] = toInsert;
            }
        }
    }

    public static void shellSort(int[] arr) {
        int incr = 1;
        while (2 * incr <= arr.length)
            incr = 2 * incr;
        incr = incr - 1;
        
        while (incr >= 1) {
            for (int i = incr; i < arr.length; i++) {
                if (arr[i] < arr[i - incr]) {
                    int toInsert = arr[i];
                    int j = i;
                    do {
                        arr[j] = arr[j - incr];
                        j = j - incr;
                    } while (j > incr - 1 && toInsert < arr[j - incr]);
                    arr[j] = toInsert;
                }
            }
            incr = incr / 2;
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static int partition(int[] arr, int first, int last) {
        int pivot = arr[(first + last) / 2];
        int i = first - 1;
        int j = last + 1;

        do {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);
            if (i < j) {
                swap(arr, i, j);
            }
        } while (i < j);

        return j;
    }

    private static void qSort(int[] arr, int first, int last) {
        int split = partition(arr, first, last);
        if (first < split) {
            qSort(arr, first, split);
        }
        if (last > split + 1) {
            qSort(arr, split + 1, last);
        }
    }

    public static void quickSort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
    }

    private static void merge(int[] arr, int[] temp, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int i = leftStart;
        int j = rightStart;
        int k = leftStart;
        
        while (i <= leftEnd && j <= rightEnd) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++; k++;
            } else {
                temp[k] = arr[j];
                j++; k++;
            }
        }

        while (i <= leftEnd) {
            temp[k] = arr[i];
            i++; k++;
        }
        while (j <= rightEnd) {
            temp[k] = arr[j];
            j++; k++;
        }
        for (i = leftStart; i <= rightEnd; i++) {
            arr[i] = temp[i];
        }
    }

    private static void mSort(int[] arr, int[] temp, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (start + end) / 2;
        mSort(arr, temp, start, middle);
        mSort(arr, temp, middle + 1, end);
        merge(arr, temp, start, middle, middle + 1, end);
    }

    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mSort(arr, temp, 0, arr.length - 1);
    }

    public static void printArray(int[] arr) {
        System.out.print("{ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("}");
    }

    public static void main(String[] arr) { 
        int[] orig = new int[NUM_ELEMENTS];
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            orig[i] = (int)(50 * Math.random());
        }
        printArray(orig);
        
        int[] copy = new int[NUM_ELEMENTS];
        
        System.arraycopy(orig, 0, copy, 0, orig.length); 
        selectionSort(copy);
        System.out.print("selection sort:\t");
        printArray(copy);
        
        System.arraycopy(orig, 0, copy, 0, orig.length); 
        insertionSort(copy);
        System.out.print("insertion sort:\t");
        printArray(copy);
        
        System.arraycopy(orig, 0, copy, 0, orig.length); 
        shellSort(copy);
        System.out.print("Shell sort:\t");
        printArray(copy);
        
        System.arraycopy(orig, 0, copy, 0, orig.length); 
        bubbleSort(copy);
        System.out.print("bubble sort:\t");
        printArray(copy);
        
        System.arraycopy(orig, 0, copy, 0, orig.length); 
        quickSort(copy);
        System.out.print("quicksort:\t");
        printArray(copy);
        
        System.arraycopy(orig, 0, copy, 0, orig.length); 
        mergeSort(copy);
        System.out.print("mergesort:\t");
        printArray(copy);
    }
}
