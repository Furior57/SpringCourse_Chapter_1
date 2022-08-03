package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.oneToOne.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class Lesson_27_Delete {
    // Эта лекция будет короткой. Здесь мы рассмотрим синтаксис для удаления объектов
    public static void main(String[] args) {
        // Все те же действия, создаем фабрику, сессию и открываем транзакцию
        try (SessionFactory sessionFactory = new Configuration().configure()
                .addAnnotatedClass(Employee.class).buildSessionFactory()) {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            // Сначала получим работника
//            Employee emp = new Employee("John", "Tomorrow", "IT", 1500);
//            Employee emp = currentSession.get(Employee.class, 9);
            // ну собственно первый способ крайне прост, передаем объект работника в метод delete()
//            currentSession.save(emp);
            // однако может быть такое, что у нас нет объекта работника которого мы хотим
            // удалить, в этом случае нам нужно составить запрос, этот способ используется
            // так же в том случае, если мы удаляем несколько строк сразу.
            // Структура запроса вновь очень похожа на SQL, но без from, это
            // уже сделано на уровне hibernate. Так же в конце пишем executeUpdate(),
            // эта команда отвечает за любые изменения в таблицах
            currentSession.createQuery("delete Employee where name='John'").executeUpdate();
            currentSession.getTransaction().commit();

        }
    }
}
