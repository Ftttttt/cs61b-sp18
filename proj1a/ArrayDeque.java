public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int sentF;
    private int sentB;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        sentF = -1;
        sentB = -1;
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addFirst(T item) {
        size += 1;
        if (size == items.length) {
            resize(size + 1);
        }
        if (sentF == -1) {
            sentF = 0;
            sentB = 0;
        }
        if (sentF == 0) {
            sentF = items.length - 1; 
        } else {
            sentF -= 1;
        }
        items[sentF] = item;
    }

    public void addLast(T item) {
        size += 1;
        if (size == items.length) {
            resize(size + 1);
        }
        if (sentF == -1) {
            sentF = 0;
            sentB = 0;
        }
        if (sentB == items.length - 1) {
            sentB = 0; 
        } else {
            sentB += 1;
        }
        items[sentB] = item;
    }

    public boolean isEmpty() {
        return sentF == -1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (sentF == -1) {
            return;
        }
        if (sentF <= sentB) {
            for (int i = sentF; i < sentB; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println(items[sentB]);
        } else {
            for (int i = sentF; i <= items.length - 1; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < sentB; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println(items[sentB]);
        }
    }

    public T removeFirst() {
        if (sentF == -1) {
            return null;
        }
        T temp = items[sentF];
        size -= 1;
        if (size == 0) {
            sentF = -1;
            sentB = -1;
        }
        //sentF = (sentF + 1) % items.length;
        if (sentF == items.length - 1) {
            sentF = 0;
        } else {
            sentF = sentF - 1;
        }

        return temp;
    }

    public T removeLast() {
        if (sentF == -1) {
            return null;
        }
        T temp = items[sentB];
        size -= 1;
        if (size == 0) {
            sentF = -1;
            sentB = -1;
        }
        if (sentB == 0) {
            sentB = items.length - 1;
        } else {
            sentB = sentB - 1;
        }
        
        return temp;
    }

    public T get(int index) {
        if (sentF == -1) {
            return null;
        }
        if (index > size) {
            return null;
        } else {
            int num = (sentF + index + items.length - 1) % items.length;
            return items[num];
        }
    }
}
