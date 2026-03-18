public class Node{
    private Task item;
    private Node next;
    private Node prev;
    public Node(Task item){
        this.item = item;
    }

    public Task getItem() {
        return item;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setItem(Task item) {
        this.item = item;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
