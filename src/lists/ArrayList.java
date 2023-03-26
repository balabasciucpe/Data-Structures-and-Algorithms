package lists;

import Maps.AbstractMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements ListInterface<E>{

    private static final int CAPACITY = 10;
    private int listSize = 0;

    private E[] arrayList;

    public ArrayList(int capacity)
    {
        this.arrayList = (E[]) new Object[capacity];
    }

    public ArrayList()
    {
        this(CAPACITY);
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    private void checkIndex(int index, int size) throws IndexOutOfBoundsException
    {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("bad index, try again");
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndex(index, listSize);

        return arrayList[index];
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        checkIndex(index, listSize);

        E elementReturned = arrayList[index];
        arrayList[index] = element;
        return elementReturned;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException, IllegalStateException {
        checkIndex(index, listSize+1);

        if(listSize == arrayList.length)
        {
            //throw new IllegalStateException("we are full!");
            resize(2 * arrayList.length);
        }
        for(int i = listSize-1; i >= index; i--)
        //iti trebe loc liber la st, listSize, ca sa muti elementul mai in dreapta
        //altfel eroare index out of bounds ca nu gaseste loc
        {
            arrayList[i+1] = arrayList[i];
        }
        arrayList[index] = element;
        listSize++;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index, listSize);

        E elementReturned = arrayList[index];
        //il muti in stanga, oricum ultimul element n-o sa mai aiba nimic in el
        for (int no = index; no < listSize - 1; no++)
        {
            arrayList[index] = arrayList[index+1];
        }
        arrayList[listSize-1] = null;
        listSize--;
        return elementReturned;
    }

    protected void resize(int newCapacity)
    {
        E[] arrayDataNew = (E[]) new Object[newCapacity];
        for(int i = 0; i < listSize; i++)
        {
            arrayDataNew[i] = arrayList[i];
        }
        //ii atribuim si capacitatea si elementele
        arrayList = arrayDataNew;
    }

    @Override
    public void addAll(E[] addAllElements) {
        E[] arrayListNew = (E[]) Arrays.stream(addAllElements).toArray();
        int arrayListSize = addAllElements.length;

        //if we receive a null list size.... don't do nothing
        if(arrayListSize > 0) {
            E[] elementData = this.arrayList;
            final int lengthForThisInstance;
            //check if we copy all these alements in the existent arraylist, we need to resize?
            if (arrayListSize > elementData.length - (lengthForThisInstance = listSize)) {
                resize(lengthForThisInstance + listSize);
            }
            System.arraycopy(arrayListNew, 0, elementData, lengthForThisInstance, arrayListSize);
            listSize = lengthForThisInstance + arrayListSize;
        }
        //java.util.ArrayList.addAllMethod... sa vedem daca merge
        //ba, merge
    }

    @Override
    public ListInterface<E> getAll() {
        return null;
        //to do
    }

    public void add(E elements) {
        if(listSize == arrayList.length)
        {
            //throw new IllegalStateException("we are full!");
            resize(2 * arrayList.length);
        }
        this.arrayList[listSize] = elements;
        listSize++;
    }

    private class ArrayIterator implements Iterator<E>
    {
        private int ourCurrentIndex = 0;
        private boolean isElementRemovable = false;

        @Override
        public boolean hasNext() {
            return listSize > ourCurrentIndex;
        }

        @Override
        public E next() {
            if(ourCurrentIndex == arrayList.length)
                throw new NoSuchElementException("element no longer available");
            isElementRemovable = true;
            return arrayList[ourCurrentIndex++];
        }


        @Override
        public void remove() {
            if(!isElementRemovable)
                throw new IllegalStateException("we cna't delete that element");
            ArrayList.this.remove(ourCurrentIndex-1); // last index element
            ourCurrentIndex--;
            isElementRemovable = false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }
}
