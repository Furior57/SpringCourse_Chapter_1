package aop.lessons;

import aop.config.MyAOPConfig;
import aop.service.Book;
import aop.service.Library;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Lesson_14_Pointcut {
    // На прошлой лекции мы познакомились с Advice-методами и услышали, что в аннотации такого метода
    // мы записываем pointcut выражение. Познакомимся с этим понятием поближе.
    // Pointcut - это выражение описывающее где должен быть применен Advice.
    // Spring AOP использует AspectJ Pointcut expression language. Т.е. определенные правила в написании
    // выражений для создания pointcut в предыдущем примере мы использовали такое выражение
    // @Before("execution(public void getBook())"), означающее что мы должны запустить метод с
    // этой аннотацией перед запуском метода в выражении.
    // Выражения пишутся по определенным правилам, рассмотрим поподробней:
    // execution(modifiers-pattern? return-type-pattern declaring-type-pattern?
    // method-name-pattern(parameters-pattern) throws-pattern?), те части где стоят знаки вопроса
    // необязательны. Рассмотрим их:
    // modifiers-pattern? - модификатор доступа
    // return-type-pattern - возвращаемый тип
    // declaring-type-pattern? - указание класса содержащего метод
    // method-name-pattern(parameters-pattern) - имя метода и параметры, если они есть
    // throws-pattern? - описание исключений которые может бросить метод

    // В нашем примере execution(public void getBook()), advice метод будет вызываться
    // перед любым методом подходящим под параметры, мы можем создать второй класс и проверить
    // это, но особого практического смысла тут не будет. Нас больше интересует как указать класс
    // метода, перейдем в LoggingAspect, где мы определили новый метод, который будет отрабатывать
    // после метода getBook(), но уже укажем к какому именно классу относится этот метод.
    // Называться этот метод будет afterGetBookAdvice()
    // Однако чаще всего нам необходимо, чтобы advice метод работал с несколькими методами, как
    // нам в этом случае указывать имя метода? Здесь нам поможет wildcard, вернемся
    // в LoggingAspect, где мы определили новый метод setBookAdvice().
    // Очень важно именовать методы в соответствии с какой-то методологией для правильного
    // использования Aspect классов, это намного облегчит работу со сквозной логикой.

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyAOPConfig.class);

        Library lib = context.getBean("libraryBean", Library.class);
        Book book = context.getBean("book", Book.class);
        lib.getBook();
        // Закомментируем вызов методов чтобы не захламлять аутпут.
//        lib.setBookName();
//        lib.setReaderName();
        // До сих пор мы обрабатывали методы без параметров, теперь посмотрим как аспекты
        // работают с параметрами. В классе Library, мы перегрузили метод getBook(), теперь
        // он может принимать в себя строку с названием книги и целочисленное значение - id.
        // Перейдем в LoggingAspect на 55 строку.
        lib.getBook("Alphabet", 1);
        // Пример работы с пользовательскими классами
        lib.getBook(book);
        context.close();
    }
}
