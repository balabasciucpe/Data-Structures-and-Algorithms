package priorityQueues.PQ_diffImpl;

public interface PriorityQueueInterface<K, V> {

    EntryInterface<K, V> insert(K key, V value) throws IllegalArgumentException;

    EntryInterface<K, V> removeMin();
    EntryInterface<K, V> min();

    int size();
    boolean isEmpty();


}
