package Part_two_AOP.lessons;

import Part_two_AOP.config.MyAOPConfig;
import Part_two_AOP.service.Student;
import Part_two_AOP.service.University;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Lesson_19_AfterThrowing {
    // В этой лекции мы поближе рассмотрим @AfterThrowing.
    // AfterThrowing - выполняется в том случае, если target- метод выбросил исключение.
    // В основном используется для логирования.
    // Для примера мы специально в методе University.getStudents() попытались вывести информацию
    // о студенте с индексом 3, так как такого не существует метод бросит исключение. Проверяем:
//    beforeGetStudentsLoggingAdvice: logging getting list ofstudents before method getStudents()
//    Start working method: getStudents()
//    afterThrowingGetStudentsLoggingAdvice: logging exception
    // Действительно, мы видим, что перед тем как метод бросил исключение вывелось наше сообщение,
    // теперь мы можем получить информацию о методе который бросил это исключение и сохранить ее,
    // а так же, например, обработать это исключение, если мы представляем какого типа оно будет.
    // Так же мы можем получить информацию об исключении, до того как оно дойдет в метод main()
    // Перейдем в UniversityLoggingAspect к AfterThrowing.

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
