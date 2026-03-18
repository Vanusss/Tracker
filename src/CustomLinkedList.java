import java.util.ArrayList;

public class CustomLinkedList {
    private int size;
    private Node first;
    private Node last;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    void addFirst(Task task) {
        Node newNode = new Node(task);
        if(getFirst() != null){
            newNode.setNext(getFirst());
            getFirst().setPrev(newNode);
            setFirst(newNode);
        }
        if (getFirst() == null) {
            setFirst(newNode);
        }
        if((getLast() == null) && (getSize() > 1)){
            Node check = getFirst();
            while(check.getNext() != null){
                check = check.getNext();
            }
            setLast(check);
        }
        size++;
    }

    void addLast(Task task) {
        Node newNode = new Node(task);
        if(getLast() != null){
            newNode.setPrev(getLast());
            getLast().setNext(newNode);
            setLast(newNode);
            if (getLast() == null) {
                setLast(newNode);
            }
        }
        if((getFirst() == null) && (getSize() > 1)){
            Node check = getLast();
            while (check.getPrev() != null){
                check = check.getPrev();
            }
            setFirst(check);
        }
        size++;
    }
    ArrayList<Task> getTasks() {
        Node node = getFirst();
        ArrayList<Task> history = new ArrayList<>();
        if((getFirst() != null)&&(getLast() != null)) {
            while (node != getLast()) {
                history.add(node.getItem());
                node = node.getNext();
            }
        }
        if(getSize() == 0) {
            return history;
        }
        if(getFirst() != null) {
            history.add(getFirst().getItem());
        }
        if(getLast() != null) {
            history.add(getLast().getItem());
        }
        return history;
    }
}
