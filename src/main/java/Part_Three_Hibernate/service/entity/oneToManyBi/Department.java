package Part_Three_Hibernate.service.entity.oneToManyBi;

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
    // Мы определили поле в котором будем связывать два класса. Это поле имеет тип List<Employee>,
    // поскольку работников может быть много. Ставим аннотацию @OneToMany, в которую параметрами
    // передаем каскад и название поля из класса Employee, по которому мы и будем связываться.
    // Напоминание, параметр mappedBy говорит нашему классу "Ищи связь в поле department", а там
    // мы уже поставили аннотацию @JoinColumn которая эту связь и создает. Так же после конструкторов
    // мы определили метод, перейдем к нему
    @OneToMany(cascade = {PERSIST, DETACH, REFRESH, MERGE}, mappedBy = "department")
    private List<Employee> emps;

    public Department() {
    }

    public Department(String name, int minSalary, int maxSalary) {
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }
    // В этом методе мы прописали логику записи работников в List<Employee>
    public void addEmployeeToDepartment(Employee employee) {
        if (emps == null) {
            emps = new ArrayList<>();
        }
        emps.add(employee);
        // Так как у нас двунаправленная связь, то при добавлении работника в лист
        // мы должны сразу задать у него поле Department, сделаем это передав в метод
        // setDepartment() объект из которого этот метод вызван, перейдем в класс Lesson_30
        // в main()
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
