package sorting.insertionSort;


import priorityQueues.positionalLists.PositionInterface;
import priorityQueues.positionalLists.PositionalList;

import java.util.Comparator;

/*
    as the performance...
    is good for smaller data sets  <= 10000 = 6 sec: https://www.youtube.com/watch?v=0lOnnd50cGI
    but from there is slower
    time complexity n^2 when we increse n(input) the time for performing this sorting increse exponentially
 */
public class InsertionSort<E> {

    public static void insertionSortCharArray(char[] dataArray)
    {
        int length = dataArray.length;

        //is sorted from pos 0, we start at pos 1 to check if smaller than pos[0]
        for(int index = 1; index < length; index++)
        {
            char currentChar = dataArray[index]; //and for each element in the array, starting at the pos 1, check inner loop
            //check elements before this element
            int j  = index; //for another loop, because we need to check currentChar with the elements before is to see if is greater or not
            //so wee need another loop for that, another index
            //check current number if is smaller for every element in the array
            //go at the beggining at the array and stop there j >0 or fine another element currentChar < dataArray[j-1];
            while(j > 0 && currentChar < dataArray[j-1]) // checking for non decresing order
            {
                dataArray[j] = dataArray[j-1]; // move to right
                j--; // move on on the left if any, to check
            }
            dataArray[j] = currentChar; // and set that value at i position
        }
    }

    //Operator '<' cannot be applied to generics 'E', 'E', dumb me
    //https://stackoverflow.com/questions/64507683/how-do-i-implement-an-insertion-sort-method-for-a-generic-arraylist
    public static <E extends Comparable<E>> void insertionSortForGenericElements(E[] arrayData)
    {
        int length = arrayData.length;
        for(int index = 1; index < length; index++)
        {
            E currentElement = arrayData[index];

            int j = index;
            while(j > 0 && (currentElement.compareTo(arrayData[j-1]) < 0))
            {
                arrayData[j] = arrayData[j-1];
                j--;
            }
            arrayData[j] = currentElement;
        }
    }



}
