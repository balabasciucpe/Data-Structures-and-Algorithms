package trees.binaryTrees;


import SQDQ.Queue.QueueInterface;
import SQDQ.Queue.SingleLinkedListQueueImpl;
import trees.AbstractTreeClass;
import trees.PositionTreeInterface;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTreeClass<E> extends AbstractTreeClass<E> implements BinaryTreeInterface<E>
{

    @Override
    public Iterable<PositionTreeInterface<E>> children(PositionTreeInterface<E> positionToCheck) throws IllegalArgumentException {
        List<PositionTreeInterface<E>> arrayList = new ArrayList<>(2);
        if(left(positionToCheck) != null)
            arrayList.add(left(positionToCheck));
        if(right(positionToCheck) != null)
            arrayList.add(right(positionToCheck));

        return arrayList;

    }

    @Override
    public int numOfChildren(PositionTreeInterface<E> positionToCheckForChildren) throws IllegalArgumentException {
        int count = 0;
        if(left(positionToCheckForChildren) != null)
            count++;
        if(right(positionToCheckForChildren) != null)
            count++;
        return count;
    }

    @Override
    public PositionTreeInterface<E> sibling(PositionTreeInterface<E> positionToCheck) throws IllegalArgumentException {
        PositionTreeInterface<E> parent = parent(positionToCheck);
        if(parent == null)
            return null;
        if(parent == left(parent))
            return right(parent);
        else return left(parent);
    }


    public Iterable<PositionTreeInterface<E>> breadthFirst()
    {
        List<PositionTreeInterface<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
        {
            QueueInterface<PositionTreeInterface<E>> queue = new SingleLinkedListQueueImpl<>();
            queue.enqueue(root()); //add root as the first element of the queue
            while(!queue.isEmpty())
            {
                PositionTreeInterface<E> position = queue.dequeue();
                snapshot.add(position);
                for(PositionTreeInterface<E> childrenPosition : children(position))
                    queue.enqueue(childrenPosition);
            }
        }
        return snapshot;
    }

    private void inOrderSubTree(PositionTreeInterface<E> position, List<PositionTreeInterface<E>> snapshot)
    {
        if(left(position) != null)
        {
            inOrderSubTree(left(position), snapshot);
        }
        snapshot.add(position);
        if(right(position) != null)
        {
            inOrderSubTree(right(position), snapshot);
        }
    }

    public Iterable<PositionTreeInterface<E>> inOrder()
    {
        List<PositionTreeInterface<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
        {
            inOrderSubTree(root(), snapshot);
        }
        return snapshot;
    }

    public Iterable<PositionTreeInterface<E>> positions()
    {
        return inOrder();
    }
}
