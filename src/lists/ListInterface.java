package lists;

public interface ListInterface<E> extends Iterable<E> {

    int size();
    boolean isEmpty();

    E get(int index) throws IndexOutOfBoundsException;
    E set(int index, E element) throws IndexOutOfBoundsException;
    void add(int index, E element) throws IndexOutOfBoundsException;

    E remove(int index) throws IndexOutOfBoundsException;

    void addAll(E[] addAllElements);

    ListInterface<E> getAll();
}
