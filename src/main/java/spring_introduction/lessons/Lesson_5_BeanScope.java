package spring_introduction.lessons;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring_introduction.animal.Cat;
import spring_introduction.animal.Dog;
import spring_introduction.animal.Pet;

public class Lesson_5_BeanScope {
    // Поговорим о понятии Bean Scope. Scope - это область видимости бина, она определяет его жизненный
    // цикл и возможное количество создаваемых бинов. Всего существует 6 разновидностей областей видимости.
    // Четыре из них относятся к веб-разработке, мы их пока не будем касаться, нас интересуют две
    // оставшиеся, это singletone и prototype. По умолчанию при определении бинов используется
    // синглтон. Что это означает? Это означает, что при создании объекта из бина, мы создаем
    // один обьект и кладем его в кеш, все переменные, которым мы присвоим этот обьект, получат ссылку
    // на него. Соответственно изменив какой-либо параметр у одной переменной, мы изменим его у всех
    // переменных ссылающихся на этот обьект. Prototype же, в свою очередь, каждый раз при вызове
    // метода создания объекта, будет создавать новый. Вот и все их различие. Для этого урока мы создали
    // новый файл конфигурации и назвали его applicationContext2.xml

    public static void main(String[] args) {
        // создали контекст
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext2.xml");
        // а теперь интересный момент, мы не ставили никаких scope, так что по умолчанию у нас
        // используется singleton, если мы просто запустим сейчас main(), то у нас появится
        // сообщение "Cat bean is created", которое мы указали в пустом конструкторе класса Cat.
        // Это указывает нам на то, что когда мы создали контейнер, сразу создался обьект класса
        // Cat, после он поместился в кеш. Однако мы так же можем заметить, что конструктор класса
        // Person вызван не был. Далее разберем почему так.
        // Обобщая все что мы узнали о синглтоне мы можем вывести несколько его свойств:
        // бин с таким scope создается сразу после прочтения Spring контейнером файла конфигурации
        // это единственный экземпляр объекта и он является общим для всех кто его запросил у контейнера
        // подходит для stateless объектов, то есть для объектов, состояние которых мы менять
        // не собираемся, эдакий final, но не такой жесткий по ограничениям изменений.

        // Теперь перейдем ко второму scope, prototype. Мы уже знаем, что он создает разные обьекты
        // в памяти, опишем основные свойства:
        // инстанс создается после обращения к контейнеру методом getBean()
        // для каждого обращения создается новый бин,
        // подходит для stateful объектов, то есть каждый обьект имеет свое собственное состояние

        // Перейдем в файл конфигурации для этого урока.

        // А теперь продемонстрируем как это все будет работать
        Cat cat = context.getBean("myPet", Cat.class);
        Cat cat2 = context.getBean("myPet", Cat.class);
        Dog dog = context.getBean("myPet2", Dog.class);
        Dog dog2 = context.getBean("myPet2", Dog.class);
        // здесь мы удостоверяемся что две переменных ссылаются на один обьект в памяти
        System.out.println("Is same objects? "+ (cat==cat2));
        System.out.println(cat+"\n"+cat2);
        // а здесь в обратном
        System.out.println("Is same objects? "+ (dog==dog2));
        System.out.println(dog+"\n"+dog2);

        context.close();
    }
}