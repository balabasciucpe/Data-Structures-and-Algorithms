package SQDQ.Deque;

import lists.DoublyLinkedList;

public class DoublyLinkedListDequeImpl<E> implements DequeInterface<E> {

    //we use delegate
    private final DoublyLinkedList<E> doublyLinkedList = new DoublyLinkedList<E>();

    @Override
    public int size() {
        return doublyLinkedList.getListSize();
    }

    @Override
    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }

    @Override
    public E first() {
        return doublyLinkedList.getFirst();
    }

    @Override
    public E last() {
        return doublyLinkedList.getLast();
    }

    @Override
    public void addFirst(E element) {
        doublyLinkedList.addFirst(element);
    }

    @Override
    public void addLast(E element) {
        doublyLinkedList.addLast(element);
    }

    @Override
    public E removeFirst() {
        return doublyLinkedList.removeFirst();
    }

    @Override
    public E removeLast() {
        return doublyLinkedList.removeLast();
    }
}
