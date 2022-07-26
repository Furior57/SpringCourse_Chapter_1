package Part_two_AOP.lessons;

import Part_two_AOP.config.MyAOPConfig;
import Part_two_AOP.service.Book;
import Part_two_AOP.service.Library;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Lesson_17_JoinPoint {
    // На прошлых лекциях мы познакомились с аспектами и advice-методами, теперь поговорим
    // об их применении.
    // В АОП существует понятие Join Point.
    // Join Point - это точка\момент в выполняемой программе когда мы применяем advice-методы,
    // то есть это точка переплетения бизнес логики со служебным функционалом.

    // Для этой лекции мы несколько видоизменили наш код. В классе Book мы добавили два поля,
    // автор и год выпуска книги, сразу задали им значения.
    // В классе Library разделили выполнение методов прочерками и добавили новый метод addBook()
    // принимающий параметрами строку с именем читателя и обьект Book.
    // Так же в классе MyPointcut мы добавили новое выражение работающее со всеми add методами.
    // После мы добавили это выражение во все аспекты как @Before.
    // Теперь перейдем в LoggingAspect на 86 строку


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyAOPConfig.class);
        // Создали библиотеку и книгу и запустили метод addBook()
        // вывод будет такой:
//        Logging aspect working in: void Part_two_AOP.service.Library.addBook(String,Book)
//        With params: [Art, Book{bookName='Pride and prejudice', author='Jane Austen', yearOfPublication=1796}]
//        Security aspect
//        Exception handling aspect
//        Book is added in library
        // как мы видим мы получили полную информацию на каком методе мы вызвали логирование,
        // а так же какие параметры мы передали, вплоть до всех полей класса Book, вернемся
        // в LoggingAspect на 104 строку и познакомимся с другими методами интерфейса JoinPoint
        Library library = context.getBean("libraryBean", Library.class);
        Book book = context.getBean("book", Book.class);
        library.addBook("Art", book);
        context.close();
    }

}
