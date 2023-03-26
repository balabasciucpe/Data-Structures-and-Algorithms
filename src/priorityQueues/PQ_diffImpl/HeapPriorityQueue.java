package priorityQueues.PQ_diffImpl;



import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<EntryInterface<K, V>> heap = new ArrayList<>();

    public HeapPriorityQueue(Comparator<K> comparator) {
        super(comparator);
    }

    public HeapPriorityQueue()
    {
        super();
    }

    protected int parent(int j)
    {
        return (j-1) /2;
    }

    protected int left(int j)
    {
        return 2 *j +1;
    }


    protected int right(int j)
    {
        return 2 * j +2;
    }

    protected boolean hasLeft(int j)
    {
        return left(j) < heap.size();

    }

    protected boolean hasRight(int j)
    {
        return right(j) < heap.size();
    }

    protected void swap(int i, int j)
    {
        EntryInterface<K, V> temporar = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temporar);

        //swap entries
    }

    protected void upheap(int j)
    {
        while(j > 0)
        {
            int p = parent(j);

            if(compare(heap.get(j), heap.get(p)) >= 0)
                break;
            swap(j, p);
            j = p;
        }
    }

    protected void downheap(int j)
    {
        while(hasLeft(j))
        {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if(hasRight(j))
            {
                int rightIndex = right(j);
                if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                {
                    smallChildIndex = rightIndex;
                }
            }

            if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break;
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    @Override
    public EntryInterface<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        EntryInterface<K, V> response = new EntryAPQ<>(key, value);
        heap.add(response);
        upheap(heap.size()-1);
        return response;
    }

    @Override
    public EntryInterface<K, V> removeMin() {
        if(heap.isEmpty())
            return null;
        EntryInterface<K, V> response = heap.get(0);
        swap(0, heap.size()-1);
        heap.remove(heap.size()-1);
        downheap(0);
        return  response;

    }

    @Override
    public EntryInterface<K, V> min() {
        if(heap.isEmpty())
            return null;
        return heap.get(0);
    }

    @Override
    public int size() {
        return heap.size();
    }
}