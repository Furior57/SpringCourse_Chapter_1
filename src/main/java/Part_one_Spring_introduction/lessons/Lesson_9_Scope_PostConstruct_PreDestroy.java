package Part_one_Spring_introduction.lessons;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import Part_one_Spring_introduction.animal.PersonWithAnnotation;

public class Lesson_9_Scope_PostConstruct_PreDestroy {
    // С областями видимости все просто, после @Component мы указываем аннотацию @Scope, куда в скобки
    // записываем какую именно область видимости мы создаем. Для этого урока мы будем использовать
    // класс DogWithAnnotation, он идентичен классу CatWithAnnotation, но в нем мы явно указали
    // что это singleton бин. Соответственно в классе PersonWithAnnotation мы переписали все id
    // на dogBean, а так же пометили его как prototype.

    // Собственно с аннотациями для init и destroy методов все тоже очень просто
    // они помечаются как @PostConstruct и @PreDestroy, соответственно. Самое сложное это
    // подтянуть зависимость, для того, чтобы эти аннотации заработали :) Оставим это вне урока.

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext3.xml");
        PersonWithAnnotation person = context.getBean("personBean", PersonWithAnnotation.class);
        person.callYourPet();

        context.close();
    }
}
