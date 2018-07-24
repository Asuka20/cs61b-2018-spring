public class ArrayDeque<T> {

    private T[] items;
    private int capacity;
    private int size;
    private int nextFirst;
    private int nextLast;

    private int modCapacity(int i) {
        return (i % capacity);
    }

    private boolean isFull() {
        return (size == capacity);
    }

    private void resize(int cap) {
        T[] newItems = (T[]) new Object[cap];
        int index = modCapacity(nextFirst + 1);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[index];
            index = modCapacity(index + 1);
        }
        items = newItems;
        capacity = cap;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    public ArrayDeque() {
        items = (T[]) new Object[8];
        capacity = 8;
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }


    public int size() {
        return size;
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(size() * 2);
        }
            size += 1;
        items[nextFirst] = item;
        nextFirst = modCapacity(nextFirst - 1);
    }

    public void addLast(T item) {
        size += 1;
        items[nextLast] = item;
        nextLast = modCapacity(nextLast + 1);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        nextFirst = modCapacity(nextFirst + 1);
        T res = items[nextFirst];
        items[nextFirst] = null;
        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        nextLast = modCapacity(nextLast - 1);
        T res = items[nextLast];
        items[nextLast] = null;
        return res;
    }

    public T get(int index) {
        return items[modCapacity((nextFirst + 1 + index))];
    }

    public void printDeque() {
        int index = modCapacity(nextFirst + 1);
        for (int i = 0; i < size(); i++) {
            System.out.print(items[index]);
            System.out.print(' ');
            index = modCapacity(index + 1);
        }
    }


}
