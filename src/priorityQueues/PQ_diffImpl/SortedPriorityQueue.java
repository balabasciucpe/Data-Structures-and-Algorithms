package priorityQueues.PQ_diffImpl;

import priorityQueues.positionalLists.LinkedPositionalList;
import priorityQueues.positionalLists.PositionInterface;

import java.util.Comparator;

public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V>{

    private final LinkedPositionalList<EntryInterface<K, V>> linkedPositionalList = new LinkedPositionalList<>();

    public SortedPriorityQueue(Comparator<K> comparator)
    {
        super(comparator);
    }

    public SortedPriorityQueue()
    {
        super();
    }


    @Override
    public EntryInterface<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        EntryInterface<K, V> newestEntry = new EntryAPQ<>(key, value);
        PositionInterface<EntryInterface<K, V>> walk = linkedPositionalList.last();
        while(walk != null && compare(newestEntry, walk.getElement()) < 0)
            walk = linkedPositionalList.before(walk);

        if(walk == null)
        {
            linkedPositionalList.addFirst(newestEntry);
        }
        else
            linkedPositionalList.addAfter(walk, newestEntry);

        return newestEntry;
    }

    @Override
    public EntryInterface<K, V> removeMin() {
        if(linkedPositionalList.isEmpty())
            return null;
        return linkedPositionalList.remove(linkedPositionalList.first());
    }

    @Override
    public EntryInterface<K, V> min() {
        if(linkedPositionalList.isEmpty())
            return null;
        return linkedPositionalList.first().getElement();
    }

    @Override
    public int size() {
        return linkedPositionalList.size();
    }
}
