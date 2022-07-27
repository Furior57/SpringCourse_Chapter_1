package Part_two_AOP.lessons;

import Part_two_AOP.config.MyAOPConfig;
import Part_two_AOP.service.Library;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Lesson_20_After_Around {
    // Аннотация @After выполняется при любом результате
    // выполнения метода, будь то нормальное выполнение, или выбросится исключение.
    // Отличается от предыдущих after-ов он тем, что мы не можем получить доступ к результату
    // работы метода, или к его исключению, если таковое было. Однако получить
    // сигнатуру метода мы все же можем с помощью JoinPoint. Это все, что нам надо про него знать.

    // Around advice-метод выполняется до и после выполнения метода. С его помощью можно
    // произвести какие-либо действия до работы target-метода, получить результат работы
    // target-метода, произвести какие-либо действия после работы target-метода, предпринять
    // какие-либо действия в случае если target-метод бросил исключение.
    // В общем это некая сущность которая объединяет в себе Before, AfterReturning, AfterThrowing,
    // однако есть существенное различие.

    // Мы немного подправили наш код. В классе Library мы изменили метод returnBook, теперь
    // он возвращает строку с названием книги, убрали использование return-advice-методов
    // везде, кроме нового аспекта LoggingAspectWithAround, в нем просто выводим сообщение,
    // перейдем туда и посмотрим как сейчас там все выглядит.

    public static void main(String[] args) {
        // Мы имеем вот такую запись, ожидаем при этом, что сначала main выведет сообщение, следующим
        // сообщением будет сообщение от Around advice, после сообщение от метода returnBook(),
        // после название книги и сообщение о том что main завершил работу. А получаем:
//        Main method starts
//        aroundReturnBookLoggingAdvice: return book in library
//        null
//        Main method end working
        // Сообщение от returnBook не было выведено, а вместо названия книги null. Почему так?
        // Вернемся в наш around аспект на 16 строку.
        System.out.println("Main method starts");
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyAOPConfig.class);
        Library library = context.getBean("libraryBean", Library.class);
        String book = library.returnBook();
        System.out.println(book);
        context.close();
        System.out.println("Main method end working");
    }
}
