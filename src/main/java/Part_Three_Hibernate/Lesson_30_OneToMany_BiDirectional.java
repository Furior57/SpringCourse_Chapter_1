package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.oneToManyBi.Department;
import Part_Three_Hibernate.service.entity.oneToManyBi.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Lesson_30_OneToMany_BiDirectional {
    // Теперь мы рассмотрим отношение множество-к-одному с двунаправленной связью.
    // Для начала удалим старые таблицы из базы данных и с помощью этого запроса
    // создадим две новые(кстати интернет говорит, что можно и не создавать таблицы, их
    // создаст Hibernate на основе наших объектов, но это требует дополнительной настройки)
//    CREATE TABLE my_db.departments (
//    id int NOT NULL AUTO_INCREMENT,
//    name varchar(15),
//    max_salary int,
//    min_salary int,
//    PRIMARY KEY (id));
//
//
//    CREATE TABLE my_db.employees (
//    id int NOT NULL AUTO_INCREMENT,
//    name varchar(15),
//    surname varchar(25),
//    salary int,
//    department_id int,
//    PRIMARY KEY (id),
//    FOREIGN KEY (department_id) REFERENCES my_db.departments(id));
    // Так же мы немного отрефакторили код, в пакете Part_Three_Hibernate.service.entity
    // мы создали два новых пакета, OneToOne и OneToManyBi, в них лежат разные классы Employee, в
    // дальнейшем мы будем создавать там новые пакеты под новые entity.
    // Перейдем в класс Employee

    public static void main(String[] args) {
        // И снова открываем все ресурсы которые должны быть закрыты в блоке
        // try-with-resources
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class).addAnnotatedClass(Department.class)
                .buildSessionFactory(); Session session = factory.getCurrentSession()) {
            // Создаем департамент, двух работником и добавляем этих работников в соответствующий
            // список являющийся полем в классе Department
//            Department dep = new Department("IT",300,1200);
//            Employee emp1 = new Employee("Art", "Shibaev", 800);
//            Employee emp2 = new Employee("Elena", "Smirnova", 1000);
//            dep.addEmployeeToDepartment(emp1);
//            dep.addEmployeeToDepartment(emp2);

            session.beginTransaction();
            // Сохраним департамент в базе данных, работники сохранятся автоматически
//            session.save(dep);
            // Получим департамент из таблицы и выведем на экран всех работников в этом департаменте
            Department department = session.get(Department.class, 1);
            for (Employee e : department.getEmps()) {
                System.out.println(e);
            }
            // Так как связь двусторонняя мы можем получить его из таблицы и вывести данные о его
            // департаменте
            Employee employee = session.get(Employee.class, 1);
            System.out.println(employee.getDepartment());
            // Удалим работника, однако важно помнить, если у нас установлен тип каскада
            // CascadeType.ALL или REMOVE, то удалив одного работника из департамента мы
            // удалим и сам департамент, а так как в департаменте тоже могут стоять
            // эти каскады, то удалятся и все работники из этого департамента. Чтобы
            // этого не случилось мы, во-первых, сделаем статический импорт перечисления CascadeType,
            // чтобы не писать полностью имя перечисления. А во-вторых укажем такой записью, что
            // мы используем все типы каскада кроме ALL и REMOVE:
            // cascade = {PERSIST, DETACH, REFRESH, MERGE}, теперь при удалении одного работника
            // не будет удален департамент, а при удалении департамента не будут удалены
            // работники.
            session.delete(session.get(Employee.class, 2));
            session.getTransaction().commit();
        }
    }



}
