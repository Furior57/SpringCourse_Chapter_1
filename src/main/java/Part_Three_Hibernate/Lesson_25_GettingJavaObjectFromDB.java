package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Lesson_25_GettingJavaObjectFromDB {
    // В этой лекции мы рассмотрим получение объектов из базы данных.
    // Прежде всего отметим один момент. В тот момент когда мы передали объект работника в
    // таблицу, поле id в этом объекте автоматически заполняется hibernate. Надо помнить это.

    // Сначала получим объект из базы по id

    public static void main(String[] args) {
//        Employee emp = new Employee("Elena", "Petrova", "Buh", 1425);
        // Инициируем фабрику и добавляем в таблицу нового работника
        try (SessionFactory factory = new Configuration().configure()
                .addAnnotatedClass(Employee.class).buildSessionFactory()) {
            Session currentSession = factory.getCurrentSession();
//            currentSession.beginTransaction();
//            currentSession.save(emp);
//            currentSession.getTransaction().commit();
            // так как мы уже добавили сотрудника в таблицу, мы можем получить его id
//            String myId = emp.getDepartment();
            // Теперь мы хотим получить обратно этот объект. Сначала нам нужно
            // открыть новую сессию, как только мы завершили транзакцию, старая сессия закрылась
//            currentSession = factory.getCurrentSession();
            // начинаем транзакцию
//            currentSession.beginTransaction();
            // а теперь получаем сам объект методом get(), первым аргументом идет класс который мы хотим
            // получить, второй аргумент первичный ключ.
//            Employee employee = currentSession.get(Employee.class,myId);
            // завершаем транзакцию
//            currentSession.getTransaction().commit();
            // В примере выше для получения объекта Employee из базы мы открывали новую транзакцию,
            // однако никто не мешает нам сделать это все в одной транзакции.
            // Кстати поподробней о транзакции, в самой БД такого понятия нет мы пишем
            // SELECT FROM table_name и исполняем запрос, а в hibernate открываем какие-то
            // транзакции. Если объяснить на пальцах, когда мы создаем транзакцию, мы
            // открываем некое поле куда метод save() записывает команды языка SQL, а когда
            // мы ее закрываем, то все скрипты из этого поля исполняются.
            // Так вот никто не заставляет нас закрывать транзакцию после одного действия,
            // мы можем вложить в нее много действий и только потом закрыть.
//            System.out.println(employee.toString());
            // Сейчас мы получали объект из базы с помощью id, однако нм может понадобиться
            // получать объекты и по другим условиям, по департаменту, например.
            // Для этого существует HQL(Hibernate Query Language). Это язык запросов
            // очень схожим с SQL, но он работает не с таблицами, а с объектами.
            // Мы закомментировали часть кода, чтобы не добавлять ненужные строки в таблицу, оставили
            // только фабрику, начнем заново транзакцию, получим всех работников.
            currentSession.beginTransaction();
            // используем метод createQuery(), в него строкой передаем запрос, в нашем случае
            // очень простой, забираем все объекты Employee и получаем их в список методом
            // getResultList()
//            List<Employee> emps = currentSession.createQuery("from Employee").getResultList();
//            for (Employee e : emps) {
//                System.out.println(e);
//            }
            // Усложним условие. Получим всех работников с именем Art. Все крайне просто,
            // добавляем условие WHERE, как и в SQL. Еще раз отметим, что мы работаем
            // с объектами, так что мы обращаемся не к названию столбца, а к полю класса name.
            // Тут же сразу усложним условие. Хотим видеть всех кого зовут Art и кто получает больше 900
            // тот же оператор AND, что и в SQL, пока отличий совсем нет.
            List<Employee> emps = currentSession.createQuery("from Employee where " +
                    "name='Art' and salary>900",Employee.class).getResultList();
            for (Employee e : emps) {
                System.out.println(e);
            }
            // не забудем закрыть сессию
            currentSession.getTransaction().commit();
        }
    }
}
