package priorityQueues.positionalLists;


import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedPositionalList<E> implements PositionalList<E> {

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public LinkedPositionalList() {
        header = new Node(null, null, null);
        trailer = new Node(null, header, null);
        header.setNext(trailer);
    }

    @Override
    public PositionInterface<E> first() {
        return position(header.getNext());
    }

    @Override
    public PositionInterface<E> last() {
        return position(trailer.getPrevious());
    }

    @Override
    public PositionInterface<E> before(PositionInterface<E> p) throws IllegalStateException {
        Node<E> node = validate(p);
        return position(node.getPrevious());
    }

    @Override
    public PositionInterface<E> after(PositionInterface<E> p) throws IllegalStateException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }



    private PositionInterface<E> addBetween(E element, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(element, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrevious(newest);
        size++;
        return newest;
    }

    @Override
    public PositionInterface<E> addFirst(E element) {
        return this.addBetween(element, header, header.getNext());
    }

    @Override
    public PositionInterface<E> addLast(E element) {
        return this.addBetween(element, trailer.getPrevious(), trailer);
    }

    @Override
    public PositionInterface<E> addBefore(PositionInterface<E> position, E element) throws IllegalStateException {
        Node<E> node = validate(position);
        return this.addBetween(element, node.getPrevious(), node);
    }

    @Override
    public PositionInterface<E> addAfter(PositionInterface<E> position, E element) throws IllegalStateException {
        Node<E> node = validate(position);
        return this.addBetween(element, node, node.getNext());
    }

    @Override
    public E set(PositionInterface<E> position, E element) throws IllegalStateException {
        Node<E> node = validate(position);
        E removed = node.getElement();
        node.setElement(element);
        return removed;
    }

    @Override
    public E remove(PositionInterface<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        Node<E> predecessorNode = node.getPrevious();
        Node<E> sucessorNode = node.getNext();
        predecessorNode.setNext(sucessorNode);
        sucessorNode.setPrevious(predecessorNode);

        size--;

        E element = node.getElement();

        // help with garbage collection
        node.setElement(null);
        node.setPrevious(null);
        node.setNext(null);

        return element;
    }



    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }


    private Node<E> validate(PositionInterface<E> position) throws IllegalStateException {
        if (!(position instanceof Node)) {
            throw new IllegalStateException("Invalid position");
        }

        Node<E> node = (Node<E>) position;

        if (node.getNext() == null) {       // convention for defunct node
            throw new IllegalStateException("'position' is no longer in the list");
        }

        return node;
    }


    private PositionInterface<E> position(Node<E> node) {
        if (node == header || node == trailer) {
            return null; // do not expose user to the sentinels
        }

        return node;
    }


    public Iterable<PositionInterface<E>> positions() {
        return new PositionIterable();
    }


    public Iterator<E> iterator() {
        return new ElementIterator();
    }


    private static class Node<E> implements PositionInterface<E> {

        private E element;
        private Node<E> previous;
        private Node<E> next;

        public Node(E element, Node<E> previous, Node<E> next) {
            this.element = element;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if (next == null) {
                throw new IllegalStateException("Position no longer valid");
            }

            return this.element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private class PositionIterator implements Iterator<PositionInterface<E>> {

        private PositionInterface<E> cursor = first();     // position of the next element to report
        private PositionInterface<E> recent = null;        // position of last reported element

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public PositionInterface<E> next() {
            if (cursor == null) {
                throw new NoSuchElementException("Nothing to get");
            }

            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        @Override
        public void remove() {
            if (recent == null) {
                throw new IllegalStateException("Nothing to remove");
            }

            LinkedPositionalList.this.remove(recent);
            recent = null;          // do not allow remove again until next is called
        }
    }

    private class PositionIterable implements Iterable<PositionInterface<E>> {

        @Override
        public Iterator<PositionInterface<E>> iterator() {
            return new PositionIterator();
        }
    }

    private class ElementIterator implements Iterator<E> {

        Iterator<PositionInterface<E>> positionIterator = new PositionIterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }

        @Override
        public void remove() {
            positionIterator.remove();
        }
    }

    //make it general in sorting package
    private static void insertSort(PositionalList<Integer> list) {
        PositionInterface<Integer> marker = list.first();

        while (marker != list.last()) {
            PositionInterface<Integer> pivot = list.after(marker);

            if (marker.getElement() < pivot.getElement()) {
                marker = pivot;
            } else {
                PositionInterface<Integer> walk = marker;

                while (walk != list.first() && list.before(walk).getElement() > pivot.getElement()) {
                    walk = list.before(walk);
                }

                list.remove(pivot);
                list.addBefore(walk, pivot.getElement());
            }
        }
    }

}