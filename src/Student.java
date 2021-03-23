import java.io.Serializable;

public class Student implements Serializable {
    Long id;
    String name;
    String surname;
    int age;

    public Student(Long id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return id+". "+name+" "+surname+". Age: "+age;
    }
}
