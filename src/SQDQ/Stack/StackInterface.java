package SQDQ.Stack;

public interface StackInterface<E> {

    boolean isEmpty();

    int size();

    void push(E e);

    E top();

    E pop();
}
