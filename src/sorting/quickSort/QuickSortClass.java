package sorting.quickSort;


import SQDQ.Queue.QueueInterface;
import SQDQ.Queue.SingleLinkedListQueueImpl;
import priorityQueues.PQ_diffImpl.DefaultComparator;

import java.util.Comparator;

public class QuickSortClass<T> {



    public static <T> void quickSortArray(T[] arrayData)
    {
        quickSortArray(arrayData, 0, arrayData.length  -1, new DefaultComparator<>());
    }

   //https://www.youtube.com/watch?v=h8eyY7dIiN4
    private static <T> void quickSortArray(T[] arrayData, int lowestIndex, int highestIndex, Comparator<T> comparator)
    {
        //check size
        int arrayDataLength = arrayData.length;

        //is already sorted if is have only one element or is null




        if(lowestIndex >= highestIndex)
            return;

        //chose a pivot
        //random pivot increase performance
     //   int randomPivotIndex = new Random().nextInt(1 + highestIndex - lowestIndex);
      //  T pivot = arrayData[highestIndex]; //arrayData[randomPivotIndex];
    //    System.out.println("pivot index is " + highestIndex);
        T pivot = arrayData[highestIndex];
        //then we swap pivot index with the last element of the array
        swapElements(arrayData, highestIndex, highestIndex);

        int leftPointer = divide(arrayData, lowestIndex, highestIndex, pivot, comparator);

        //call and sort recursivly
        //sort left part of the pivot, from pos 0 to position pivot -1;
        quickSortArray(arrayData, lowestIndex, leftPointer-1, comparator);
        //same here for the right part of the array
        quickSortArray(arrayData, leftPointer+1, highestIndex, comparator);


    }


    private static <T> int divide(T[] arrayData, int lowestIndex, int highestIndex, T pivot, Comparator<T> comparator)
    {
        int leftPointer = lowestIndex;
        int rightPointer = highestIndex -1;

        while(leftPointer < rightPointer)
        {
            while((comparator.compare(arrayData[leftPointer], pivot) <= 0) && leftPointer < rightPointer) //generics cannot < arrayData[leftPointer] <= pivot && leftPointer < rightPonter)
            {
                leftPointer++;
            }

            while((comparator.compare(arrayData[rightPointer], pivot) >= 0) && rightPointer > leftPointer)
            {
                rightPointer--;
            }

            //here the case is:
            //if our left or right pointer elements are larger or smaller than pivot, then swap elements at the other side
            swapElements(arrayData, leftPointer, rightPointer);
        }


        //when our pointers meet in the array, swap that value pointers point with the value of pivot
        //when pivot last index is smaller than pointers value, then swap elements
        if(comparator.compare(arrayData[leftPointer], arrayData[highestIndex]) > 0) {
            swapElements(arrayData, leftPointer, highestIndex); //pivot is highest index
        }
        else
        {
            leftPointer = highestIndex;
        }
        return leftPointer;
    }

    private static <T> void swapElements(T[] arrayData, int index1, int index2)
    {
        T tempElement = arrayData[index1];
        arrayData[index1] = arrayData[index2];
        arrayData[index2] = tempElement;
    }

    public static <T> void quickSortQueue(QueueInterface<T> queueData)
    {
        quickSortQueue(queueData, new DefaultComparator<T>());
    }

    public static <T> void quickSortQueue(QueueInterface<T> queueData, Comparator<T> comparator)
    {
        int queueSize = queueData.size();

        if(queueSize < 2)
            return; //queue is already sorted or is null;

        T pivot = queueData.first();
        QueueInterface<T> LessThanPivot = new SingleLinkedListQueueImpl<>();
        QueueInterface<T> EqualsPivot = new SingleLinkedListQueueImpl<>();
        QueueInterface<T> GreaterThanPivot = new SingleLinkedListQueueImpl<>();

        while(!queueData.isEmpty()) {

            System.out.println(" size: " + queueSize);
            T elementToCheck = queueData.dequeue();
            System.out.println(elementToCheck);
            System.out.println(pivot);
            int compared = comparator.compare(elementToCheck, pivot);
            System.out.println(compared); //-6


            if(compared < 0)
                LessThanPivot.enqueue(elementToCheck);
            else if(compared == 0)
                EqualsPivot.enqueue(elementToCheck);
            else
                GreaterThanPivot.enqueue(elementToCheck);
        }
            //conquer
            quickSortQueue(LessThanPivot, comparator); // sorting elements smaller than pivot recursiv
            quickSortQueue(GreaterThanPivot, comparator); //sorting elements greater than pivot

            while(!LessThanPivot.isEmpty() || !EqualsPivot.isEmpty() || !GreaterThanPivot.isEmpty())
            {
                queueData.enqueue(LessThanPivot.dequeue());
                queueData.enqueue(EqualsPivot.dequeue());
                queueData.enqueue(GreaterThanPivot.dequeue());
            }

    }
}
