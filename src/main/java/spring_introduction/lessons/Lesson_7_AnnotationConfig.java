package spring_introduction.lessons;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring_introduction.animal.CatWithAnnotation;

public class Lesson_7_AnnotationConfig {
    // Мы ознакомились с устаревшим способом конфигурации контейнера,
    // настало время познакомиться с современными.
    // Начнем знакомство с аннотаций. Мы уже встречались с ними изучая Java, в частности, когда
    // указывали что переопределяем метод(@Override) и создавали функциональные
    // интерфейсы(@FunctionalInterface). Однако не задумывались над точным определением этого слова.
    // Аннотации - это специальные комментарии(метки, метаданные), которые нужны для передачи
    // определенной информации.
    // В Spring аннотации так же используются для передачи метаданных. Сейчас мы касаемся именно
    // конфигурации контейнера. Если мы будем использовать наши знания для ручной конфигурации каждого
    // бина, то в большом приложении, мы потратим на это много времени, не говоря о том, что читать
    // такую конфигурацию становится утомительным занятием. Вот тут аннотации и приходят на помощь.

    // Как проходит процесс создания бинов из аннотаций? Сначала Spring сканирует все классы на наличие
    // аннотации @Component, после этого создает(регистрирует) бин в Spring контейнере.
    // Мы создали новый конфигурационный файл applicationContext3.xml, он абсолютно пуст, кроме указания
    // области имен, добавили туда единственную строку:
    // <context:component-scan base-package="spring_introduction"/>
    // Такой записью мы указываем в каком пакете Spring должен искать классы отмеченные как @Component
    // перейдем класс CatWithAnnotation

    // проверим как все работает
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext3.xml");
        CatWithAnnotation cat = context.getBean("catBean", CatWithAnnotation.class);
        cat.say();
        context.close();
    }

}
