package Part_Three_Hibernate.service.entity.manyToMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
// По аналогии с прошлым примером OneToMany мы создаем класс, полями определяем столбцы в таблице,
// а также добавляем поле List в котором будем держать множество детей(кстати было бы логичней
// Set а не List), также мы по аналогии определили метод заполнения нашего списка.
// Теперь перейдем к этому полю.
@Entity
@Table(name="children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    // А вот здесь будет непросто, запоминаем, что мы делаем. Сначала ставим аннотацию
    // отношений @ManyToMany, мы уже знакомы с ее параметрами, не будем заострять внимание на них.
    // После этого идет новая аннотация для нас @JoinTable. Первым параметром мы указываем
    // название таблицы в которой будут храниться первичные ключи наших связных таблиц.
    // Вторым параметром мы передаем joinColumn = @JoinColumn, этим параметром мы указываем
    // в каком поле JoinTable мы будем устанавливать связь с нашим классом.
    // Третьим параметром мы устанавливаем inverseJoinColumns = @JoinColumn, этим параметром
    // мы указываем каким полем будет связываться с нами другой entity. Несколько
    // сложно для понимания, попробуем объяснить на пальцах. Параметром joinColumn мы передаем
    // наш первичный ключ в поле JoinTable, которое указали в этом параметре.
    // Параметром inverseJoinColumns мы указываем поле в котором будем искать первичный ключ
    // переданный связным entity, для нас он является внешним ключом.
    // В классе Section мы сделали все абсолютно так же как и тут, но поля указанные в
    // joinColumns и inverseJoinColumns поменялись там местами.
    // Перейдем в Lesson_33 в main().
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "children_section",joinColumns = @JoinColumn(name = "child_id"),
    inverseJoinColumns = @JoinColumn(name = "section_id"))
    private List<Section> sections;


    public Child() {
    }

    public Child(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void addChild(Section section) {
        if (sections == null) {
            sections = new ArrayList<>();
        }
        sections.add(section);
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
