package sorting.selectionSort;

public class SelectionSortClass<T extends Comparable<T>> {

    private final T[] ourArrayData;

    public SelectionSortClass(T[] ourArrayData) {
        this.ourArrayData = ourArrayData;
    }

    public  void selectionSort()
    {
        for(int index = 0; index < ourArrayData.length -1 ; index++)
        {
            int minIndex = index;
            for(int j = index +1; j < ourArrayData.length; j++)
            {
                if(ourArrayData[j].compareTo(ourArrayData[minIndex]) < 0)
                    minIndex = j;
            }
            if(minIndex != index)
            {
                T temporarVar = ourArrayData[index];
                ourArrayData[index] = ourArrayData[minIndex];
                ourArrayData[minIndex] = temporarVar;
            }
        }
    }
}
