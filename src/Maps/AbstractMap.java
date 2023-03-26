package Maps;



import priorityQueues.PQ_diffImpl.EntryInterface;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractMap<K, V> implements MapInterface<K, V> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    protected static class MapEntry<K, V> implements EntryInterface<K, V>
    {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
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

        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) &&
                    Objects.equals(value, mapEntry.value);
        }
    }

    private class KeyIteratorMap implements Iterator<K>
    {
        private Iterator<EntryInterface<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return entries.next().getKey();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("we don't do this thing here!");
        }
    }

    private class KeyIterable implements Iterable<K>
    {
        public Iterator<K> iterator()
        {
            return (Iterator<K>) new KeyIteratorMap();
        }
    }

    public Iterable<K> keySet()
    {
        return new KeyIterable();
    }

    private class ValueIterator implements Iterator<V>
    {
        private Iterator<EntryInterface<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public V next() {
            return entries.next().getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("we don't do that here!");
        }
    }

    private class ValueIterable implements Iterable<V>
    {
        public Iterator<V> iterator()
        {
            return new ValueIterator();
        }
    }

    public Iterable<V> values()
    {
        return new ValueIterable();
    }

}
