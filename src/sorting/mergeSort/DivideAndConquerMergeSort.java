package sorting.mergeSort;


import SQDQ.Queue.QueueInterface;
import SQDQ.Queue.SingleLinkedListQueueImpl;

import java.util.*;

//for a java array
public class DivideAndConquerMergeSort<V> {

    private V value;

    public DivideAndConquerMergeSort(V value) {
        this.value = value;
    }


    public V getValue() {
        return value;
    }


    public static <V> List<V> mergeSort(List<V> original, Comparator<V> comparator)
    {
    List<V> left = new ArrayList<>();
    List<V> right = new ArrayList<>();
    int center;
		if (original.size() == 1) {
        return original;
    } else {
        center = original.size() / 2;
        for (int i = 0; i < center; i++) {
            left.add(original.get(i));
        }
        for (int i = center; i < original.size(); i++) {
            right.add(original.get(i));
        }
        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);
        merge(left,right,original, comparator);
    }
		return original;
}

    private static <V> void merge(List<V>left, List<V>right, List<V>original, Comparator<V> comparator) {
        int leftIndex=0;
        int rightIndex=0;
        int originalIndex=0;

        while(leftIndex<left.size()&& rightIndex<right.size()) {

            if(comparator.compare(left.get(leftIndex), right.get(rightIndex)) < 0)
             {
                original.set(originalIndex, left.get(leftIndex));
                leftIndex++;
            }else {
                original.set(originalIndex, right.get(rightIndex));
                rightIndex++;
            }
            originalIndex++;
        }

        while(leftIndex<left.size()) {
            original.set(originalIndex, left.get(leftIndex));
            originalIndex++;
            leftIndex++;
        }
        while(rightIndex<right.size()) {
            original.set(originalIndex, right.get(rightIndex));
            originalIndex++;
            rightIndex++;
        }
    }






    //protected because maybe in a subclass we want to change the way of sorting
    protected static <V> void merge(V[] sub1, V[] sub2, V[] whole, Comparator<V> comparator)
    {
        int i = 0, j = 0;
        while(i + j < whole.length)
        {                                                   //if the first argument is smaller than second, then returns - integer, 0 for equals and positive integor for >
            if(j == sub2.length || (i < sub1.length && comparator.compare(sub1[i], sub2[j]) < 0))
                whole[i+j] = sub1[i++]; // copy ith element of S1 and increment i
            else
                whole[i+j] = sub2[j++];
        }
    }


    public static <V> void mergeSort(V[] whole, Comparator<V> comparator)
    {
        int length = whole.length;
        if(length < 2)
            return;
        int mid = length/2;
        V[] sub1 = Arrays.copyOfRange(whole, 0, mid);
        V[] sub2 = Arrays.copyOfRange(whole, mid, length);

        mergeSort(sub1, comparator);
        mergeSort(sub2, comparator);

        merge(sub1, sub2, whole, comparator);
    }





    //Linked List based impl of Sorting
    public static <V> void mergeSortQueue(QueueInterface<V> wholeQueue, Comparator<V> comparator)
    {
        int queueSize = wholeQueue.size();
        if(queueSize < 2)
            return;

        QueueInterface<V> subQueue1 = new SingleLinkedListQueueImpl<>();
        QueueInterface<V> subQueue2 = new SingleLinkedListQueueImpl<>();

        while(subQueue1.size() < queueSize / 2)
            subQueue1.enqueue(wholeQueue.dequeue());
        while(!wholeQueue.isEmpty())
            subQueue2.enqueue(wholeQueue.dequeue());

        mergeSortQueue(subQueue1, comparator);
        mergeSortQueue(subQueue2, comparator);

        mergeLinkedList(subQueue1, subQueue2, wholeQueue, comparator);
    }

    protected static <V> void mergeLinkedList(QueueInterface<V> subQueue1, QueueInterface<V> subQueue2, QueueInterface<V> wholeQueue, Comparator<V> comparator)
    {
        while(!subQueue1.isEmpty() && !subQueue2.isEmpty()) {
            if (comparator.compare(subQueue1.first(), subQueue2.first()) < 0)
                wholeQueue.enqueue(subQueue1.dequeue());
            else
                wholeQueue.enqueue(subQueue2.dequeue());
        }

        while(!subQueue1.isEmpty())
            wholeQueue.enqueue(subQueue1.dequeue());
        while(!subQueue2.isEmpty())
            wholeQueue.enqueue(subQueue2.dequeue());
    }
}
