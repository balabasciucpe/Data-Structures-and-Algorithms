package trees.binaryTrees;

import trees.PositionTreeInterface;

import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTreeClass<E> {

    protected static class NodeTree<E> implements PositionTreeInterface<E>
    {
        private E element;
        private NodeTree<E> parent;
        private NodeTree<E> leftNode;
        private NodeTree<E> rightNode;

        public NodeTree(E element, NodeTree<E> parent, NodeTree<E> leftNode, NodeTree<E> rightNode) {
            this.element = element;
            this.parent = parent;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public NodeTree<E> getParent() {
            return parent;
        }

        public void setParent(NodeTree<E> parent) {
            this.parent = parent;
        }

        public NodeTree<E> getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(NodeTree<E> leftNode) {
            this.leftNode = leftNode;
        }

        public NodeTree<E> getRightNode() {
            return rightNode;
        }

        public void setRightNode(NodeTree<E> rightNode) {
            this.rightNode = rightNode;
        }
        }

    protected NodeTree<E> createNode(E element, NodeTree<E> parentNode, NodeTree<E> leftNode, NodeTree<E> rightNode)
    {
        return new NodeTree<>(element, parentNode, leftNode, rightNode);
    }

    protected NodeTree<E> root = null;
    private int nodeSize = 0;

    public LinkedBinaryTree() {}

    protected NodeTree<E> validatePosition(PositionTreeInterface<E> position) throws IllegalArgumentException
    {
        if(!(position instanceof NodeTree))
            throw new IllegalArgumentException("bad position you got there meit");
        NodeTree<E> nodeTree = (NodeTree<E>) position;
        if(nodeTree.getParent() == nodeTree)
            throw new IllegalArgumentException("this position got promoted, no longer availlable");

        return nodeTree;
    }

    public int size() {
        return nodeSize;
    }

    public PositionTreeInterface<E> root()
    {
        return root;
    }

    @Override
    public PositionTreeInterface<E> left(PositionTreeInterface<E> positionToCheck) throws IllegalArgumentException {
        NodeTree<E> nodeTree = validatePosition(positionToCheck);
        return nodeTree.getLeftNode();
    }

    @Override
    public PositionTreeInterface<E> right(PositionTreeInterface<E> positionToCheck) throws IllegalArgumentException {
        NodeTree<E> nodeTree = validatePosition(positionToCheck);
        return nodeTree.getRightNode();
    }

    @Override
    public PositionTreeInterface<E> parent(PositionTreeInterface<E> positionToCheck) throws IllegalArgumentException {
        NodeTree<E> nodeTree = validatePosition(positionToCheck);
        return nodeTree.getParent();
    }

    public PositionTreeInterface<E> addRoot(E elementAdded) throws IllegalArgumentException
    {
        if(!isEmpty())
            throw new IllegalArgumentException("Tree is not empty, we have a root for now");
        root = createNode(elementAdded, null, null, null);
        nodeSize = 1;
        return root;
    }

    public PositionTreeInterface<E> addLeft(E element, PositionTreeInterface<E> position) throws IllegalArgumentException
    {
        NodeTree<E> parent = validatePosition(position);
        if(parent.getLeftNode() != null)
            throw new IllegalArgumentException("We have a left node already!");
        NodeTree<E> childOfLeftNode = new NodeTree<>(element, null, null, null);
        parent.setLeftNode(childOfLeftNode);
        nodeSize++;
        return childOfLeftNode;
    }

    public PositionTreeInterface<E> addRight(E element, PositionTreeInterface<E> position) throws IllegalArgumentException
    {
        NodeTree<E> parentRight = validatePosition(position);
        if(parentRight.getRightNode() != null)
            throw new IllegalArgumentException("we have a child right node already");
        NodeTree<E> rightChildNode = new NodeTree<E>(element, null, null, null);
        parentRight.setRightNode(rightChildNode);
        nodeSize++;
        return rightChildNode;
    }

    public E set(PositionTreeInterface<E> positionTree, E element)
    {
        NodeTree<E> node = validatePosition(positionTree);
        E temporar = node.getElement();
        node.setElement(element);
        return temporar;
    }


    public void attach(PositionTreeInterface<E> position, LinkedBinaryTree<E> tree1, LinkedBinaryTree<E> tree2) throws IllegalArgumentException
    {
        NodeTree<E> node = validatePosition(position);
        if(!isInternal(position)) //if does not have children
            throw new IllegalArgumentException("position must be a leaf");
        nodeSize += tree1.size() + tree2.size();

        if(!tree1.isEmpty()) //attach elements at the left side of the root
        {
            tree1.root.setParent(node);
            node.setLeftNode(tree1.root);
            tree1.root = null;
            tree1.nodeSize = 0;
        }

        if(!tree2.isEmpty())
        {
            tree2.root.setParent(node);
            node.setRightNode(tree2.root);
            tree2.root = null;
            tree2.nodeSize = 0;
        }
    }

    public E remove(PositionTreeInterface<E> position) throws IllegalArgumentException
    {
        NodeTree<E> treeNode = validatePosition(position);
        if(numOfChildren(position) == 2)
            throw new IllegalArgumentException("this node have 2 children"); //so we cannot replace one children with the removed node
        //if we have only one children in that node ->
        NodeTree<E> child = (treeNode.getLeftNode() != null ? treeNode.getLeftNode() : treeNode.getRightNode()); // what child do we have? left size or right size?
        if(child != null) //if we have one
            child.setParent(treeNode.getParent()); // move upwards
        if(treeNode == root) // child become root or viceversa
            root = child;
        else // to see where we place child
        {
            NodeTree<E> parent = treeNode.getParent(); //upwards
            if(treeNode == parent.getLeftNode()) // if this treeNode is on left node, place on the left side of the new parent Node
                parent.setLeftNode(child);
            else
                parent.setRightNode(child); // or right
        }

        nodeSize--;
        E elementReturned = treeNode.getElement();
        //helping gc
        treeNode.setRightNode(null);
        treeNode.setLeftNode(null);
        treeNode.setParent(null);
        treeNode.setElement(null);

        return elementReturned;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }



    @Override
    public Iterable<PositionTreeInterface<E>> positions() {
        return preOrder();
    }

    private class ElementIterator implements Iterator<E>
    {
        Iterator<PositionTreeInterface<E>> posIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return (E) posIterator.next();
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }

    @Override
    public PositionTreeInterface<E> addLeft(PositionTreeInterface<E> position, E element) throws IllegalArgumentException {
        return null;
    }

    @Override
    public PositionTreeInterface<E> addRight(PositionTreeInterface<E> position, E element) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void attach(PositionTreeInterface<E> position) {

    }
}
