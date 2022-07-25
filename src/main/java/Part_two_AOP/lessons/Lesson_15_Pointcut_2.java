package Part_two_AOP.lessons;

import Part_two_AOP.config.MyAOPConfig;
import Part_two_AOP.service.Library;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Lesson_15_Pointcut_2 {
    // Небольшое дополнение к прошлому уроку мы вынесли в отдельный. Касается оно использования pointcut.
    // Мы создали новый аспект и назвали его SecurityAspect, как можно понять из названия, он будет
    // проверять безопасность, допустим права доступа. В нем мы определили новый advice метод, который
    // так же как и один из методов класса LoggingAspect проверяет выражение:
    // @Before("execution(* get*(String, *))"), мы записали его, но тут мы нарушаем одно из
    // основополагающих правил do not repeat yourself. Писать одно и то же выражение несколько раз
    // считается моветоном, а что если его используют несколько методов и нам понадобилось его изменить?
    // Существует решение этой проблемы. Перейдем в класс SecurityAspect

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyAOPConfig.class);

        Library lib = context.getBean("libraryBean", Library.class);
        lib.readBook();
        lib.returnBook();


        context.close();
    }

}
