package Part_two_AOP.service.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspectWithAround {
    // Ничего нового для нас, кроме другой аннотации. Запоминаем как она выглядит, потом
    // мы закомментируем этот метод и напишем заново. Вернемся в Lesson_20 в main()
//    @Around("execution(public String returnBook())")
//    public void aroundReturnBookLoggingAdvice() {
//        System.out.println("aroundReturnBookLoggingAdvice: return book in library");
//    }
    // Тут то мы и столкнулись с существенным отличием @Around от остальных advice-методов.
    // Дело в том, что когда мы используем эту аннотацию, мы обязуемся сами запустить
    // target-методы.
    @Around("execution(public String returnBook())")
    // Для того чтобы запустить метод мы должны передать в параметры advice-метода обьект
    // класса ProceedingJoinPoint, его отличие от обычного JoinPoint как раз и состоит
    // в том что мы можем запустить через него target-метод. Для этого в классе
    // ProceedingJoinPoint есть метод proceed(). Второе отличие от обычных
    // advice состоит в том, что если метод который мы будем запускать
    // имеет какой-то возврат, то и advice-метод тоже должен вернуть этот тип.
    // Чаще всего возвращают обьект Object, так как от него наследуются все классы
    public Object aroundReturnBookLoggingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        // ProceedingJoinPoint имеет все те же методы, что и JoinPoint, мы так же
        // можем получить сигнатуру метода, аргументы и т.д.
        // Небольшое отступление, выполнение Around методов довольно затратное
        // занятие, в высоконагруженных системах стоит точно просчитывать для
        // чего и как мы будем его использовать.
        System.out.println("aroundReturnBookLoggingAdvice: try to return book");
        // здесь мы присвоили результат работы переменной, при желании мы можем изменить
        // ее значение, после того как мы это сделали ide попросит обработать возможное
        // исключение класса Throwable, родителя всех исключений. Мы добавили его в
        // сигнатуру метода.
        // Однако в отличие от AfterThrowing мы можем обработать это исключение,
        // что мы и сделаем.
        // Вынесем переменную вызываемого нами метода за пределы блока try-catch, чтобы
        // она не была локальной только в этом блоке, а уже в блоке присвоим ей значение
        Object targetMethodResult =null;
        try {
            // В данном случае мы обработаем исключение так:
            // Вывели лог об ошибке и присвоили возврату от функции дефолтное значение,
            // в нашем случае написали unknown book, но это плохая практика,
            // так как мы обработали исключение здесь, main метод о нем ничего не узнает
            // мы даже можем не заметить что оно было. Логичнее было бы пробросить исключение
            // выше и обработать его там, но здесь сделаем как сделаем. Ах-да, чтобы получить
            // исключение в методе returnBook() мы попытались поделить 1 на 0, потом
            // закомментируем.
            // Пожалуй все таки пробросим исключение выше в целях образования.
            // Закомментируем строку с возвратом и возбудим исключение, которое будет
            // уже передано в main. Как мы увидим, сначала отработает наш код
            // делающий лог, а уже потом будет брошено исключение
           targetMethodResult  = joinPoint.proceed();
        } catch (Exception e) {
            System.out.println("Logging exception in "+joinPoint.getSignature().getName()
            +"\n"+e);
//            targetMethodResult = "Unknown book";
            throw e;
        }
        System.out.println("aroundReturnBookLoggingAdvice: return book in library");
        return targetMethodResult;
    }
}
