package Part_Three_Hibernate.service.entity.loadingTypes;

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

    // В аннотации OneToMany мы можем указать тип загрузки данных, параметром fetch,
    // указываем LAZY, теперь получая данные о департаменте мы получаем только департамент,
    // что будет видно в консоли, за кадром мы поигрались с разными типами и сравнили вывод.

    // Если мы не будем явно устанавливать тип загрузки данных, будет использоваться тип по умолчанию.
    // Причем для разных отношений тип загрузки по умолчанию тоже разный, вот все типы:
    // One-To-One - Eager
    // One-ToMany - Lazy
    // Many-To-One - Eager
    // Many-To-Many - Lazy
    @OneToMany(cascade = ALL, mappedBy = "department", fetch = FetchType.LAZY)
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
        employee.setDepartment(this);
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
