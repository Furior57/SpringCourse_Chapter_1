package spring_introduction.lessons;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_introduction.animal.Person;
import spring_introduction.animal.PersonWithJava;
import spring_introduction.config.MyConfig;

public class Lesson_10_ConfigurationWithJava {
    // Мы научились конфигурировать контейнер с помощью xml файла и аннотаций+xml,
    // настало время научиться делать то же самое используя только Java.
    // Делается это с помощью специального класса, который и будет нашей конфигурацией
    // мы создали новый пакет, назвали его config, а в нем создали класс MyConfig, перейдем в него.

    public static void main(String[] args) {
        // До этого мы создавали контейнер Spring используя файл xml, теперь его инициализация
        // будет несколько отличаться, такой записью мы создаем контейнер:
        AnnotationConfigApplicationContext context =
                // обратим внимание, что мы не передаем аргументом обьект класса MyConfig, а
                // получаем описание класса с помощью рефлексии.
                new AnnotationConfigApplicationContext(MyConfig.class);
        // мы создали новый класс для работы с этим типом конфигурации
        PersonWithJava person = context.getBean("personWithJava", PersonWithJava.class);
        person.callYourPet();
        context.close();
    }
}
