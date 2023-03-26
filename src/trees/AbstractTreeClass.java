package trees;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTreeClass<E> implements TreeInterface<E> {

    @Override
    public boolean isInternal(PositionTreeInterface<E> checkPosition) throws IllegalArgumentException {
        return (numOfChildren(checkPosition) > 0);
    }

    @Override
    public boolean isExternal(PositionTreeInterface<E> checkPosition) throws IllegalArgumentException {
        return (numOfChildren(checkPosition) == 0);
    }

    @Override
    public boolean isRoot(PositionTreeInterface<E> checkForRoot) throws IllegalArgumentException {
        return checkForRoot == root();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    //O n
    //depth algorithm
    public int depth(PositionTreeInterface<E> position)
    {
        if(isRoot(position))
            return 0;
        return 1 + depth(parent(position));
    }

    //O n^2
    //height algoritm
    private int height()
    {
        int h = 0;
        for(PositionTreeInterface<E> position : positions())
        {
            if(isExternal(position))
                h = Math.max(h, depth(position));
        }
        return h;
    }

    //going recursiv
    //for that position node, we add all child nodes in a list snapshot
    private void preOrderTree(PositionTreeInterface<E> positionNode, List<PositionTreeInterface<E>> snapshot)
    {
        snapshot.add(positionNode);
        for(PositionTreeInterface<E> c : children(positionNode))
            preOrderTree(c, snapshot);
    }

    private void postOrderTree(PositionTreeInterface<E> positionNode, List<PositionTreeInterface<E>> snapshot)
    {
        for(PositionTreeInterface<E> position : children(positionNode))
            postOrderTree(positionNode, snapshot);
        snapshot.add(positionNode); // adding positions after exploring nodes subtrees
    }


    public Iterable<PositionTreeInterface<E>> preOrder()
    {
        List<PositionTreeInterface<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            preOrderTree(root(), snapshot); // we searching this list in pre order, beginning from the root
        return snapshot;
    }

    public Iterable<PositionTreeInterface<E>> postOrder() {
        List<PositionTreeInterface<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            postOrderTree(root(), snapshot);
        return snapshot;
    }

    //O n
    public int calHeight(PositionTreeInterface<E> positionToCheck)
    {
        int h = 0; // if position is external, we return 0
        for(PositionTreeInterface<E> position : children(positionToCheck))
        {
            h = Math.max(h, 1 + calHeight(position));
        }
        return h;
    }
}
