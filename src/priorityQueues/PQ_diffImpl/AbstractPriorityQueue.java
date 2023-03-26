package priorityQueues.PQ_diffImpl;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueueInterface<K, V> {

    protected static class EntryAPQ<K, V> implements EntryInterface<K, V> {
        private K key;
        private V value;

        public EntryAPQ(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private Comparator<K> comparator;

    protected AbstractPriorityQueue(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    protected AbstractPriorityQueue() {
        this(new DefaultComparator<>());
    }



    protected int compare(EntryInterface<K, V> key1, EntryInterface<K, V> key2)
    {
        return comparator.compare(key1.getKey(), key2.getKey());
    }

    protected boolean checkKey(K key)
    {
        try
        {
            return (comparator.compare(key, key) == 0);
        } catch (ClassCastException classCastException)
        {
            throw new IllegalArgumentException("Bad key you got there mate");
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


}
