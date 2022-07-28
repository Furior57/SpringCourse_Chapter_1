package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Lesson_24_PrimaryKey {
    // Мы уже знакомы с понятием первичного ключа, но, как говорится, повторение - мать ученья.
    // Primary key - это уникальный идентификатор столбца в таблице базы данных.
    // Он не может быть null и должен всегда быть уникальным.

    // В прошлой лекции мы добавляли наш объект в таблицу, но поле id не стали заполнять.
    // Дело в том что MySQL, когда видит пустое поле id, сам заполняет его, так как тип id
    // в таблице мы указали как autoincrement, то все дальнейшие id заполняемых
    // объектов будут сами увеличиться на 1
    // Закрепим наши знания и снова вручную добавим работника, уже нового.

    public static void main(String[] args) {
        // Создать объект работника можно как заранее, так и непосредственно в области
        // видимости сессии. Сделаем это заранее
        Employee emp = new Employee("Zaur", "Tregulov", "Education", 2000);
        // Создаем фабрику сессий
        // В этот раз чтобы не писать блок finally, обернем все в try-with-resources
        // Сейчас перейдем в Employee к полю id
        try (SessionFactory factory = new Configuration().configure()
                .addAnnotatedClass(Employee.class).buildSessionFactory()) {
            Session currentSession = factory.getCurrentSession();
            currentSession.beginTransaction();
            currentSession.save(emp);
            currentSession.getTransaction().commit();
        }

        

    }
}
