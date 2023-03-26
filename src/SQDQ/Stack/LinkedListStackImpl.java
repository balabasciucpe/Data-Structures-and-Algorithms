package SQDQ.Stack;

import lists.SingleLinkedList;

public class LinkedListStackImpl<E> implements StackInterface<E> {

    private final SingleLinkedList<E> singleLinkedList;

    public LinkedListStackImpl(SingleLinkedList<E> singleLinkedList) {
        this.singleLinkedList = singleLinkedList;
    }

    @Override
    public boolean isEmpty() {
        return singleLinkedList.isEmpty();
    }

    @Override
    public int size() {
        return singleLinkedList.getListSize();
    }

    @Override
    public void push(E e) {
        singleLinkedList.addFirst(e);
    }

    @Override
    public E top() {
        return (E) singleLinkedList.getFirst();
    }

    @Override
    public E pop() {
        return (E) singleLinkedList.removeFirst();
    }

    @Override
    public String toString() {
        return "LinkedListStackImpl{" +
                "elements=" + singleLinkedList +
                '}';
    }

    public String printResults()
    {
       return singleLinkedList.printResult();
    }
}
