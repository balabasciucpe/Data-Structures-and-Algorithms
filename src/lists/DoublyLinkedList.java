package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements Iterable<E>{

    private static class Node<E>
    {
        private E element;
        private Node<E> previousNode;
        private Node<E> nextNode;

        public Node(E element, Node<E> previousNode, Node<E> nextNode) {
            this.element = element;
            this.previousNode = previousNode;
            this.nextNode = nextNode;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPreviousNode() {
            return previousNode;
        }

        public void setPreviousNode(Node<E> previousNode) {
            this.previousNode = previousNode;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }

    private Node<E> header;
    private Node<E> trailer;

    private int listSize = 0;

    public DoublyLinkedList()
    {
        this.header = new Node<>(null, null, null);
        this.trailer = new Node<>(null, header, null);
        header.setNextNode(trailer);
    }

    public int getListSize() {
        return listSize;
    }

    public boolean isEmpty()
    {
        return listSize == 0;
    }

    public E getFirst()
    {
        if(isEmpty())
            return null;
        return header.getNextNode().getElement();
    }

    public E getLast()
    {
        if(isEmpty())
            return null;
        return trailer.getPreviousNode().getElement();
    }

    private void addBetween(E element, Node<E> previousNode, Node<E> nextNode)
    {
        Node<E> newestNode = new Node<>(element, previousNode, nextNode);
        previousNode.setNextNode(newestNode);
        nextNode.setPreviousNode(newestNode);
        listSize++;
    }

    private E removeNode(Node<E> node)
    {
        Node<E> successorNode = node.getNextNode();
        Node<E> predecessorNode = node.getPreviousNode();
        successorNode.setPreviousNode(predecessorNode);
        predecessorNode.setNextNode(successorNode);

        listSize--;
        return node.getElement();
    }

    public void addFirst(E element)
    {
        addBetween(element, header, header.getNextNode());
    }

    public void addLast(E element)
    {
        addBetween(element, trailer.getPreviousNode(), trailer);
    }

    public E removeFirst()
    {
        if(isEmpty())
            return null;
        return removeNode(header.getNextNode());
    }

    public E removeLast()
    {
        if(isEmpty())
            return null;
        return removeNode(trailer.getPreviousNode());
    }

    public String printResult()
    {
        StringBuilder result = new StringBuilder();
        for (E element : this)
            result.append(element).append(" ");
        return String.valueOf(result);
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    class DoublelinkedListNodeIterator implements Iterator<E>
    {
        Node<E> nextNode;

        DoublelinkedListNodeIterator()
        {
            this.nextNode = header;
        }

        @Override
        public boolean hasNext() {
            //we start after header
            return (nextNode.getNextNode() != null);
        }

        @Override
        public E next() {
            //we start at hedear to see if next node != null
            if((nextNode.getNextNode() == null) && (nextNode == trailer))
                throw new NoSuchElementException("END OF LINE");

            E currentE = nextNode.getNextNode().getElement();
            nextNode = nextNode.getNextNode();
            return currentE;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class DoublyLinkedListIterator implements Iterator<E>
    {
        final DoublelinkedListNodeIterator doublelinkedListNodeIterator = new DoublelinkedListNodeIterator();

        @Override
        public boolean hasNext() {
            return doublelinkedListNodeIterator.hasNext();
        }

        @Override
        public E next() {
            return doublelinkedListNodeIterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
