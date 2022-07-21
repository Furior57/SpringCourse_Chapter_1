package spring_introduction.lessons;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_introduction.animal.Cat;
import spring_introduction.animal.PersonWithJava2;
import spring_introduction.config.MyConfig2;

public class Lesson_11_ConfigurationWithJava2 {
    // Существует второй способ конфигурации контейнера с помощью Java, в этом способе
    // мы в классе-конфигураторе напрямую описываем какие бины создаются и какие зависимости
    // будут использоваться. Для демонстрации мы создали классы MyConfig2, PersonWithJava2.
    // В качестве бина будем использовать класс Cat, в нем нет никаких аннотаций, что нам сейчас и нужно.
    // Перейдем в класс MyConfig2.

    public static void main(String[] args) {
        // создание бина ничем не отличается от прошлого способа
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig2.class);
        PersonWithJava2 person = context.getBean("personBean", PersonWithJava2.class);
        person.callYourPet();
        System.out.println(person.getAge());
        System.out.println(person.getSurname());

        context.close();
    }
}
