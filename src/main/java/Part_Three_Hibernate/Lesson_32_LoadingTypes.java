package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.loadingTypes.Department;
import Part_Three_Hibernate.service.entity.loadingTypes.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Lesson_32_LoadingTypes {
    //  В прошлых лекциях мы описывали отношение OneToMany с двунаправленными отношениями.
    // При этом получая из таблицы объект департамента, мы автоматически получали объекты
    // всех работников из этого департамента. Рассмотрим поподробнее этот механизм.

    // В Hibernate при загрузке данных из таблицы есть два типа этой загрузки Eager и Lazy.
    // Eager(нетерпеливая) загрузка - при ее использовании связанные сущности загружаются сразу
    // вместе с загрузкой основной сущности. Неудобство такой загрузки может состоять в том,
    // что при загрузке одного объекта, мы будем подтягивать половину БД, это не всегда логично и
    // нужно.
    // Lazy(ленивая) загрузка - при ее использовании связанные сущности не загружаются сразу с
    // основной, загрузятся они только при обращении к ним. У этого подхода есть свой недостаток.
    // Для загрузки дополнительных сущностей создается дополнительный прокси объект с помощью
    // JDK Dynamic Proxy, если объяснять на пальцах, то когда мы используем метод get() для
    // получения связной сущности, создается некий третий класс, который ловит вызов этого метода
    // и обрабатывает его. При этом этот объект требует открытой сессии, что не всегда будет
    // для нас удобно, например вызов может осуществляться где-то на клиентской машине,
    // которая знать не знает о БД.

    public static void main(String[] args) {
        // Мы создали новый департамент HR и создали несколько новых работников, всех раскидали
        // в разные департаменты, чтобы было минимум два работника на один департамент.
        // Теперь перейдем в класс Department в пакете loadingTypes на 23 строку
        try (SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class).buildSessionFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            System.out.println(session.get(Department.class,2).toString());
            session.getTransaction().commit();
        }
    }

}
