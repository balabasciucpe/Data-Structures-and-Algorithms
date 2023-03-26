package SQDQ.Deque;

public interface DequeInterface<E> {
    //double linked list impl or circular array

    int size();

    boolean isEmpty();

    E first();
    E last();

    void addFirst(E element);
    void addLast(E element);

    E removeFirst();
    E removeLast();


}
