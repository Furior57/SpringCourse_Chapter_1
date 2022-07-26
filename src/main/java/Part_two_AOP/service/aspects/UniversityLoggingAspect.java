package Part_two_AOP.service.aspects;

import Part_two_AOP.service.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class UniversityLoggingAspect {

    @Before("execution(* getStudents())")
    public void beforeGetStudentsLoggingAdvice() {
        System.out.println("beforeGetStudentsLoggingAdvice: logging getting list of" +
                "students before method getStudents()");
    }
    // Здесь будет новый для нас синтаксис, в аннотацию мы передаем выражение, если оно одно,
    // то мы просто пишем его, если их несколько, то каждому выражению мы должны присвоить имя
    // типа этого выражения. Первое выражение pointcut, имя такое же, второе выражение
    // у нас возврат от target-метода, его имя будет returning, записывается так:
    @AfterReturning(pointcut = "execution(* getStudents())", returning = "students")
    // Однако чтобы это заработало, нам необходимо в advice-метод аргументом передать
    // тот тип данных который мы перехватываем, имя аргумента должно совпадать с выражением
    // returning. Записывается так:
    public void afterReturningGetStudentsLoggingAdvice(List<Student> students) {
        // Теперь мы можем обработать то что перехватили и вернуть модифицированную
        // коллекцию. Кстати никто не запрещает нам так в аргументы передать JoinPoint,
        // чтобы получить сигнатуру метода. Предостережение! Всегда необходимо оставлять
        // комментарии к тем методам, возврат которых мы можем подобным образом
        // модифицировать.
        Student firstStudent = students.get(0);
        firstStudent.setNameSurname("Artem Shibaev");
        firstStudent.setAvGrade(9.6);
        System.out.println("afterReturningGetStudentsLoggingAdvice: logging getting list of " +
                "students after returning method getStudents()");
    }

    // Если мы хотим получить информацию об исключении, нам необходимо действовать
    // по аналогии с тем как мы записывали сигнатуру AfterReturning, добавляем выражение:
    @AfterThrowing(pointcut = "execution(* getStudents())", throwing = "exception")
    // Слово throwing аналог предыдущего returning, а аргументом в метод передаем
    // обьект класса Throwable, это родитель для всех классов исключений, впрочем,
    // если мы уверены в том какое исключение у нас будет мы можем передать
    // именно его тип.
    public void afterThrowingGetStudentsLoggingAdvice(Throwable exception) {
        // здесь мы просто выведем имя исключения.
        System.out.println("afterThrowingGetStudentsLoggingAdvice: logging exception:\n" +
                ""+ exception);
        // Здесь еще два полезных метода, описание исключения не включая его полного имени:
        System.out.println(exception.getMessage());
        // значение ставшее причиной исключения, у нас null, так как мы вышли за границы списка
        System.out.println(exception.getCause());
        // Однако несмотря на то, что мы можем перехватить исключение, мы никак не можем его
        // обработать, оно будет перебрасываться выше до того блока где мы его обработаем.
        // Либо дойдет до main() не обработанным и программа аварийно завершится.

    }
}
