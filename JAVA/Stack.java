class Stack<T> {
    private Node<T> top;
    Stack() {
        top = null;
    }
    void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(top);
        top = newNode;
    }
    Node<T> pop() {
        if (top == null) {
            return null;
        }
        Node<T> temp = top;
        top = top.getNext();
        return temp;
    }
    Node<T> peek() {
        return top;
    }
    boolean isEmpty() {
        return top == null;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = top;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append("->");
            }
            current = current.getNext();
        }
        return sb.toString();
    }
}
