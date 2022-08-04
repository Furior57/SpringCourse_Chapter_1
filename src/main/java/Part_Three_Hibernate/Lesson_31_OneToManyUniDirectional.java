package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.oneToManyUni.Department;
import Part_Three_Hibernate.service.entity.oneToManyUni.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Lesson_31_OneToManyUniDirectional {
    // Теперь рассмотрим отношение один-ко-множеству, но с однонаправленными отношениями.
    // Мы создали новый пакет в который скопировали классы Employee и Department.
    // Перейдем в класс Department на 22 строку

    public static void main(String[] args) {
        // Создали новый департамент и двух работников, добавили этих работников в список
        // в объекте департамента, сохранили в таблицу новый департамент, работники подтянулись сами.
        // Удалив работника - мы просто удалим работника. Удалив департамент - удалим всех работников,
        // которые входят в этот департамент.
        // Опять же, можем это поправить, но здесь нам это не критично.
        try (SessionFactory factory = new Configuration().configure()
                .addAnnotatedClass(Department.class).addAnnotatedClass(Employee.class)
                .buildSessionFactory(); Session session = factory.getCurrentSession()) {
            Department department = new Department("Sales", 600, 1000);
            Employee employee1 = new Employee("Ivan", "Taranov", 800);
            Employee employee2 = new Employee("Ilya", "Petrov", 980);
            department.addEmployeeToDepartment(employee1);
            department.addEmployeeToDepartment(employee2);
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        }
    }
}
