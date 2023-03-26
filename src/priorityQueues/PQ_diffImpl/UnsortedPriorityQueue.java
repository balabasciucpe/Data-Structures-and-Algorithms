package priorityQueues.PQ_diffImpl;


import priorityQueues.positionalLists.LinkedPositionalList;
import priorityQueues.positionalLists.PositionInterface;

import java.util.Comparator;


public class  UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    private final LinkedPositionalList<EntryInterface<K, V>> positionalList = new LinkedPositionalList<>();

    public UnsortedPriorityQueue()
    {
        super(); //default comparator
    }

    public UnsortedPriorityQueue(Comparator<K> comparator)
    {
        super(comparator); //custom comparator
    }

    @Override
    public EntryInterface<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        EntryInterface<K, V> newestEntry = new EntryAPQ<>(key, value);
        positionalList.addLast(newestEntry);
        return newestEntry;
    }

    public PositionInterface<EntryInterface<K, V>> findMin()
    {
        PositionInterface<EntryInterface<K, V>> small = positionalList.first();
        for (PositionInterface<EntryInterface<K, V>> position :  positionalList.positions())
            if(compare(position.getElement(), small.getElement()) < 0)
                small = position;
        return small;
    }

    @Override
    public EntryInterface<K, V> removeMin() {
        if(isEmpty())
            return null;
        return positionalList.remove(findMin());
    }

    @Override
    public EntryInterface<K, V> min() {
        if (isEmpty())
            return null;
        return findMin().getElement();
    }

    @Override
    public int size() {
        return positionalList.size();
    }
}
