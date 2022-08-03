package Part_Three_Hibernate.service.entity.oneToManyBi;
import Part_Three_Hibernate.service.entity.oneToOne.Detail;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "salary")
    private int salary;
    // Так же создаем поле связывающее наши классы, но аннотация связи теперь @ManyToOne, множество
    // работников может относиться к одному департаменту.
    @ManyToOne(cascade = {PERSIST, DETACH, REFRESH, MERGE})
    // Вновь с помощью этой аннотации указываем, что в таблице employees столбец department_id, это
    // внешний ключ. ВНИМАНИЕ! Когда мы работаем с отношением множество-к-одному, внешний ключ
    // ВСЕГДА должен находиться в той таблице, которая является множеством, далее Hibernate
    // сам разберется на какой именно id мы ссылаемся. Теперь перейдем в класс Department
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {
    }

    public Employee(String name, String surname, int salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                '}';
    }
}
