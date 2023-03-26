package Maps;


import priorityQueues.PQ_diffImpl.EntryInterface;

public interface MapInterface<K, V> {

    int size();
    boolean isEmpty();

    V get(K key);
    V put(K key, V value);

    V remove(K key);

    Iterable<K> keySet();
    Iterable<V> values();

    Iterable<EntryInterface<K, V>> entrySet();
}
