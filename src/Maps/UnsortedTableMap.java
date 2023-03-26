package Maps;

import lists.ArrayList;
import priorityQueues.PQ_diffImpl.EntryInterface;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {

    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    public UnsortedTableMap() {}

    private int findIndex(K key)
    {
        int n = table.size();
        for(int l = 0; l < n; l++)
        {
            if(table.get(l).getKey().equals(key))
                return l;
        }
        return -1; //we found nothing
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        int j = findIndex(key);
        if(j == -1)
            return null;
        return table.get(j).getValue();
    }

    @Override
    public V put(K key, V value) {
        int j = this.findIndex(key);
        if(j == -1) {
            table.add(new MapEntry<>(key, value));
            return value;
        }
        else {
            //        V oldValue = table.get(j).getValue();
      //  table.get(j).setValue(value);
         return table.get(j).setValue(value);
        }
    }

    @Override
    public V remove(K key) {
        int j = findIndex(key);
        int n = size();
        if(j == -1)
            return null;

        V answer = table.get(j).getValue();
        if(j != n-1)
            table.set(j, table.get(n-1));
        table.remove(n-1);
        return answer;
    }

    private class EntryIterator implements Iterator<EntryInterface<K, V>>
    {
        private int j = 0;

        @Override
        public boolean hasNext() {
            return j < table.size();
        }

        @Override
        public EntryInterface<K, V> next() {
            if(j == table.size())
                throw new NoSuchElementException("bad element");
            return table.get(j++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("we don't remove");
        }
    }

    private class EntryIterable implements Iterable<EntryInterface<K, V>> {

        @Override
        public Iterator<EntryInterface<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    public Iterable<EntryInterface<K, V>> entrySet()
    {
        return new EntryIterable();
    }

    @Override
    public String toString() {
        return "UnsortedTableMap{" +
                "table=" + table +
                '}';
    }
}
