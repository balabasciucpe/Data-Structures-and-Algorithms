package dumyClasses.priorityQueueDummy;

public class StudentId implements Comparable<StudentId>{

    private int studentId;

    public StudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "StudentId{" +
                "studentId=" + studentId +
                '}';
    }

    @Override
    public int compareTo(StudentId o) {
        return ((Comparable)o.getStudentId()).compareTo(this.getStudentId());
    }
}
