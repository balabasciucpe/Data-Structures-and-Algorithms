package dumyClasses.Comparators;

import priorityQueues.PQ_diffImpl.DefaultComparator;

import java.util.Comparator;

public class GetDefaultComparator<E> {

    public static <E> Comparator<E> getDefaultComparator()
    {
        return new DefaultComparator<>();
    }
}
