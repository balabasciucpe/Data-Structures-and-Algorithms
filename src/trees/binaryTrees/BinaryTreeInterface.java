package trees.binaryTrees;

import trees.PositionTreeInterface;
import trees.TreeInterface;

public interface BinaryTreeInterface<E> extends TreeInterface<E> {

    PositionTreeInterface<E> left(PositionTreeInterface<E> positionToCheck) throws IllegalArgumentException;
    PositionTreeInterface<E> right(PositionTreeInterface<E> positionToCheck) throws IllegalArgumentException;
    PositionTreeInterface<E> sibling(PositionTreeInterface<E> positionToCheck) throws IllegalArgumentException;

    //update methods
    PositionTreeInterface<E> addRoot(E element) throws IllegalArgumentException;
    PositionTreeInterface<E> addLeft(PositionTreeInterface<E> position, E element) throws IllegalArgumentException;
    PositionTreeInterface<E> addRight(PositionTreeInterface<E> position, E element) throws IllegalArgumentException;

    E set(PositionTreeInterface<E> position, E element);
    void attach(PositionTreeInterface<E> position);

    E remove(PositionTreeInterface<E> position) throws IllegalArgumentException;



}
