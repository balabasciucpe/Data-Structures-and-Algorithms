package trees;

import java.util.Iterator;

public interface TreeInterface<E> extends Iterable<E> {

    PositionTreeInterface<E> root();
    PositionTreeInterface<E> parent(PositionTreeInterface<E> positionToCheck) throws IllegalArgumentException;
    Iterable<PositionTreeInterface<E>> children(PositionTreeInterface<E> positionToCheck) throws IllegalArgumentException;
    int numOfChildren(PositionTreeInterface<E> positionToCheckForChildren) throws IllegalArgumentException;

    boolean isInternal(PositionTreeInterface<E> checkPosition) throws IllegalArgumentException;
    boolean isExternal(PositionTreeInterface<E> checkPosition) throws IllegalArgumentException;
    boolean isRoot(PositionTreeInterface<E> checkForRoot) throws IllegalArgumentException;

    int size();
    boolean isEmpty();
    Iterator<E> iterator();
    Iterable<PositionTreeInterface<E>> positions();
}
