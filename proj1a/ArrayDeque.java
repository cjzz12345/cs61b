import java.lang.reflect.Array;

public class ArrayDeque < T > {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 7;
        nextLast = 0;
    }

    public ArrayDeque(T item) {
        size = 1;
        items = (T[]) new Object[8];
        items[7] = item;
        nextFirst = 6;
        nextLast = 0;
    }

    private void increSize() {
        T[] newItems = (T[]) new Object[2*size];
        inNoramlOrder(newItems);
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size - 1;
    }

    private void decreSize() {
        T[] newItems = (T[]) new Object[size/2];
        inNoramlOrder(newItems);
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size - 1;
    }

    private void inNoramlOrder(T[] newItems) {
        if (nextLast > nextFirst && size < items.length) {
            System.arraycopy(items,0,newItems,0,size);
        }else {
            System.arraycopy(items,nextFirst+1,newItems,0,size-nextFirst-1);
            System.arraycopy(items,0,newItems,size-nextFirst-1,nextFirst+1);
        }
    }

    public void addFirst(T item) {
        if(size == items.length) {
            increSize();
        }
        size++;
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    public void addLast(T item) {
        if(size == items.length) {
            increSize();
        }
        size++;
        items[nextLast] =item;
        nextLast = (nextLast + 1 + items.length) % items.length;
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
        if (nextLast > nextFirst && size < items.length) {
            for(int i = nextFirst + 1;i < nextLast;i++) {
                System.out.println(items[i]+" ");
            }
        } else {
            for(int i = nextFirst + 1;i < items.length;i++) {
                System.out.println(items[i]+" ");
            }
            for(int i = 0;i < nextLast;i++) {
                System.out.println(items[i]+" ");
            }
        }
    }

    public T removeFirst() {
        nextFirst = (nextFirst + 1 + items.length) % items.length;
        T oldItem = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (items.length >= 16 && (double)size / items.length < 0.25) {
               decreSize();
        }
        return oldItem;
    }

    public T removeLast() {
        nextLast = (nextLast - 1 + items.length) % items.length;
        T oldItem = items[nextLast];
        items[nextLast] = null;
        size--;
        if (items.length >= 16 && (double)size / items.length < 0.25) {
            decreSize();
        }
        return oldItem;
    }

    public T get(int index) {
        return items[nextFirst+1+index];
    }
}
