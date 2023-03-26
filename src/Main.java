import Maps.*;
import Maps.AbstractMap;
import SQDQ.Deque.DequeInterface;
import SQDQ.Deque.DoublyLinkedListDequeImpl;
import SQDQ.Queue.QueueInterface;
import SQDQ.Queue.SingleLinkedListQueueImpl;
import SQDQ.Stack.*;
import arrays.GameEntry;
import arrays.ScoreBoard;
import Maps.CustomHashMap;
import dumyClasses.ElementsToCollectionHelper;
import dumyClasses.Person;
import dumyClasses.User;
import dumyClasses.priorityQueueDummy.Student;
import dumyClasses.priorityQueueDummy.StudentId;
import lists.*;
import lists.ArrayList;

import priorityQueues.PQ_diffImpl.*;
import priorityQueues.positionalLists.*;

import sorting.insertionSort.InsertionSort;
import sorting.mergeSort.DivideAndConquerMergeSort;
import sorting.quickSort.QuickSortClass;
import sorting.selectionSort.SelectionSortClass;
import trees.PositionTreeInterface;
import trees.binaryTrees.LinkedBinaryTree;


import java.util.*;

public class Main<K, T> {
    public static void main(String[] args) {


        System.out.println("Array example...\n");
        GameEntry gameEntry1 = new GameEntry("A", 100);
        GameEntry gameEntry2 = new GameEntry("B", 101);

        ScoreBoard scoreBoard = new ScoreBoard(2);

        scoreBoard.addGame(gameEntry2);
        scoreBoard.addGame(gameEntry1);

        System.out.println(scoreBoard.toString());

        scoreBoard.remove(0);
        System.out.println(scoreBoard.toString());

        System.out.println("Example of insertion sort of small data  primitive sets ");
        char[] data = {'z', 'b', 's'};
        System.out.println("Before: " + Arrays.toString(data));
        ScoreBoard.insertionSort(data);
        System.out.println("After insertion sort: " + Arrays.toString(data));

        int[] dataa = {1, 5, 0, 2};
        System.out.println("\nBefore: " + Arrays.toString(dataa));
        ScoreBoard.insertionSortInt(dataa);
        System.out.println("After insertion sort: " + Arrays.toString(dataa));


        System.out.println("\nSingle Linked List: ");
        SingleLinkedList<Integer> singleeLinkedList = new SingleLinkedList<Integer>();
        System.out.println("Is empty: "+ singleeLinkedList.isEmpty());
        singleeLinkedList.addFirst(5);
        System.out.println("Get last element of a single linked list: " +singleeLinkedList.getLast());
        singleeLinkedList.addLast(2);
        System.out.println("Results are: " + singleeLinkedList.printResult());
        System.out.println("Get last element of a single linked list: " +singleeLinkedList.getLast());
        System.out.println("After removing first element: " + singleeLinkedList.removeFirst() + " we have element " + singleeLinkedList.getFirst() + " at first position");



        System.out.println("\nDoubly Linked List: ");
        DoublyLinkedList<String> doubleeLinkedList = new DoublyLinkedList<>();

        System.out.println("Is list empty "  + doubleeLinkedList.isEmpty());
        doubleeLinkedList.addFirst("Working,");
        doubleeLinkedList.addLast(" like a magic");
        System.out.println("Size is: " + doubleeLinkedList.getListSize());
        System.out.println("Elements of our doubly Linked List are: \n" + doubleeLinkedList.printResult());
        System.out.println("Adding first element to a double linked list of Strings: " + doubleeLinkedList.getFirst());
        doubleeLinkedList.removeFirst();
        System.out.println("After we remove first element, we have next element retained as a first element: " + doubleeLinkedList.getFirst());

        System.out.println("\nCircularly Linked List: ");
        CircularlyLinkedList<Integer> curcularlyyLinkedList = new CircularlyLinkedList<>();
        System.out.println("Adding elements...");
        curcularlyyLinkedList.addFirst(19);
        curcularlyyLinkedList.addLast(10);
        System.out.println("first element: "  + curcularlyyLinkedList.getFirst());
        curcularlyyLinkedList.rotate();
        System.out.println("After we rotate() elements - tail -> head: " + curcularlyyLinkedList.getFirst());

        System.out.println("\nStarting Array Lists: ");
        ListInterface<Person> personList = new ArrayList<>();
        populateList(personList);
        System.out.println("Person at index 0 is: " + personList.get(0));
        //we implement Iterable, so we can do for each loop
        //btw, we use a SuperType who didn t implement iterable, so even if we implement iterable in subclass, we cannot use
        //this instance in for each loop because it has a SuperType obj even if it have a subtype reference assigned
        //so i implemented iterable in ListInterface and removed from subclass
        for (Person person: personList) {
            System.out.println("Iterating for persons in list: " + person);
        }

        System.out.println("\n\nSTACKS");
        System.out.println("\nArray stack at work: ");
        StackInterface<Integer> integerStack = new ArrayStackImpl<>();
        integerStack.push(5);
        integerStack.push(4);
        System.out.println("Elements of the Stack are: " + integerStack);
        System.out.println("LIFO: return last element added : " + integerStack.top());
        System.out.println("Remove and return last element added: " + integerStack.pop());
        System.out.println("Now see what is the last element of the stack: " + integerStack.top());


        System.out.println("\nLinked List implementation of a STACK: ");
        StackInterface<Person> personStackLinkedList = new LinkedListStackImpl<>(new SingleLinkedList<>());
        personStackLinkedList.push(new Person("Test Stack Linked List Name", "Prename", 29));
        personStackLinkedList.push(new Person("A NAME", "B PRENAME", 33));
        System.out.println("What we are added in single: " + personStackLinkedList);


        System.out.println("STACK IS LIFO, so last element added, and removed after call this method is: " + personStackLinkedList.pop());
        System.out.println("Now we have in stack: " + personStackLinkedList.top());

        System.out.println("\n Queue - FIFO ");
        QueueInterface<Person> personQueue = new SingleLinkedListQueueImpl<>();
        personQueue.enqueue(new Person("Person Queue Name", "Person Queue Prename", 45));
        personQueue.enqueue(new Person("tana", "nana", 44));
        System.out.println("Now after removing first Person in the Queue, " + personQueue.dequeue() + " \nwe left with a " + personQueue.first() + " person.");

        System.out.println("\n Double Ended Queue - we can remove and add elements at the beginning or at the end of this structure of date");
        DequeInterface<Person> personDeque = new DoublyLinkedListDequeImpl<>();
        personDeque.addFirst(new Person("Person A from Deque", "A D", 23));
        personDeque.addFirst(new Person("Person B from Deque", "B D", 22));
        System.out.println("First Person in Deque is: " + personDeque.first());



        System.out.println("\n\nPriority Queue... like a Queue but with Priorities as Key, basically you can skip positions in Queue if you have highest priority");
        System.out.println("\nUnsorted Priority Queue implemented with a Single Linked List");
        //class cast exception, doesn t work with custom key data types, so we need to create a new comparator
        PriorityQueueInterface<StudentId, Student> priorityQueue = new UnsortedPriorityQueue<>(new StudentIdComparator());
        priorityQueue.insert(new StudentId(1), new Student("Petru"));
        priorityQueue.insert(new StudentId(3), new Student("Last"));
        priorityQueue.insert(new StudentId(0), new Student("First Student to be"));
        System.out.println("First Student in Priority Queue based on priority is: " + priorityQueue.min().getValue());

        System.out.println("\nSorted Priority Queue implemented with a Positional List");
        PriorityQueueInterface<StudentId, Student> sortedPriorityQueue = new SortedPriorityQueue<>(getComparator());
        sortedPriorityQueue.insert(new StudentId(2), new Student("Our Student instance with a priority queue of 2 added first in the Queue"));
        sortedPriorityQueue.insert(new StudentId(3), new Student("Student instance with a priority of 3"));
        sortedPriorityQueue.insert(new StudentId(0), new Student("Our first Student in the Queue based on priority"));
        System.out.println(sortedPriorityQueue.min().getValue());
        //Some of the advantages of using these or that are...



        System.out.println("\nNow, we implement a Queue using Heap concept" +
                "\n bassically, a heap is implemented as a Ordoned Tree");
        PriorityQueueInterface<StudentId, Student> heapPriorityQueue = new HeapPriorityQueue<>(new StudentIdComparator());
        heapPriorityQueue.insert(new StudentId(5), new Student("Petru"));
        heapPriorityQueue.insert(new StudentId(8), new Student("Bal"));
        heapPriorityQueue.insert(new StudentId(3), new Student("Test"));
      //if we don't use our Custom Comparator, is gonna test based based on Student Name
        System.out.println("Person added on heap based on priority is: " + heapPriorityQueue.min().getValue());


        System.out.println("\nPositional Lists - are lists that get a Position on a element, that position doesn't change at adding or removing elements"
        + "\nthis List use some concepts from Linked Lists, like a Position as a inner class with references to next, previous positions, E lement of this Position, bla bla bla");
        PositionalList<Person> positionalList = new LinkedPositionalList<>();
        positionalList.addFirst(new Person("Petru", "A", 23));
        positionalList.addFirst(new Person("Andrei", "B", 20));
        System.out.println("First Person in a Positional List, after we implement comparable is: " + positionalList.first().getElement());





        /* works but trivial like this
        PositionalList<Integer> pos = new LinkedPositionalList<>();

        pos.addFirst(2);
        pos.addFirst(4);
        pos.addFirst(3);
        System.out.println(pos.size()); //3
        System.out.println(pos.last().getElement()); //2
        System.out.println(pos.first().getElement()); //3


        //and no suport for generics in this method...
        SelectionSortForPositionalList.insertionSor(pos);
        System.out.println("Size is: " + pos.size()); //3

        System.out.println("First element in the Positional List is: " + pos.first().getElement()); //2
        System.out.println("Last element in the Positional List is: " + pos.last().getElement()); //4

        */

        //MAPS - KEY VALUE PAIR,
        //the UnsortedTableMap class on the whole is not very efficient.
        //On a map with n entries, each of the fundamental methods takes O(n) time in the
        //worst case because of the need to scan through the entire list when searching for an
        //existing entry
        System.out.println("\n MAPS - KEY VALUE PAIR, using Unsorted Table Map impl ");
        AbstractMap<Long, Person> unsortedTableMap = new UnsortedTableMap<>();
        populateMap(unsortedTableMap);
        System.out.println("\nSize of our unsorted table map is is: " + unsortedTableMap.size() + "\nand Persons added in Map are: ");
        for(Person person: unsortedTableMap.values())
            System.out.println(person.toString());
        System.out.println("Person with key 1 is: "  +unsortedTableMap.get(1L));



        System.out.println();
        AbstractHashMap<User, Person> mapp = new CustomHashMap<>(10);
        User user1 = new User("petrea.test");
        User user2 = new User("revo.best");
        Person person1 = new Person("Petrea", "Test", 30);
        Person person2 = new Person("Revo", "Best", 25);
        mapp.put(user1, person1);
        mapp.put(user2, person2);
        System.out.println("Our HashMap using separate chaining, give is result: " + mapp.get(user1)); // afișează "Person [personName=Petrea, personPrename=Test, personAge=30]"

        System.out.println("Now, Map impl using Linear Probing: ");
        AbstractHashMap<User, Person> linearProbingMap  = new ProbeHashMap<>(5);

        User userMap1 = new User("user1");
        User userMap2 = new User("user2");
        User userMap3 = new User("user3");
        Person personMap1 = new Person("Pop", "Alex", 25);
        Person personMap2 = new Person("Ion", "Maria", 30);
        Person personMap3 = new Person("Test", "testt", 40);
        linearProbingMap.put(userMap1, personMap1);
        linearProbingMap.put(userMap2, personMap2);
        linearProbingMap.put(userMap3, personMap3);



        // afișarea elementelor din map
        for (EntryInterface<User, Person> entry : linearProbingMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("Size is:" + linearProbingMap.size()); // 3

        // căutarea unei valori în map
        Person person = linearProbingMap.get(userMap1);
        System.out.println(person);

        // eliminarea unei valori din map
        linearProbingMap.remove(userMap2);

        // afișarea elementelor rămase în map
        for (EntryInterface<User, Person> entry : linearProbingMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


        System.out.println("\n TREEEES");
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        PositionTreeInterface<String> root = tree.addRoot("A");
        PositionTreeInterface<String> b = tree.addLeft("B", root);
        PositionTreeInterface<String> c = tree.addRight("C", root);
        PositionTreeInterface<String> d = tree.addLeft("D", b);
        PositionTreeInterface<String> e = tree.addRight("E", b);

        System.out.println("Tree size: " + tree.size()); // expect 5

        System.out.println("Root element: " + tree.root().getElement()); // expect "A"
        System.out.println("Left child of root: " + tree.left(root).getElement()); // expect "B"
        System.out.println("Right child of root: " + tree.right(root).getElement()); // expect "C"
        System.out.println("Left child of B: " + tree.left(b).getElement()); // expect "D"
        System.out.println("Right child of B: " + tree.right(b).getElement()); // expect "E"

        tree.set(d, "F");
        System.out.println("Element at node D after set(): " + tree.left(b).getElement()); // expect "F"




        System.out.println("\n" + "-".repeat(80));
        System.out.println("Sorting Algorithms"
        + "\n Insertion sort: ");

        Integer[] values = {5, 2, 1, 10};
        System.out.println("Using insertion sort for generic obj, elements before insertion sort are: " + Arrays.toString(values));

        InsertionSort.insertionSortForGenericElements(values);
        System.out.println(Arrays.toString(values));




        System.out.println("\n Insertion Sort: ");
        Long[] longNumbers = {3L, 1L, 10L, 15L, 2L, 1L};
        System.out.println(Arrays.toString(longNumbers));
        InsertionSort.insertionSortForGenericElements(longNumbers);
        System.out.println(Arrays.toString(longNumbers));




        Long[] longNumbers2 = {3L, 1L, 10L, 15L, 2L, 1L, -1L};
        System.out.println("\n selection sort: " + "\n" + Arrays.toString(longNumbers2));
        new SelectionSortClass<>(longNumbers2).selectionSort();
        System.out.println(Arrays.toString(longNumbers2));

        Random random = new Random();

        List<Integer> mergeSortList = ElementsToCollectionHelper.populate(random1 -> random1.nextInt(10), 10);



        System.out.println("\nMerge sort algorithm:  \nunsorted elements of a List before: ");
        System.out.println(mergeSortList);


        DivideAndConquerMergeSort.mergeSort(mergeSortList, new DefaultComparator<>());
        System.out.println("Sorted Elements of a List are: \n" + mergeSortList);



        System.out.println("\nSorting Queue using Merge Sort");
        // Queue merge sort
        QueueInterface<Integer> queueNumbers = (QueueInterface<Integer>) ElementsToCollectionHelper.populateGeneral(new SingleLinkedListQueueImpl<>(), randomQueue -> randomQueue.nextInt(10), 10);
        System.out.println("Size of our queue is: " + queueNumbers.size());
        System.out.println("Queue elements are: " + queueNumbers.toString());
        DivideAndConquerMergeSort.mergeSortQueue(queueNumbers, new DefaultComparator<>());
        System.out.println("Queue sorted elements are: " + queueNumbers.toString());


        System.out.println("\n");
        QueueInterface<Integer> queueInterfaceInteger = new SingleLinkedListQueueImpl<>();
        queueInterfaceInteger = (QueueInterface<Integer>) ElementsToCollectionHelper.populateGeneral(queueInterfaceInteger, random1 -> random.nextInt(10), 10);
        System.out.println("size of our Queue of Integers is " +  + queueInterfaceInteger.size());
        System.out.println("Unsorted elements are: \n" + queueInterfaceInteger.toString());
        DivideAndConquerMergeSort.mergeSortQueue(queueInterfaceInteger, new DefaultComparator<>());
        System.out.println("Sorted elements are: \n" + queueInterfaceInteger.toString());


        System.out.println("\n");
        QueueInterface<Integer> queueDouble = (QueueInterface<Integer>) ElementsToCollectionHelper.populateGeneral(new SingleLinkedListQueueImpl<>(), random1 -> random.nextInt(10), 11);
        System.out.println("Queue size: " + queueDouble.size());
      //  System.out.println("Queue elements are: " + Arrays.toString(ElementsToCollectionHelper.transform(queueDouble.toString(), queueDouble.size())));
        System.out.println("Queue elements are: \n" + queueDouble.toString());

        System.out.println("Queue sorted elements are: ");
        DivideAndConquerMergeSort.mergeSortQueue(queueDouble, new DefaultComparator<>());
        System.out.println(queueDouble);






        Integer[] numQuick = new Integer[10];
        populateArray(numQuick);




        System.out.println("\nNumbers are: \n"  + Arrays.toString(numQuick));

        QuickSortClass.quickSortArray(numQuick);
        System.out.println("After sorting using quickSort, array is: \n" + Arrays.toString(numQuick));



    }

    public static void populateArray(Object[] array)
    {
        Random random = new Random();

        for(int i = 0; i < array.length; i++)
        {
            array[i] = random.nextInt(10);
        }
    }

    public static void populateList(ListInterface<Person> list)
    {
        //imutable list, but we get a concurrentmodificationexception with imutables...

        //list = List.of()
        Person[] persons = {new Person("Petru", "Test", 15),
                new Person("Mina", "ABC", 20),
        new Person("DefaultName", "DefaultPrename", 23)};
        list.addAll(persons);
    }

    public static void populateMap(MapInterface<Long, Person> map)
    {

       map.put(1L, new Person("Petru", "Test", 25));
             map.put(2L, new Person("Testam", "Testul", 20));
              map.put(0L, new Person("Tatata", "hmmm", 30));

    }


    public static <E> void reverse(E[] data)
    {
        StackInterface<E> buffer = new ArrayStackImpl<E>();
        for (E datum : data)
            buffer.push(datum);

        for(int i = 0; i < data.length; i++)
            data[i] = buffer.pop();
    }



    public static Comparator<StudentId> getComparator()
    {
        return new StudentIdComparator();
    }

}