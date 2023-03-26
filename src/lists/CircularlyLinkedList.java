package lists;

public class CircularlyLinkedList<E> {

    //using single linked list
    private static class Node<E>
    {
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
    }

    private Node<E> tail = null;
    private int listSize = 0;

    public CircularlyLinkedList() {}

    public int getListSize() {
        return listSize;
    }

    public boolean isEmpty()
    {
        return listSize == 0;
    }

    public E last()
    {
        if(isEmpty())
            return null;
        return tail.getElement();
    }

    public E getFirst()
    {
        if(isEmpty())
            return null;
        return tail.getNextNode().getElement();
    }

    public void rotate()
    {
        if(tail != null)
            tail = tail.getNextNode();
    }

    public void addFirst(E element)
    {
        if(listSize == 0)
        {
            tail = new Node<>(element, null);
            tail.setNextNode(tail);
        }
        else
        {
            Node<E> newestNode = new Node<>(element, tail.getNextNode());
            tail.setNextNode(newestNode);
        }
        listSize++;
    }

    public void addLast(E element)
    {
        addFirst(element);
        //insert this new element at the start of the list, head
        //now this new element becomes new tail
        tail = tail.getNextNode();
    }

    public E removeFirst()
    {
        if(isEmpty())
            return null;

        //we need this new node head to set next node on tail, if listSize > 1
        Node<E> head = tail.getNextNode();
        if(head == tail)
            tail = null;
        else
            tail.setNextNode(head.getNextNode());
        listSize--;

        return head.getElement();
    }
}
