package Part_two_AOP.service.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// Помечаем этот класс двумя аннотациями, сначала как @Component, а уже потом как @Aspect,
// по умолчанию этой библиотеки нет, переходим на сайт maven ищем в репозитории AspectJ weaver, качаем
// jar файл и добавляем в библиотеки проекта, почему-то автоматически maven не захотела его загружать.

// Теперь у нас есть служебный класс-аспект, в нем мы опишем метод, который будет запускаться перед
// методом Library.getBook().
@Component
@Aspect
@Order(1)
public class LoggingAspect {
    // Почему в конце названия метода мы поставили слово Advice? Это принятое именование методов
    // внутри аспект-классов. Есть несколько типов Advice методов:
    // @Before - выполняется до метода с основной логикой
    // @AfterReturning - выполняется после нормального окончания метода с основной логикой
    // @AfterThrowing - этот метод запускается только в том случае, если основной метод бросил
    // исключение
    // After/After finally - выполняется в любом случае после завершения работы основного метода,
    // пока такой аннотации не нашел, далее изучим как это пишется
    // @Around - выполняется до и после метода с основной логикой.

    // Этот метод мы определяем как метод выполняющий свою работу до основного метода, для этого
    // пометим его аннотацией @Before, далее новый для нас синтаксис, дословно он означает:
    // перед-выполнением-public void getBook(), такое выражение называется pointcut и оно
    // описывает, когда именно будет отрабатывать аспект-метод.
//    @Before("execution(public void getBook())"), в дальнейшем мы вынесли это выражение в
    // отдельный класс
    @Before("Part_two_AOP.service.MyPointcuts.beforeGetMethods()")
    public void beforeGetBookAdvice() {
        System.out.println("Logging aspect");
    }

    // При работе аспект-методов очень важно учитывать скорость их работы, не перегружать
    // лишней логикой и использованием ресурсов, так как это может сильно отражаться на скорости
    // работы программы.

    // Вот таким вот образом мы ограничиваем класс метода, который нас интересует.
//    @AfterReturning("execution(public void Part_two_AOP.service.Library.getBook())")
//    public void afterGetBookAdvice() {
//        System.out.println("Method execution with @AfterRunning");
//    }

    // Использование wildcard с названием метода, после * может идти все что угодно, если оставим
    // только *, этот метод будет отрабатывать перед всеми методами с таким модификатором
    // доступа и типом возврата. А если мы заходим, чтобы наш метод работал вообще со всеми
    // запускаемыми методами без параметров, то запись будет выглядеть так:
    // @Before("execution(* *())"), однако что то мне подсказывает, что это плохая практика.
    // Вернемся в Lesson_14 на 47 строку
    @Before("execution(public void set*())")
    public void setBookAdvice() {
        System.out.println("Set-advice method with wildcard is running");
    }

    // Определим еще один advice метод, но уже учитывающий параметры. Как мы видим с помощью
    // wildcard мы так же можем подставлять любой тип параметров, но если мы его не используем,
    // мы должны учитывать тип параметров, а так же их очередность, не получится поставить
    // int перед String, в этом случае аспект будет искать именно такую очередность
    // параметров в методе, само собой количество параметров тоже необходимо учитывать.
    // Однако может возникнуть ситуация, когда нам нужны все методы с любым количеством
    // параметров, в этом случае запись принимает вид:
    // @Before("execution(* get*(..))"), так мы указываем, что количество параметров не определено,
    // их может вообще не быть.
    @Before("execution(* get*(String, *))")
    public void beforeGetBookWithParameters() {
        System.out.println("Get a book with parameters");
    }

    // Однако до сих пор мы работали с примитивными типами(String не учитываем, это класс-обертка
    // для char, который является примитивным), но что будет если параметром мы передаем
    // пользовательский тип данных? Мы создали класс Book, пометили его как @Component, задали
    // одно поле с именем и сразу же определили имя, так же сгенерировали геттер. В этом случае
    // мы параметром указываем полное имя класса, в нашем случае aop.service.Book, иначе ide
    // просто не поймет откуда брать этот класс. Однако в случае с неопределенным количеством
    // параметров мы все равно получим результат, не важно, пользовательский там класс, или
    // примитивный тип данных. Закомментируем его чтобы он нам дальше не мешал.
//    @AfterReturning("execution(* get*(Part_two_AOP.service.Book))")
//    public void afterGetBookObjectAdvice() {
//        System.out.println("Get object \"Book\"");
//    }
    // Для логирования неплохо было бы понимать на каком именно методе вызывается наш advice
    // именно для этого и существуют JoinPoint-ы, в параметры служебного метода мы передаем
    // обьект интерфейса JoinPoint, в котором будет содержаться вся информация
    // о методе на котором вызвали advice
    @Before("Part_two_AOP.service.MyPointcuts.beforeAddMethods()")
    public void beforeAddBookAdvice(JoinPoint joinPoint) {
        // Таким образом мы получаем информацию о сигнатуре метода
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // а таким образом получаем аргументы метода на котором вызван advice
        Object[] args = joinPoint.getArgs();
        // а теперь передадим эту информацию в сообщение и посмотрим что получилось
        // в Lesson_17
        System.out.println("Logging aspect working in: " + methodSignature + "\nWith params: " +
                Arrays.toString(args));
        // Пройдемся по методам интерфейса MethodSignature:
        // Получаем полное название метода с полными названиями типов аргументов
        methodSignature.getMethod();
        // получаем тип возврата метода
        methodSignature.getReturnType();
        // получаем имя метода
        methodSignature.getName();
        // Это основные методы, есть и другие, но их мы тут не будем рассматривать,
        // по большей части этой информации нам будет хватать всегда.
        // Теперь по основным методам JoinPoint
        // получаем вид метода(method-execution)
        System.out.println(joinPoint.getKind());
        // получаем полный адрес целевого метода
        System.out.println(joinPoint.getTarget());
        // Как я понял это место хранения скомпилированного pointcut, могу ошибаться
        System.out.println(joinPoint.getSourceLocation());
        // а здесь получаем сигнатуру pointcut со всеми прописанными в нем аргументами,
        // но вместо метаданных идет точное название метода и типы его аргументов
        // в нашем случае вывод такой:
        // execution(void Part_two_AOP.service.Library.addBook(String,Book))
        System.out.println(joinPoint.getStaticPart());
        // получаем this, для нас ничем не отличается от getTarget, но видно отличие есть
        System.out.println(joinPoint.getThis());
    }


}
