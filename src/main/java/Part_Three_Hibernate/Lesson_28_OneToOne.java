package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.Detail;
import Part_Three_Hibernate.service.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Lesson_28_OneToOne {
    //  В отношениях можно выделить еще один тип связи, правда он относится ко всем типам отношений
    // Это однонаправленные и двунаправленные отношения. Однонаправленные отношения - вид отношений
    // когда одна сущность знает о второй, а вторая о первой не знает. Двунаправленные - когда обе
    // сущности знают друг о друге. В данный момент мы будем работать с однонаправленными отношениями.

    //  У нас уже есть одна таблица employees, мы хотим связать ее по внешнему ключу со второй таблицей
    // details. В MySQL мы удалили старую таблицу employees и накидали вот такой запрос:
//    CREATE TABLE my_db.details ( - создаем таблицу
//    id int NOT NULL AUTO_INCREMENT, - определяем первичный ключ
//    city varchar(15), - определяем три колонки город, номер телефона, адрес почты
//    phone_number varchar(25),
//    email varchar(30), PRIMARY KEY (id) - указываем что именно id первичный ключ
//            );
//
//    CREATE TABLE my_db.employees ( - создаем вторую таблицу с уже знакомыми полями
//    id int NOT NULL AUTO_INCREMENT,
//    name varchar(15),
//    surname varchar(25),
//    department varchar(20), salary int, details_id int - определяем поле с внешним ключом
//,  PRIMARY KEY (id) - устанавливаем первичный ключ
//, FOREIGN KEY (details_id) REFERENCES my_db.details(id)); - устанавливаем внешний ключ ссылающийся на
    // поле id в таблице my_db.details

    // Теперь создадим entity класс, который будет отвечать за таблицу details, Detail, в нем
    // ничего нового, создали поля, определили пустой конструктор, определили конструктор
    // без поля id, а так же сгенерировали геттеры и сеттеры, нас интересует класс Employee в
    // который мы внесли изменения. Перейдем туда на 48 строку

    // Итак, мы имеем два связанных класса у которых отношение OneToOne, прежде всего
    // мы должны при создании фабрики указать их оба методом addAnnotatedClass()
    public static void main(String[] args) {
        Session session = null;
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class).addAnnotatedClass(Detail.class)
                .buildSessionFactory()) {
            session = factory.getCurrentSession();
            // Создаем оба объекта класса и заполняем их.
            // В дальнейшем мы их закомментировали, как и вызов этих объектов
//            Employee employee = new Employee("Olga", "Smirnova", "Buh", 2000);
//            Detail detail = new Detail("Kiev", "someNumberPhone", "someEmail");
            // Теперь нам необходимо на основе объекта Detail присвоить значение в поле этого типа
            // в классе Employee, для этого мы там определили сеттер и геттер для поля
//            employee.setEmpDetail(detail);
            session.beginTransaction();
            // теперь нам достаточно просто сохранить самого работника в таблицу, благодаря
            // тому, что мы указали в аннотации @OneToOne тип каскада, в таблицу
            // details автоматически добавятся данные, причем сначала добавится detail, ему присвоится
            // ключ, а уже потом этот ключ будет добавлен как внешний к работнику
//            session.save(employee);
            // Теперь получим работника из базы и выведем данные о деталях на экран
            Employee emp = session.get(Employee.class, 1);
            System.out.println(emp.getEmpDetail());
            // Кстати о закрытии сессии, несмотря на то, что мы закрываем фабрику при любом исходе,
            // если мы получим исключение до того как применили метод commit() у нас получится
            // утечка памяти. Это не очень хорошая практика, поэтому закрытие сессии
            // тоже необходимо выносить в finally, для этого переменную сессии мы определим
            // за блоком try и присвоим ей null, а внутри уже будем работать с этой переменной
            session.getTransaction().commit();
            // удалять работника мы уже не будем, но запомним, что благодаря каскаду
            // мы удалим не только объект работника, но и связанные с ним детали
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
