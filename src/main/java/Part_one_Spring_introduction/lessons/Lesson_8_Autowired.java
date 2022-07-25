package Part_one_Spring_introduction.lessons;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import Part_one_Spring_introduction.animal.PersonWithAnnotation;

public class Lesson_8_Autowired {
    // Мы внедряли зависимости вручную прописывая их в конфигурационном файле, однако
    // неудобство такого способа состоит в том, что ссылку на каждую зависимость нам
    // приходилось прописывать руками, в Spring существует специальная аннотация которая
    // позволит нам обойти этот момент, называется она Autowired.

    // Есть три типа autowiring-а которые мы можем использовать для внедрения DI:
        // Для конструктора
        // Для сеттера
        // Для поля
    // Начнем с конструктора.
    // Мы создали новый класс PersonWithAnnotation, перейдем туда.


    // Внедрение зависимостей с помощью Autowired выглядит так:
    // Сканирование пакета, поиск классов с аннотацией @Component
    // При наличии аннотации @Autowired начинается поиск подходящего по типу бина
    // Далее может быть несколько вариантов:
        // Если нашелся подходящий бин, происходит внедрение зависимости
        // Если подходящих по типу бинов нет, бросается исключение
        // Если подходящих бинов больше одного, тоже бросается исключение, кроме случаев
        // когда мы используем @Qualified

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext3.xml");
        PersonWithAnnotation person = context.getBean("personBean", PersonWithAnnotation.class);
        person.callYourPet();
        System.out.println(person.getSurname());
        System.out.println(person.getAge());
        context.close();
    }
}
