package SQDQ.Queue;

import dumyClasses.MarkerInterfaceCollection;

//FIFO PRINCIPLE
public interface QueueInterface<E> extends MarkerInterfaceCollection<E> {

    int size();
    boolean isEmpty();

    E first();

    void enqueue(E element); //add element
    E dequeue(); //remove first element
}
