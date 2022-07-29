package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Lesson_26_Update {

    // В этой лекции мы будем модифицировать объекты содержащиеся в таблицах
    public static void main(String[] args) {
        // Начало стандартное, создаем фабрику, не забываем в finally закрыть ее,
        // нам это не нужно потому что мы пользуемся try-with-resources.
        try (SessionFactory factory = new Configuration().configure()
                .addAnnotatedClass(Employee.class).buildSessionFactory()) {
            // Получаем сессию и открываем транзакцию
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            // Получаем работника которого будем модифицировать, мы сразу укажем его id
            Employee employee = session.get(Employee.class, 1);
            // Меняем зарплату и закрываем транзакцию. И все.
            // Дело в том, что объект который мы получили всегда связан с таблицей.
            // Изменили объект? Изменили его отображение в таблице. UPDATE пропишется автоматически.
            employee.setSalary(1500);
            // а теперь поднимем зп всех работников в таблице, для этого пользуемся
            // ключевым словом UPDATE, опять же разницы с SQL тут нет, потом пишем set,
            // указываем какое поле мы меняем и присваиваем ему новое значение, можно
            // добавить where с указанием условий отбора сотрудников, но мы тут решили
            // поднять зп всем. В конце добавляем метод executeUpdate, иначе наши изменения
            // не применятся, хоть ошибок и не будет
            session.createQuery("update Employee set salary= salary + 1000").executeUpdate();
            session.getTransaction().commit();
            // а вот тут интересный момент, мы получим здесь зп 1500, потому что мы получали
            // объект со старым значение зп, чтобы увидеть изменение после update, необходимо
            // снова получить этот объект.
            System.out.println(employee);




        }
    }
}
