import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryHistoryManager {
    private int size;
    private Node first;
    private Node last;
    HashMap<Integer, Node> map = new HashMap<>();

    public int getSize() {
        return size;
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

/*    void addFirst(Task task) {
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

 */

    /*void remove(Node node) {
        if(node == getFirst()) {
            node.getNext().setPrev(null);
            setFirst(node.getNext());
            node.setNext(null);
            return;
        }
        else if(node == getLast()) {
            node.getPrev().setNext(null);
            setLast(node.getPrev());
            node.setPrev(null);
            return;
        }
        else if(size >= 3) {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            node.setNext(null);
            node.setPrev(null);
        }
    }*/

    void remove(Node node) {
        if (node == null) return;

        // единственный элемент
        if (node == first && node == last) {
            first = null;
            last = null;
            size = 0;
            return;
        }

        // первый
        if (node == first) {
            first = node.getNext();
            if (first != null) {
                first.setPrev(null);
            }
        }
        // последний
        else if (node == last) {
            last = node.getPrev();
            if (last != null) {
                last.setNext(null);
            }
        }
        // в середине
        else {
            Node prev = node.getPrev();
            Node next = node.getNext();
            if (prev != null) {
                prev.setNext(next);
            }
            if (next != null) {
                next.setPrev(prev);
            }
        }

        node.setNext(null);
        node.setPrev(null);
        size--;
    }

    void linkLast(Task task) {
        Node existing = map.remove(task.getId());
        if (existing != null) {
            remove(existing);
        }

        Node newNode = new Node(task);

        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setPrev(last);
            last.setNext(newNode);
            last = newNode;
        }

        map.put(task.getId(), newNode);
        size++;
    }


    ArrayList<Task> getTasks() {
        ArrayList<Task> history = new ArrayList<>();
        Node node = first;
        while (node != null) {
            history.add(node.getItem());
            node = node.getNext();
        }
        return history;
    }
    void printHistory() {
        int k = 1;
        System.out.println("История просмотра: ");
        for(Task task : getTasks()) {
            System.out.println(k + ") " + task.getName());
            k++;
        }
    }
}


