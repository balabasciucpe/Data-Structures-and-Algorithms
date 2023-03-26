package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SingleLinkedList<E> implements Iterable<E> {

    private static class Node<E> {
        private E element;
        private Node<E> nextNode;

        public Node(E element, Node<E> nextNode) {
            this.element = element;
            this.nextNode = nextNode;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return getElement().toString();
        }
    }


    //what is the size of this list?
    private int listSize = 0;

    private Node<E> head = null;
    private Node<E> tail = null;

    //we want to construct an instance of this class
    public SingleLinkedList() {
    }

    public int getListSize() {
        return listSize;
    }

    //test to see if we have elements inside or not
    public boolean isEmpty() {
        return listSize == 0;
    }

    public E getFirst() {
        if (isEmpty())
            return null;
        return head.getElement();
    }

    public E getLast() {
        if (isEmpty())
            return null;
        return tail.getElement();
    }

    public void addFirst(E element) {
        head = new Node<>(element, head);
        if (listSize == 0)
            tail = head;
        listSize++;
    }

    public void addLast(E element) {
        Node<E> node = new Node<>(element, null);
        if (isEmpty())
            head = node;
        else
            tail.setNextNode(node);
        tail = node;
        listSize++;
    }

    public E removeFirst() {
        if (isEmpty())
            return null;

        E objToReturn = head.getElement();
        head = head.getNextNode();
        listSize--;

        if (listSize == 0)
            tail = null;

        return objToReturn;
    }

    public String printResult()
    {
        StringBuilder result = new StringBuilder();
        for (E element : this)
            result.append(element).append(" ");
        return String.valueOf(result);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SingleLinkedList singleLinkedList = (SingleLinkedList) obj;
        if (listSize != singleLinkedList.listSize)
            return false;

        Node walkA = head; // for first loop
        Node walkB = singleLinkedList.head; // second loop
        //and for every node and element of these lists, check if these are equals
        while (walkA != null) {
            if (!walkA.getElement().equals(walkB.getElement()))
                return false;
            //increment to see for next node
            //and so on and so on
            walkA = walkA.getNextNode();
            walkB = walkB.getNextNode();
        }
        return true; //if they re equals
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<E> current = head;
        while (Objects.requireNonNull(current).getNextNode() != null) {
            result.append(current.getElement().toString());
            if (current.getNextNode() != null) {
                result.append(", ");
            }
            current = current.getNextNode();
        }
        return " " + result;
    }

    @Override
    public Iterator<E> iterator() {
        return new SinglelinkedListIterator();
    }

    class SingleLinkedListNodeIterator implements Iterator<E>
    {
        Node<E> nextNode;

        public SingleLinkedListNodeIterator() {
            this.nextNode = head;
        }



        @Override
        public boolean hasNext() {
            return (nextNode != null);
        }

        @Override
        public E next() {
            if(nextNode == null)
                throw new NoSuchElementException("End of line ");
            E current = nextNode.getElement();
            nextNode = nextNode.getNextNode();
            return current;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("We can t delete elements");
        }
    }

    class SinglelinkedListIterator implements Iterator<E>
    {
        final SingleLinkedListNodeIterator singleLinkedListNodeIterator = new SingleLinkedListNodeIterator();


        @Override
        public boolean hasNext() {
            return singleLinkedListNodeIterator.hasNext();
        }

        @Override
        public E next() {
            return singleLinkedListNodeIterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("We can t do that here");
        }
    }
}
