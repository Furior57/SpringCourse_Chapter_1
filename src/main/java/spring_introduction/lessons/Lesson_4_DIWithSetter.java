package spring_introduction.lessons;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring_introduction.animal.Person;

public class Lesson_4_DIWithSetter {
    // В прошлом уроке мы внедряли зависимость с помощью конструктора. Теперь мы будем это делать с помощью
    // сеттеров. Для начала перейдем в класс Person

    // Добавим в классе Person два поля surname и age, строка и число, соответственно, и добавим
    // для них геттеры и сеттеры. Мы хотим задать их значения, вернемся в newContext на 28 строку.

    // До сих пор мы всегда задавали значения параметров в самом коде, но как уже было сказано выше,
    // это плохая практика. В Collection существует класс Property, это наследник HashMap, его суть
    // заключается в том, что мы можем загрузить в него какие-либо параметры в формате ключ-значение.
    // Так же этот класс наследует методы от HashMap. В Java эти параметры можно хранить в файлах с
    // расширением .properties, формат параметра key=value. И ключ и значение считываются как строка.
    // Вернемся в newContext 34 строка

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("newContext.xml");

        Person person = context.getBean("myPerson", Person.class);
        person.callYourPet();
        context.close();

    }
}
