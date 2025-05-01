//Set Operations.
import java.util.Scanner;
class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the total number of elements for the first set: ");
        int n1 = scanner.nextInt();
        int[] arr1 = new int[n1];
        System.out.println("Enter elements for the first set:");
        for (int i = 0; i < n1; i++) {
            System.out.print("Enter element "+(i+1)+": ");
            arr1[i] = scanner.nextInt();
        }
        Set set1 = new Set(arr1);
        System.out.println("Set1: " + set1);
        System.out.print("Enter the total number of elements for the second set: ");
        int n2 = scanner.nextInt();
        int[] arr2 = new int[n2];
        System.out.println("Enter elements for the second set:");
        for (int i = 0; i < n2; i++) {
            System.out.print("Enter element "+(i+1)+": ");
            arr2[i] = scanner.nextInt();
        }
        Set set2 = new Set(arr2);
        System.out.println("Set2: " + set2);
        while(true) {
        	System.out.print("List of Choice:\n1. Get Clone of Set.\n2. Convert Set to String\n3. Get Cardinality of Set.\n4. Check if the Set is empty.\n5. Check if the Set is subset of the other one.\n6. Check if the two of the given Sets are equal\n7. Insert element in the Set\n8. Delete Element form the Set.\n9. Perform Set Union.\n10. Perform Set Intersection\n11. Perform Set Difference\n12. Exit\n");
        	System.out.print("Enter Your Choice: ");
        	int choice = scanner.nextInt();
        	switch (choice) {
                case 1:
		      System.out.print("Choose One Set to make it's clone:\n1. Press 1 for making clone of Set 1\n2. Press 2 for making clone of Set 2.\n");
		      System.out.print("Enter your choice: ");
		      int subChoice = scanner.nextInt();
		         if(subChoice == 1) { 
				Set clonedSet = set1.clone();
				System.out.println("Cloned Set1: " + clonedSet);
		          } else if(subChoice == 2) { 
		            Set clonedSet = set2.clone();
		            System.out.println("Cloned Set1: " + clonedSet);
		          }else {
		            System.out.println("Invalid Choice entered. Please enter a correct choice."); 
		          }
                    break;
                case 2:
		      System.out.print("Choose One Set to make it's String form:\n1. Press 1 for making String form of Set 1\n2. Press 2 for making String form of Set 2.\n");
		            System.out.print("Enter your choice: ");
		            int subChoice2 = scanner.nextInt();
		            if(subChoice2 == 1) { 
				    System.out.println("Set1 as String: " + set1.toString());
		            } else if(subChoice2 == 2) { 
		            	System.out.println("Set1 as String: " + set2.toString());
		            }else {
		            	System.out.println("Invalid Choice entered. Please enter a correct choice."); 
		            }
                    break;
                case 3:
		    System.out.print("Choose One Set to get it's cardinality\n1. Press 1 to get cardinality of Set 1\n2. Press 2 to get cardinality of Set 2.\n");
		            System.out.print("Enter your choice: ");
		            int subChoice3 = scanner.nextInt();
		            if(subChoice3 == 1) { 
				    System.out.println("Cardinality of Set1: " + set1.cardinality());
		            } else if(subChoice3 == 2) { 
		            	System.out.println("Cardinality of Set1: " + set2.cardinality());
		            }else {
		            	System.out.println("Invalid Choice entered. Please enter a correct choice."); 
		            }
                    break;
                case 4:
		       System.out.print("Choose One Set to check if it is empty or not:\n1. Press 1 to select Set 1\n2. Press 2 to select Set 2.\n");
		            System.out.print("Enter your choice: ");
		            int subChoice4 = scanner.nextInt();
		            if(subChoice4 == 1) { 
				    System.out.println("Set 1 is " + (set1.isEmpty() ? "empty" : "not empty"));
		            } else if(subChoice4 == 2) { 
		            	System.out.println("Set 2 is " + (set1.isEmpty() ? "empty" : "not empty"));
		            }else {
		            	System.out.println("Invalid Choice entered. Please enter a correct choice."); 
		            }     
                    break;
                case 5:
		     System.out.print("Choose One Set to check subset:\n1. Press 1 to check  if Set 1 is subset of Set 2.\n2. Press 2 to check  if Set 2 is subset of Set 1.\n");
		            System.out.print("Enter your choice: ");
		            int subChoice5 = scanner.nextInt();
		            if(subChoice5 == 1) { 
				    System.out.println("Set 1 is " + (set1.subset(set2) ? "a subset of Set 2" : "not a subset of Set 2"));
		            } else if(subChoice5 == 2) { 
		            	System.out.println("Set 2 is " + (set2.subset(set1) ? "a subset of Set 1" : "not a subset of Set 1"));
		            }else {
		            	System.out.println("Invalid Choice entered. Please enter a correct choice."); 
		            }       
                    break;
                case 6:
                    System.out.println("Set1 and Set2 are " + (set1.equal(set2) ? "equal" : "not equal"));
                    break;
                case 7:
		       System.out.print("Choose One Set to insert element in it:\n1. Press 1 to insert elemet in Set 1.\n2. Press 2 to insert element in Set 2.\n");
		            System.out.print("Enter your choice: ");
		            int subChoice7 = scanner.nextInt();
		            if(subChoice7 == 1) { 
				    System.out.print("Enter element to insert into Set 1: ");
				    int insertElement = scanner.nextInt();
				    set1.insert(insertElement);
				    System.out.println("Set 1 after insertion: " + set1);
		            } else if(subChoice7 == 2) { 
		            	System.out.print("Enter element to insert into Set 2: ");
				int insertElement = scanner.nextInt();
				set2.insert(insertElement);
				System.out.println("Set 2 after insertion: " + set2);
		            }else {
		            	System.out.println("Invalid Choice entered. Please enter a correct choice."); 
		            }     
                    break;
                case 8:
		            System.out.print("Choose One Set to delete element in it:\n1. Press 1 to delete elemet from Set 1.\n2. Press 2 to delete element from Set 2.\n");
		            System.out.print("Enter your choice: ");
		            int subChoice8 = scanner.nextInt();
		            if(subChoice8 == 1) { 
				    System.out.print("Enter element to delete from Set 1: ");
				    int deleteElement = scanner.nextInt();
				    set1.delete(deleteElement);
				    System.out.println("Set 1 after deletion: " + set1);
		            } else if(subChoice8 == 2) { 
		            	System.out.print("Enter element to delete from Set 2: ");
				int deleteElement = scanner.nextInt();
				set2.delete(deleteElement);
				System.out.println("Set 2 after deletion: " + set2);
		            }else {
		            	System.out.println("Invalid Choice entered. Please enter a correct choice."); 
		            }
                    break;
                case 9:
                    Set unionSet = set1.union(set2);
                    System.out.println("Union of Set1 and Set2: " + unionSet);
                    break;
                case 10:
                    Set intersectionSet = set1.intersection(set2);
                    System.out.println("Intersection of Set1 and Set2: " + intersectionSet);
                    break;
                case 11:
                    Set differenceSet = set1.setdifference(set2);
                    System.out.println("Set Difference (Set1 - Set2): " + differenceSet);
                    break;
                case 12:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please choose a valid option.");
                    break;
        }
    }
  }
}
class Set extends Main{
    private static final int SIZE = 100;
    private int[] set;
    private int size;
    private int next;
    public Set() {
        this.set = new int[SIZE];
        this.size = SIZE;
        this.next = 0;
    }
    public Set(int[] arr) {
        this();
        for (int num : arr) {
            insert(num);
        }
    }
    public Set clone() {
        Set clonedSet = new Set();
        clonedSet.size = this.size;
        clonedSet.next = this.next;
        clonedSet.set = new int[this.size];
        System.arraycopy(this.set, 0, clonedSet.set, 0, this.next);
        return clonedSet;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < next; i++) {
            sb.append(set[i]);
            if (i < next - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
    public int cardinality() {
        return next;
    }
    public boolean isEmpty() {
        return next == 0;
    }
    public boolean member(int n) {
        for (int i = 0; i < next; i++) {
            if (set[i] == n) {
                return true;
            }
        }
        return false;
    }
    public boolean subset(Set T) {
        for (int i = 0; i < next; i++) {
            if (!T.member(set[i])) {
                return false;
            }
        }
        return true;
    }
    public boolean equal(Set T) {
        return this.subset(T) && T.subset(this);
    }
    public void insert(int k) {
        if (!member(k)) {
            if (next >= size) {
                resize();
            }
            set[next++] = k;
        }
    }
    public void delete(int k) {
        int index = -1;
        for (int i = 0; i < next; i++) {
            if (set[i] == k) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < next - 1; i++) {
                set[i] = set[i + 1];
            }
            next--;
        }
    }
    public Set union(Set T) {
        Set result = this.clone();
        for (int i = 0; i < T.next; i++) {
            result.insert(T.set[i]);
        }
        return result;
    }
    public Set intersection(Set T) {
        Set result = new Set();
        for (int i = 0; i < next; i++) {
            if (T.member(set[i])) {
                result.insert(set[i]);
            }
        }
        return result;
    }
    public Set setdifference(Set T) {
        Set result = new Set();
        for (int i = 0; i < next; i++) {
            if (!T.member(set[i])) {
                result.insert(set[i]);
            }
        }
        return result;
    }
    private void resize() {
        int newSize = size * 2;
        int[] newSet = new int[newSize];
        System.arraycopy(set, 0, newSet, 0, size);
        set = newSet;
        size = newSize;
    }
}
