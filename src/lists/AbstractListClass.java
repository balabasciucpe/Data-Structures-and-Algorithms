package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public abstract class AbstractListClass<E> implements Iterable<E> {

    protected int listSize = 0;

    protected Node<E> headNode = null;

    public int getListSize() {
        return listSize;
    }

    public boolean isEmpty()
    {
        return listSize == 0;
    }

    //protected for subclases
    protected static class Node<E>
    {
        private E element;
        private Node<E> nextNode;
        private Node<E> previousNode;

        //using two constructor, for cases like single and doubly linked list

        public Node(E element, Node<E> nextNode) {
            this.element = element;
            this.nextNode = nextNode;
        }

        public Node(E element, Node<E> nextNode, Node<E> previousNode) {
            this.element = element;
            this.nextNode = nextNode;
            this.previousNode = previousNode;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public Node<E> getPreviousNode() {
            return previousNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        public void setPreviousNode(Node<E> previousNode) {
            this.previousNode = previousNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                     '}';
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<E> current = headNode;
        while (Objects.requireNonNull(current).getNextNode() != null) {
            result.append(current.getElement().toString());
            if (current.getNextNode() != null) {
                result.append(", ");
            }
            current = current.getNextNode();
        }
        return " " + result;
    }

    public String printResult()
    {
        StringBuilder result = new StringBuilder(Integer.MAX_VALUE);
        for (E element : this)
            result.append(element).append(" ");
        return String.valueOf(result);
    }


    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    class LinkedListNodeIterator implements Iterator<E>
    {
        Node<E> nextNode;

        LinkedListNodeIterator()
        {
            this.nextNode = headNode;
        }

        @Override
        public boolean hasNext() {
            return (nextNode != null);
        }

        @Override
        public E next() {
            if(nextNode == null)
                throw new NoSuchElementException("We don t have that element in here");
            E currentNode = nextNode.getElement();
            nextNode = nextNode.getNextNode();
            return  currentNode;
        }

        @Override
        public void remove() {
            throw new NoSuchElementException("Bad element");
        }
    }

    class LinkedListIterator implements Iterator<E>
    {
        final LinkedListNodeIterator linkedListNodeIterator = new LinkedListNodeIterator();

        @Override
        public boolean hasNext() {
            return linkedListNodeIterator.hasNext();
        }

        @Override
        public E next() {
            return linkedListNodeIterator.next();
        }

        @Override
        public void remove() {
            throw new NoSuchElementException("bAD");
        }
    }
}
