package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.Detail;
import Part_Three_Hibernate.service.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Мы рассмотрели отношение OneToOne с однонаправленной связью, теперь поговорим о двунаправленной.
// В прошлой лекции мы строили код так, что Employee знал с каким Detail он связан.
// Теперь мы напишем код так, чтобы и Detail знал с каким Employee он связан.
// Перейдем в класс Detail
public class Lesson_29_OneToOne_BiDirectional {
    public static void main(String[] args) {
        // Эта запись может показаться несколько громоздкой, однако она сильно уменьшает
        // количество строк кода. В блок try-with-resources мы вынесли не только создание
        // фабрики, но и создание сессии, теперь если мы получим исключение до метода
        // session.getTransaction().commit() у нас не будет утечек памяти.
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class).addAnnotatedClass(Detail.class)
                .buildSessionFactory(); Session session = factory.getCurrentSession()) {
            // Создаем объект Employee
//            Employee employee = new Employee("Mikhail", "Sidorov",
//                    "HR", 1340);
            // Создаем объект Detail
//            Detail detail = new Detail("Moscow", "somePhone",
//                    "someEmail");
            // А сейчас важный момент, мы должны задать соответствующие поля у обоих классов.
            // Если мы сделаем это только в классе Detail, то тогда в таблице у объекта Employee
            // не будет ссылки на соответствующую запись в таблице details, если у обоих
            // классов поля будут проставлены, то мы можем сохранить только объект
            // Detail, объект Employee автоматически будет добавлен в таблицу, внешний ключ
            // ему будет проставлен автоматически.
//            employee.setEmpDetail(detail);
//            detail.setEmployee(employee);
            session.beginTransaction();
//            session.save(detail);
            // А теперь посмотрим что будет когда мы удалим объект Detail из таблицы.
            // Как мы уже догадались, удалилась не только запись с деталями, но и связанная
            // с ней запись работника. Если мы хотим, например, удалить просто детали, то
            // в классе Employee нам необходимо указать другой каскад, не ALL(и DELETE соответственно),
            // либо вообще отключить каскадные операции. Однако это не сработает само по себе,
            // дело в том, что у нашего работника есть внешний ключ ссылающийся на детали, пока
            // мы не очистим это поле, мы не сможем удалить детали. Очистить можно
            // получив объект Employee и задать у него поле Detail как null. Синтаксис писать
            // не будем, там все элементарно.
//            session.delete(session.get(Detail.class,5));
            session.getTransaction().commit();
        }
    }
}
