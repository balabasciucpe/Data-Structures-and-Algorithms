package Maps;

import priorityQueues.PQ_diffImpl.EntryInterface;

import java.util.ArrayList;

//liniar probing
public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {

    private int size = 0; //nr of entries in our table
    private MapEntry<K, V>[] table; //our hash table
    private MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null); // our entry is removed and we mark that way in our table

    public ProbeHashMap() {
        super();
    }

    public ProbeHashMap(int cap) {
        super(cap);
    }

    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    protected void createTable() {
        table = (MapEntry<K, V>[]) new MapEntry[capacity];
    }

    protected int probe(int h, int i) {
        return (h + i) % capacity; // index of our place in table using linear probing
    }

    protected void rehash() { // if we exceed the limit of our table... then we rehash everything
        MapEntry<K, V>[] oldTable = table;
        createTable();
        size = 0;
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null && entry != DEFUNCT) { // put entries from old table tu new table
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    protected int findSlot(int h, K key) { // find a place in our hash table, with that key or a blank place
        int avail = -1;
        int i = 0;
        int j = h;
        do {
            MapEntry<K, V> entry = table[j];
            if (entry == null) {
                if (avail == -1)
                    avail = j;
                break;
            }
            if (entry == DEFUNCT) {
                if (avail == -1)
                    avail = j;
            } else if (key.equals(entry.getKey()))
                return j; // we found that entry in our table
            j = probe(h, ++i);
        } while (j != h); // so we start at the beggining of h, and we move rightword, if we didn t find anything ->
        return -(avail + 1); // if we didn t find a blank place or a place with the specified key, return
    }

    protected V bucketGet(int h, K key) {
        int j = findSlot(h, key);
        if (j < 0)
            return null;
        return table[j].getValue();
    }

    protected V bucketPut(int h, K key, V value) {
        int j = findSlot(h, key);
        if (j >= 0) // if we found that place, set value...
            return table[j].setValue(value);
        table[-(j + 1)] = new MapEntry<>(key, value);
        numOfEntries++;
        size++;
        if (size > (capacity / 2))
            resize(2 * capacity - 1);
        return null;
    }

    protected V bucketRemove(int h, K key) {
        int j = findSlot(h, key);
        if (j < 0)
            return null;
        V answer = table[j].getValue();
        table[j] = DEFUNCT;
        size--;
        return answer;
    }

    public Iterable<EntryInterface<K, V>> entrySet() {
        ArrayList<EntryInterface<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (table[h] != null && table[h] != DEFUNCT)
                buffer.add(table[h]);
        }
        return buffer;
    }
}
