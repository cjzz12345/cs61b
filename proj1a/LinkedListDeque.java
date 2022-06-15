import org.junit.Test;

public class LinkedListDeque < T > {
    private int size;
    private Node sentinel;
    private class Node {
        private T item;
        private Node pre;
        private Node next;
         Node(T item) {
            this.item = item;
            pre = null;
            next = null;
        }

        Node() {
            pre = null;
            next = null;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }

//    public LinkedListDeque(T item) {
//        size = 1;
//        sentinel = new Node();
//        Node newNode = new Node(item);
//        sentinel.next = newNode;
//        sentinel.pre = newNode;
//        newNode.pre = sentinel;
//        newNode.next = sentinel;
//    }

    public void addFirst(T item) {
        size++;
        Node newNode = new Node(item);
        newNode.next = sentinel.next;
        newNode.pre = sentinel;
        sentinel.next = newNode;
        newNode.next.pre = newNode;
    }

    public void addLast(T item) {
        size++;
        Node newNode = new Node(item);
        newNode.pre = sentinel.pre;
        newNode.next = sentinel;
        sentinel.pre = newNode;
        newNode.pre.next = newNode;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.println(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    public T removeFirst() {
        if(size == 0){
            return null;
        }
        size--;
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        return item;
    }

    public T removeLast() {
        if(size == 0){
            return null;
        }
        size--;
        T item = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        return item;
    }

    public T get(int index) {
        Node ptr = sentinel.next;
        int i = 0;
        T item = null;
        while (ptr != sentinel) {
            if (i == index) {
                item = ptr.item;
                break;
            }
            ptr = ptr.next;
            i++;
        }
        return item;
    }

    public T getRecursive(int index) {
        return getRcursive(index,0,sentinel.next);
    }

    private T getRcursive(int index,int current,Node ptr) {
        if (current == index) {
            return ptr.item;
        }else if(ptr == sentinel){
            return null;
        }
        return getRcursive(index,current+1,ptr.next);
    }




}
