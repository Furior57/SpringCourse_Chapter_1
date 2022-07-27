package Part_two_AOP.service.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class SecurityAspect {
    // Вот такой простой записью мы объявляем pointcut-метод, теперь его можно использовать для всех
    // методов в этом классе. Мы даже можем создать отдельный класс с объявленными
    // pointcut методами и использовать его во всех аспектах, по его полному имени, но
    // если мы объявили этот метод в каком то аспект-классе, то только в этом классе мы и можем
    // его использовать. Вспоминаем, что аспекты, это служебные классы и обращаться напрямую
    // к их методам мы не можем. В дальнейшем мы вынесли его в отдельный класс
//    @Pointcut("execution(* get*(..))")
//    public void beforeGetMethods() {
//    }

    @Before("Part_two_AOP.service.MyPointcuts.beforeGetMethods()")
    public void beforeGetSecurityAdvice() {
        System.out.println("Security aspect");
    }

    @Before("Part_two_AOP.service.MyPointcuts.beforeAddMethods()")
    public void beforeAddSecurityAdvice() {
        System.out.println("Security aspect");
    }

    // Так как pointcut-выражения, это выражения :), мы можем использовать их с операторами сравнения
    // В классе Library мы определили два новых метода readBook() и returnBook()
    // теперь определим несколько выражений.

    // Pointcuts выражения
    // все read методы
    @Pointcut("execution(* read*())")
    private void allReadMethods() {
    }
    // все return методы, закомментировали, так как далее пользуемся другим advice
//    @Pointcut("execution(* return*())")
//    private void allReturnMethods() {
//    }
    // все методы
    @Pointcut("execution(* *())")
    private void allMethods() {
    }

    // Before методы
    // перед всеми read методами
    @Before("allReadMethods()")
    public void beforeReadMethodsAdvice() {
        System.out.println("Log #1");
    }
    // перед всеми return методами, закомментировали, так как далее пользуемся другим advice
//    @Before("allReturnMethods()")
//    public void beforeReturnMethodsAdvice() {
//        System.out.println("Log #2");
//    }
    // перед всеми read или return методами, закомментировали, так как далее пользуемся другим advice
//    @Before("allReadMethods() || allReturnMethods()")
//    public void beforeReadOrReturnMethodsAdvice() {
//        System.out.println("Log #3");
//    }
    // перед всеми методами кроме return. Закомментируем его, чтобы он нам дальше не мешал
//    @Before("allMethods() && !allReturnMethods()")
//    public void beforeAllMethodsWithoutReturnAdvice() {
//        System.out.println("Log #4");
//    }

    // Такие комбинации можно выносить в отдельные pointcut-ы. В целом ничего сложного.
    // Единственное, что еще надо отметить - порядок выполнения этих pointcut разный
    // для разных методов, как с этим справиться разберемся в следующей лекции
}
