package SQDQ.Stack;

import java.util.Arrays;

public class ArrayStackImpl<E> implements StackInterface<E> {

    private static final int CAPACITY = 5;
    private E[] arrayStack;
    private int t = -1; // empty array

    public ArrayStackImpl(int capacity)
    {
        arrayStack = (E[]) new Object[capacity];
    }

    public ArrayStackImpl()
    {
        this(CAPACITY);
    }


    @Override
    public boolean isEmpty() {
       return t == -1;
    }

    @Override
    public int size() {
        return (t+1);
    }

    @Override
    public void push(E element)
    {
        if(size() > arrayStack.length)
            throw new IllegalArgumentException("can't push another element, WE FULL, WE CLOSE!");

        arrayStack[++t] = element; //adica la indicele urmator pune acest element
        //first increment t and then put that element value there

    }

    @Override
    public E top() {
        if(isEmpty())
            return null;
        return arrayStack[t];
    }

    @Override
    public E pop() {
        if(isEmpty())
            return null;
        E answer = arrayStack[t];
        arrayStack[t] = null;
        t--;

        return answer;
    }

    @Override
    public String toString() {
        return "ArrayStackImpl{" +
                "arrayStack=" + Arrays.toString(arrayStack) +
                '}';
    }
}
