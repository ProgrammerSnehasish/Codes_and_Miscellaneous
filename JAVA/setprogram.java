/**
 * Student First Name: Snehasish Das
 * Student Last Name: Das
 * Student BU Number:
 * Purpose: 
 */
class Set  {
    private static final int SIZE = 10; // default size of initial set            
    private int[] set;    // array referece to the set
    private int size;    // current size of the set
    private int next;    // index to next available slot in the set array
    public Set() {
        set = new int[SIZE];
        size = SIZE;
        next = 0;
    }
    public Set(int[] A) {
    	this();
        for (int num : A) {
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
    private void resize() {
        size *= 2;
        int[] temp = new int[size];
        for(int i = 0; i < set.length; ++i) {
            temp[i] = set[i];
        }
        set = temp;
    }
        
    public  String toString()  {
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
    
    public  boolean isEmpty() {
        if(next == 0)
            return true;   
        return false;
    }
    public boolean member(int k) {
        for (int i = 0; i < next; i++) {
            if (set[i] == k) {
                return true;
            }
        }
        return false;
    }    
    public  boolean subset(Set T) {
        for (int i = 0; i < next; i++) {
            if (!T.member(set[i])) {
                return false;
            }
        }
        return true;
    }
    
    public  boolean equal(Set T) {
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
}
