package Part_two_AOP.service.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class ExceptionHandlingAspect {
    @Before("Part_two_AOP.service.MyPointcuts.beforeGetMethods()")
    public void beforeGetExceptionHandlingBookAdvice() {
        System.out.println("Exception handling aspect");
    }
    @Before("Part_two_AOP.service.MyPointcuts.beforeAddMethods()")
    public void beforeAddExceptionHandlingBookAdvice() {
        System.out.println("Exception handling aspect");
    }

}
