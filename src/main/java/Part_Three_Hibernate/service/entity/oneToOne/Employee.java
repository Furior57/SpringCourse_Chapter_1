package Part_Three_Hibernate.service.entity.oneToOne;
// Как мы можем видеть все аннотации мы импортируем не из Hibernate а из javax.persistence
// JPA(Java Persistence API) - это стандартная спецификация в Java, которая описывает систему
// управления сохранением объектов в базы данных, Hibernate - это ее реализация.
// Де-факто Hibernate уже давно является эталоном реализации JPA.
// Чтобы было попроще понять - JPA - свод правил. Hibernate - реализует эти правила и добавляет немного
// своих. Hibernate имеет свои реализации этих аннотаций, однако команда Hibernate рекомендует
// использовать JPA аннотации. Связано это с тем, что реализация у Hibernate может поменяться,
// что потребует дополнительных правок кода.
import javax.persistence.*;

// первое, что мы должны сделать это пометить класс аннотацией @Entity, эта аннотация говорит
// что данный класс будет иметь отображение в базе данных.
@Entity
// Эта аннотация говорит к какой именно таблице мы будем привязывать наш класс, аргументом
// передаем параметр name="", в кавычки пишем название таблицы, однако если наш класс и таблица
// называются одинаково, можно оставить скобки пустыми, но это плохая практика, для повышения читаемости
// название таблицы лучше писать
@Table(name="employees")
public class Employee {
    // Перечисляем поля и добавляем к ним аннотацию @Column(), в скобках указываем с каким
    // столбцом мы связываем это поле. Поле с первичным ключом дополнительно помечаем как @Id
    @Id
    // Мы пометили поле как id, теперь оно будет автоматически заполняться SQL числами от 1 и
    // далее. Есть такое понятие "Стратегия генерации", оно как раз таки и отвечает
    // за заполнения поля id, ниже мы добавили аннотацию, которая описывает стратегию генерации.
    // В данном случае в скобки мы записываем strategy = GenerationType.IDENTITY, это
    // означает, что мы полагаемся на автоматическое увеличение id столбца по правилам
    // прописанным в БД.
    // Если и другие стратегии
    // GenerationType.SEQUENCE - генерирует id в соответствии с заданными правилами в БД.
    // Преимущество в скорости по сравнению с IDENTITY, однако в MySQL к сожалению отсутствует,
    // по умолчанию делает отдельный SELECT для начитки этого значения. По своей сути хеш-функция
    // GenerationType.TABLE - эмулирует SEQUENCE, используется редко, медленный способ.
    // GenerationType.AUTO - jpa provider сам решает что делать, в зависимости от диалекта SQL,
    // имеет неявный тип, что не есть хорошо.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "department")
    private String department;
    @Column(name = "salary")
    private int salary;
    // Нам необходимо связать два класса, в этот класс мы будем передавать внешний ключ.
    // Добавили поле типа Detail и пометили аннотацией @OneToOne, таким образом
    // мы указываем какой тип отношений будет в наших классах.
    // В скобках мы указали тип cascade-операций для этой связи, это операции со связанными таблицами
    // если мы удалим объект Employee, то у нас автоматически удалится связанный с ним объект
    // Details, перечисление ALL означает, что каскад применяется ко всем типам операций.
    // Если мы не прописываем каскад, то по умолчанию он не работает.
    @OneToOne(cascade = CascadeType.ALL)
    // А здесь мы указываем именно то поле в таблице, которое связывает наши классы
    @JoinColumn(name = "details_id")
    // Тип этого поля соответствует тому классу с которым мы связываемся.
    // Вернемся в Lesson_28 в main()
    private Detail empDetail;


    // обязательно создаем конструктор без аргументов
    public Employee() {
    }
    // Однако можем создать и конструктор с аргументами. Мы не будем туда передавать только id
    // это поле будет генерироваться базой данных
    public Employee(String name, String surname, String department, int salary) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Detail getEmpDetail() {
        return empDetail;
    }

    public void setEmpDetail(Detail empDetail) {
        this.empDetail = empDetail;
    }
}
