package Part_two_AOP.lessons;

import Part_two_AOP.config.MyAOPConfig;
import Part_two_AOP.service.Student;
import Part_two_AOP.service.University;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Lesson_18_AfterReturningAdvice {
    // Во всех прошлых лекциях мы использовали @Before методы исполняющиеся перед основными.
    // Теперь познакомимся поближе со следующим advice типом - @AfterReturning
    // Как мы помним, этот метод исполняется после успешного завершения основного метода.
    // В дальнейшем методы с основной логикой мы будем называть target-методами.

    // Мы создали два новых класса, Student и University. В первом полями у нас указаны имя студента,
    // номер курса и средняя оценка. Так же мы создали конструктор, определили геттеры, сеттеры и toString
    // Во-втором полем у нас является List с объектами класса Student и есть два метода.
    // Первый заполняет лист студентами, второй выводит нам строковое представление списка и
    // возвращает этот список.
    // Так же мы создали новый аспект в котором определили два advice метода Before и AfterReturning
    // проверим как все работает.
    // Мы видим что все работает так как мы и задумали, но зачем мы два раза вывели список студентов?
    // Чтобы продемонстрировать одно интересное свойство @AfterReturning, на самом деле
    // этот метод отрабатывает ПЕРЕД тем как мы получим возврат из target-метода, то есть
    // мы можем перехватить результат, модифицировать его и уже после этого вернуть.
    // Это очень мощный инструмент, который не раз будем нам помогать. Перейдем в
    // UniversityLoggingAspect к @AfterReturning.


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyAOPConfig.class);
        University university = context.getBean("university", University.class);
        university.addStudents();
        List<Student> students = university.getStudents();
        System.out.println(students);
        context.close();
    }
}
