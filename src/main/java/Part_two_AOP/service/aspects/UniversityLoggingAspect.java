package Part_two_AOP.service.aspects;

import Part_two_AOP.service.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
}
