package dumyClasses;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class Person  implements Comparable<Person> {

    private String personName;
    private String personPrename;
    private int personAge;

    public Person(String personName, String personPrename, int personAge) {
        this.personName = personName;
        this.personPrename = personPrename;
        this.personAge = personAge;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonPrename() {
        return personPrename;
    }

    public int getPersonAge() {
        return personAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return personAge == person.personAge && Objects.equals(personName, person.personName) && Objects.equals(personPrename, person.personPrename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personName, personPrename, personAge);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personName='" + personName + '\'' +
                ", personPrename='" + personPrename + '\'' +
                ", personAge=" + personAge +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(o.getPersonAge(), this.personAge);
    }
}
