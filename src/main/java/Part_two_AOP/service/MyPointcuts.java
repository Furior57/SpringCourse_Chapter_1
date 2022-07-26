package Part_two_AOP.service;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class MyPointcuts {
    @Pointcut("execution(* get*(..))")
    public void beforeGetMethods() {
    }

    @Pointcut("execution(* add*(..))")
    public void beforeAddMethods() {
    }
}
