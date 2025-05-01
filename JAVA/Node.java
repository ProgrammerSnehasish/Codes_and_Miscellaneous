class Node<T> {
    private T data;
    private Node <T> next;
    Node(T data) {
        this.data = data;
        this.next = null;
    }
    T getData() {
        return data;
    }
    void setNext(Node<T> next) {
        this.next = next;
    }
    Node<T> getNext() {
        return next;
    }
}
