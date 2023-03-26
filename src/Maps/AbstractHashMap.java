package Maps;


import priorityQueues.PQ_diffImpl.EntryInterface;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    protected int numOfEntries = 0;
    protected int capacity;
    private int prime;
    private long scale, shift; // for MAD

    public AbstractHashMap(int capacity, int prime) {
        this.capacity = capacity;
        this.prime = prime;
        Random random = new Random();
        scale = random.nextInt(prime - 1) + 1;
        shift = random.nextInt(prime);
        createTable();
    }

    public AbstractHashMap(int capacity)
    {
        this(capacity, 109345121);
    }

    public AbstractHashMap()
    {
        this(10); //default capacity
    }

    @Override
    public int size() {
        return numOfEntries;
    }

    @Override
    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }

    @Override
    public V put(K key, V value) {
         V answer = bucketPut(hashValue(key), key, value);
         if(numOfEntries > capacity /2) //load factor
             resize(2 * capacity-1);
         return answer;
    }

    @Override
    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

  /*  private int hashValue(K key) {
        int x =  (int) ((Math.abs(key.hashCode()* scale + shift) % prime) %capacity);
        System.out.println("hashcode is: " + x);
        return x;
    }
*/

    private int hashValue(K key) {
        int hash = Math.abs(key.hashCode());
        return hash % capacity;
    }

    void resize(int newCapacity)
    {
        ArrayList<EntryInterface<K, V>> buffer = new ArrayList<>(numOfEntries);
        for (EntryInterface<K, V> entry : entrySet())
            buffer.add(entry);

        capacity = newCapacity;
        createTable();
        numOfEntries = 0;
        for(EntryInterface<K, V> e : buffer)
            put(e.getKey(), e.getValue());
    }

    protected abstract void createTable();
    protected abstract V bucketGet(int h, K k);
    protected abstract V bucketRemove(int h, K k);
    protected abstract V bucketPut(int h, K k, V value);

}
