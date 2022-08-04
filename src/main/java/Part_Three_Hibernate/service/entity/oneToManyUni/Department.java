package Part_Three_Hibernate.service.entity.oneToManyUni;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "min_salary")
    private int minSalary;
    @Column(name = "max_salary")
    private int maxSalary;
    // Первое, мы больше не будем ссылаться на другую таблицу параметром mappedBy, так как
    // у нас однонаправленная связь и поле Department из класса Employee мы полностью удалили, как
    // и связанные с ним методы.
    @OneToMany(cascade = ALL)
    // Второе, связь нам все таки НУЖНО прописать. И тут оказывается что мы можем из класса
    // Department ссылаться на внешний ключ таблицы employees. Почему это работает?
    // Дело в том, что когда мы настраиваем отношение OneToMany, внешний ключ ВСЕГДА будет определен
    // в той таблице, которая является Many. А в этом классе мы можем просто указать аннотацию
    // @JoinColumn, которая отвечает за создание связи между таблицами и указать в ней параметром
    // название столбца, который содержит внешние ключи. Hibernate сам разберется в какой таблице
    // находится этот столбец. Вернемся в Lesson_31 в main()
    @JoinColumn(name = "department_id")
    private List<Employee> emps;

    public Department() {
    }

    public Department(String name, int minSalary, int maxSalary) {
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }
    public void addEmployeeToDepartment(Employee employee) {
        if (emps == null) {
            emps = new ArrayList<>();
        }
        emps.add(employee);
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

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }
}
