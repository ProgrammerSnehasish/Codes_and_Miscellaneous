//Binary Search.(05-07-2024)[Beginner Lv]
import java.util.Scanner;
class BubbleSort {
    static Scanner scanner = new Scanner(System.in);
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void get_list(int list[], int no_of_elements) {
        for (int index = 0; index < no_of_elements; index++) {
            System.out.print("Enter element " + (index + 1) + ": ");
            list[index] = scanner.nextInt();
        }
    }
    public static void display_list(int[] list,int no_of_elements){
        System.out.print("[\t");
        for(int index = 0;index < no_of_elements;index++){
            System.out.print(list[index]+"\t");
        }
        System.err.print("]\n");
    }
    public static void bubbleSort(int[] list, int no_of_elements) {
        int index,index2;
	    int flag;
	    for(index=0;index<no_of_elements-1;index++){
            flag = 0;
            for(index2=0;index2<no_of_elements-index-1;index2++){
                if(list[index2]>list[index2+1])
                {
                    swap(list,index2,index2+1);
                    flag = 1;
                }
            }
            if(flag == 0)
            break;
        }
    }
    public static void main(String[] args) {
        System.out.println("________Bubble Sort________");
        System.out.print("Enter total number of elements: ");
        int no_of_elements = scanner.nextInt();
        int[] list = new int[no_of_elements];
        get_list(list, no_of_elements);
        System.out.print("The List before sorting is:");
        display_list(list, no_of_elements);
        System.out.println("The list after sorting is:");
        bubbleSort(list, no_of_elements);
        display_list(list, no_of_elements);
    }
}