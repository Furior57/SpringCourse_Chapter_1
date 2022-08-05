package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.manyToMany.Child;
import Part_Three_Hibernate.service.entity.manyToMany.Section;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Lesson_33_ManyToMany {

    // Настало время рассмотреть последний тип отношений, множество-ко-множеству.
    // Его реализация несколько отличается от других отношений. Было бы логично подумать, что
    // в двух таблицах будут внешние ключи ссылающиеся друг на друга. Но только на первый взгляд.
    // Одна запись в таблице может иметь ссылаться на несколько внешних ключей, то есть эта запись
    // будет дублироваться имея каждый раз новый внешний ключ, это очень сильно повлияет
    // на производительность базы данных, как и на ее размер.
    // Чтобы обойти это ограничение вводится новое понятие, JoinTable.
    // JoinTable - это таблица, которая отображает связь many-to-many между строками двух таблиц.
    // Столбцы JointTable это внешние ключи, которые ссылаются на первичные ключи связываемых
    // таблиц. Записью в JoinTable является пара этих ключей, такая запись всегда будет уникальной.


    // В данном случае мы будем работать с новыми сущностями и таблицами. Дано:
    // Имеется список детей и список секций, в каждой секции есть несколько детей, каждый ребенок
    // может ходить в несколько секций. Мы создали три таблицы children, section, children_section.
    // Первые две таблицы содержат детей и секции, соответственно. Синтаксис их создания нам не интересен.
    // А вот создание Join table для нас непривычно, разберем что там и как.

//    CREATE TABLE my_db.child_section (
//    child_id int NOT NULL,            Создаем две колонки куда будем записывать id
//    section_id int NOT NULL,
//    PRIMARY KEY (child_id, section_id),   Обе колонки являются составным primary key
//    FOREIGN KEY (child_id) REFERENCES my_db.children(id), И обе же колонки являются foreignKey
//    FOREIGN KEY (section_id) REFERENCES my_db.section(id));
    // Первичный ключ в данном случае составной, из двух столбцов, sql вполне позволяет так делать,
    // а сами по себе столбцы являются внешними ключами-ссылками на первичные ключи
    // соответствующих таблиц. Как мы помним первичный ключ должен быть уникальным, в том числе и
    // составной. А как мы уже указали выше, все пары столбцов в нашей join таблице являются
    // уникальными.

    // Следующее, что мы должны сделать это создать entity сущности для этих таблиц,
    // назовем их Child и Section, перейдем в Child

    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure()
                .addAnnotatedClass(Child.class).addAnnotatedClass(Section.class)
                .buildSessionFactory(); Session session = factory.getCurrentSession()) {
            // Создаем детей и секции
            Child child1 = new Child("Vova", 7);
            Child child2 = new Child("Vera", 9);
            Child child3 = new Child("Vika", 8);
            Child child4 = new Child("Vitya", 7);
            Section section1 = new Section("Fight club");
            Section section2 = new Section("Water polo");
            // Случайным порядком добавляем детей в секции, главное не пытаться добавлять
            // одного ребенка в секцию два раза
            section1.addChild(child1);
            section1.addChild(child2);
            section1.addChild(child3);
            section2.addChild(child4);
            section2.addChild(child2);
            section2.addChild(child3);
            // Сохраняем секции, благодаря тому, что тип каскада у нас ALL, дети автоматически
            // добавятся в соответствующую таблицу. Мы можем сделать и наоборот, добавить
            // объекту Child несколько секций и сохранить этот объект, секции так же добавятся
            // автоматически.
            session.beginTransaction();
//            session.save(section1);
//            session.save(section2);
            // теперь давайте получим секцию и выведем всю информацию о ней
            Section section = session.get(Section.class, 5);
            System.out.println(section);
            System.out.println(section.getChildren());
            session.getTransaction().commit();
        }
    }

}
