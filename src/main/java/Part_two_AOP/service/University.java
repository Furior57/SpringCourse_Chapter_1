package Part_two_AOP.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class University {
    private List<Student> students = new ArrayList<>();

    public void addStudents() {
        Student st1 = new Student("Art Shib", 4, 6.5);
        Student st2 = new Student("Michail Ivanov", 2, 7.4);
        Student st3 = new Student("Elena Sidorova", 1, 8.1);
        students.add(st1);
        students.add(st2);
        students.add(st3);
    }

    public List<Student> getStudents() {
        System.out.println("Information from getStudents():");
        System.out.println(students);
        return students;
    }

}
