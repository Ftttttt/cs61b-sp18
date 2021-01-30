public class LinkedListDeque<T> {
    private class Node {
        private  Node prev;
        private T item;
        private Node next;

        private Node(T i, Node p, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentF;
    private int size;
    private Node sentB;

    public LinkedListDeque() {
        size = 0;
        sentF = new Node(null, null, null);
        sentB = new Node(null, null, null);
        sentF.next = sentB;
        sentB.prev = sentF;
    }

    //方法
    public void addFirst(T item) {
        size += 1;
        sentF.next = new Node(item, sentF, sentF.next);
        sentF.next.next.prev = sentF.next;
    }

    public void addLast(T item) {
        size += 1;
        sentB.prev = new Node(item, sentB.prev, sentB);
        sentB.prev.prev.next = sentB.prev;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ptr = sentF.next;
        while (ptr.next != null) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println(ptr.item);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node temp = sentF.next;
        sentF.next.next.prev = sentF;
        sentF.next = sentF.next.next;
        return temp.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node temp = sentB.prev;
        sentB.prev.prev.next = sentB;
        sentB.prev = sentB.prev.prev;
        return temp.item;
    }

    public T get(int index) {
        Node temp = sentF.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    private T getRecursiveHelper(Node currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(currentNode.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentF.next, index);
    }
}
