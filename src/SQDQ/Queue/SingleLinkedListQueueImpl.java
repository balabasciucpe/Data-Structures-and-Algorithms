package SQDQ.Queue;

import lists.SingleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedListQueueImpl<E> implements QueueInterface<E> {

    private final SingleLinkedList<E> singleLinkedList = new SingleLinkedList<>();

    public SingleLinkedListQueueImpl() {
    }

    @Override
    public void enqueue(E element) {
        singleLinkedList.addLast(element);
    }

    @Override
    public E dequeue() {
        return singleLinkedList.removeFirst();
    }

    @Override
    public E first() {
        return singleLinkedList.getFirst();
    }

    @Override
    public int size() {
        return singleLinkedList.getListSize();
    }

    @Override
    public boolean isEmpty() {
        return singleLinkedList.isEmpty();
    }

    @Override
    public void addGeneral(E element) {
        this.enqueue(element);
        //honestly, i don t know if is really ok from the perspective of thrreading and memory and eficienty to call a method from another method
    }

    @Override
    public String toString() {
        return "{" + singleLinkedList + '}';
    }




}
