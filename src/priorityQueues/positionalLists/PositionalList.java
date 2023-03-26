package priorityQueues.positionalLists;

public interface PositionalList<E> {

    int size();
    boolean isEmpty();

    PositionInterface<E> first();
    PositionInterface<E> last();

    PositionInterface<E> before(PositionInterface<E> position);
    PositionInterface<E> after(PositionInterface<E> position);

    PositionInterface<E> addFirst(E element);
    PositionInterface<E> addLast(E element);

    PositionInterface<E> addBefore(PositionInterface<E> position, E element) throws IllegalArgumentException;
    PositionInterface<E> addAfter(PositionInterface<E> position, E element) throws IllegalArgumentException;

    E set(PositionInterface<E> position, E element) throws IllegalArgumentException;
    E remove(PositionInterface<E> positionInterface) throws IllegalArgumentException;


}
