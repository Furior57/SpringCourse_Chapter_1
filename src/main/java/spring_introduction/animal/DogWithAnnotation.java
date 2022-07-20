package spring_introduction.animal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("dogBean")
@Scope("singleton")
public class DogWithAnnotation implements Pet {
    @Override
    public void say() {
        System.out.println("Woof");
    }

    public DogWithAnnotation() {
        System.out.println("Dog with annotation bean is created");
    }
    @PostConstruct
    public void init() {
        System.out.println("Class DogWithAnnotation: init method");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("Class DogWithAnnotation: destroy method");
    }
}
