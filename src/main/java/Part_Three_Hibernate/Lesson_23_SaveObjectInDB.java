package Part_Three_Hibernate;

import Part_Three_Hibernate.service.entity.oneToOne.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class Lesson_23_SaveObjectInDB {
    // В предыдущей лекции мы описали класс, который отображаем таблицу employees, настало время
    // сохранить обьект этого класса в таблицу.


    public static void main(String[] args) {
        // Перед тем как начать работать с базой данных, мы должны создать сессию.
        // Создаем ее с помощью класса SessionFactory, внимание! SessionFactory необходимо
        // импортировать из пакета hibernate, по умолчанию ide может подставить другой пакет.
        // Далее импортируем org.hibernate.cfg.Configuration, этот обьект займется
        // конфигурацией нашего соединения, ему сначала необходимо передать месторасположение
        // xml файла с конфигурацией, мы это делаем с помощью метода configure(), в который
        // мы передаем строку с названием файла конфигурации, если мы не меняли название
        // стандартного файла конфигурации Hibernate, можно ничего не менять.
        // Далее методом addAnnotationClass() мы передаем класс с которым будем работать
        // можно передавать несколько классов вызывая этот метод несколько раз, в
        // "собираем" сессию методом buildSessionFactory().

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class).buildSessionFactory();
        // Теперь создадим саму сессию, делается это с помощью объекта SessionFactory,
        // в нашем случае мы это делаем с помощью getCurrentSession, еще есть openSession(),
        // к нему вернемся позже.
        // Session - это обертка вокруг подключения к БД с помощью JDBC. Именно с помощью
        // этого объекта интерфейса мы будем добавлять, извлекать данные из таблиц и проводить
        // другие операции с Java-объектами в БД.
        // Session является ресурсом, продолжительность его жизненного цикла обычна невелика,
        // делаем необходимые операции с объектом и закрываем ее. Если нам понадобится снова
        // произвести какие-то операции мы получим новую сессию из SessionFactory.
        try {
            Session session = factory.getCurrentSession();
            // Создадим объект класса Employee, который мы и будем сохранять в таблицу
            Employee emp = new Employee("John", "Tomorrow", "IT", 1500);
            // теперь нам нужно открыть транзакцию(перемещение объекта), если при прямой
            // работе с базой данных транзакции автоматически открывались и закрывались,
            // то в java мы контролируем этот момент вручную.
            session.beginTransaction();
            // Сохраняем объект в таблице.
            session.save(emp);
            // получаем объект транзакции и методом commit() подтверждаем транзакцию, после чего она
            // закроется
            session.getTransaction().commit();
        } finally {
            // так как наш код может выбрасывать исключения, в блоке finally указываем закрытие
            // фабрики
            factory.close();
        }

    }
}
