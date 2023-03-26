package dumyClasses;


import java.util.*;
import java.util.function.Function;

public class ElementsToCollectionHelper<K, E> {





    public static <E> E[] transform(E elements, int size)
    {
        E[] ourArrayForPopulate = (E[]) new Object[size];
        for(int i = 0; i < size; i++)
        {
            ourArrayForPopulate[i] = elements;
        }
        return ourArrayForPopulate;
    }

    public static <E> void printResults(E[] elements)
    {
        //for can be replaced with enhanced for, but for elements, objects that do not extends comparable, doesn t work, so we stay parcat

        System.out.println(Arrays.toString(elements));
    }

    public static <E> void printArray(E[] ourArrayToPrint)
    {
        System.out.println(Arrays.toString(ourArrayToPrint));
    }

    public static Random getRandom()
    {
        return new Random();
    }


/* https://stackoverflow.com/questions/73527481/creating-an-array-of-generics-in-java-in-the-method-without-a-class-object
    public static <E> E[]  populateArray(Function<Random, E> creator, int size) {
        E[] ourArray = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
             ourArray[i] =creator.apply(getRandom());
        }
        return ourArray;
    }
*/



    //got exception
    // Exception in thread "main" java.lang.ClassCastException: class [Ljava.lang.Object; cannot be cast to class [Ljava.lang.Integer; ([Ljava.lang.Object; and [Ljava.lang.Integer; are in module java.base of loader 'bootstrap')
    //https://stackoverflow.com/questions/58036163/why-java-lang-classcastexception-ljava-lang-object-cannot-be-cast-to-error
    //using lists instead of arrays
    public static <E> List<E> populate(Function<Random, E> creator, int size)
    {
        List<E> list = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < size; i++)
        {
            list.add(creator.apply(random));
        }
       return list;
    }

    //oare merge cu collection marker interface?
    //hmm
    ////merge, dar cu casting, hmmm
    public static <E> MarkerInterfaceCollection<E> populateGeneral(MarkerInterfaceCollection<E> markerInterface, Function<Random, E> randomEFunction, int collectionSize)
    {
        if(markerInterface == null)
            return null;

        Random random = getRandom();
        for(int i = 0; i < collectionSize; i++)
        {
            markerInterface.addGeneral(randomEFunction.apply(random));
        }
        return markerInterface;
    }




    public static <E> void printResults(Collection<E> collection)
    {

        collection.forEach(System.out::println);
    }
}
