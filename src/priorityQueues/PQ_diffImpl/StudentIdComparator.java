package priorityQueues.PQ_diffImpl;

import dumyClasses.priorityQueueDummy.StudentId;

import java.util.Comparator;

public class StudentIdComparator implements Comparator<StudentId> {

    @Override
    public int compare(StudentId o1, StudentId o2) {
        if(o1.getStudentId() < o2.getStudentId())
            return -1;
        else if(o1.getStudentId() == o2.getStudentId())
            return 0;
        return 1;
    }
}
