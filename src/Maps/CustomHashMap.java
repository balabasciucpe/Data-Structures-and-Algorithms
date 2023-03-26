package Maps;

import Maps.AbstractHashMap;
import Maps.UnsortedTableMap;
import priorityQueues.PQ_diffImpl.EntryInterface;

import java.util.ArrayList;

//separate chaining example
public class CustomHashMap<K, V> extends AbstractHashMap<K, V> {

    private UnsortedTableMap<K, V>[] table;

    public CustomHashMap(int capacity, int prime) {
        super(capacity, prime);
    }

    public CustomHashMap(int capacity) {
        super(capacity);
    }

    public CustomHashMap() {
        super();
    }

    @Override
    protected void createTable() {
        table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
    }

    @Override
    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) {
            return null;
        }
        return bucket.get(k);
    }

    @Override
    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) {
            return null;
        }
        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        capacity -= (oldSize - bucket.size());
        return answer;
    }

    @Override
    protected V bucketPut(int h, K k, V value) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null) {
            bucket = new UnsortedTableMap<>();
            table[h] = bucket;
        }
        return bucket.put(k, value);
    }

    @Override
    public Iterable<EntryInterface<K, V>> entrySet() {
        ArrayList<EntryInterface<K, V>> arrayList = new ArrayList<>();

        for(int k = 0; k < capacity; k++)
        {
            if(table[k] != null)
            {
                for(EntryInterface<K, V> entry : table[k].entrySet())
                {
                    arrayList.add(entry);
                }
            }
        }
        return arrayList;
    }
}



