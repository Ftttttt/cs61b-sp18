public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 4;
        this.nextLast = 5;
    }

    private void getBiggerSize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    private void getSmallerSize(int capacity) {
        T[] a = (T []) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = items[(i + nextFirst + 1) % items.length];
        }
        items = a;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        size = size + 1;
        nextFirst = ((nextFirst - 1) + items.length) % items.length;
        if (size == items.length) {
            getBiggerSize(size + 5);
            nextFirst = items.length - 1;
            nextLast = size;
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        size = size + 1;
        nextLast = (nextLast + 1) % items.length;
        if (size == items.length) {
            getBiggerSize(size + 5);
            nextFirst = items.length - 1;
            nextLast = size;
        }
    }

    public boolean isEmpty() {
        return  this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = (nextFirst + 1) % items.length; i != nextLast; i++, i = i % items.length) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size == items.length / 2) {
            getSmallerSize(items.length / 2 + 5);
            nextFirst = items.length - 1;
            nextLast = size;
        }
        T temp = items[(nextFirst + 1) % items.length];
        nextFirst = (nextFirst + 1) % items.length;
        size = size - 1;

        return temp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size == items.length / 2) {
            getSmallerSize(items.length / 2 + 5);
            nextFirst = items.length - 1;
            nextLast = size;
        }
        T temp = items[((nextFirst - 1) + items.length) % items.length];
        nextFirst = ((nextFirst - 1) + items.length) % items.length;
        size = size - 1;

        return temp;
    }

    public T get(int index) {
        int first = (nextFirst + 1) % items.length;
        if (index > size - 1) {
            return null;
        }
        return items[(first + index) % items.length];
    }
}
