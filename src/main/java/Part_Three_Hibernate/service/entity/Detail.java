package Part_Three_Hibernate.service.entity;


import javax.persistence.*;
// Ниже мы добавляем поле типа Employee, перейдем к нему.
@Entity
@Table(name="details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String city;
    @Column
    private String phone_number;
    @Column
    private String email;

    // К этому полю мы указываем аннотацию с типом отношений, а параметром передаем:
    // mappedBy ="", в который передаем название поля класса Employee по которому мы связаны с ним.
    // Нам не нужно прописывать @JoinColumn, мы это уже сделали в классе Employee и создали связь.
    // Здесь мы просто указываем нашему классу, где эту связь искать.
    // Так же мы указали тип каскада, по аналогии с классом Employee, теперь все изменения в Detail
    // объекте коснутся и связанного Employee объекта. Вернемся в Lesson_29 в main().
    @OneToOne(mappedBy = "empDetail", cascade = CascadeType.ALL)
    private Employee employee;

    public Detail() {
    }

    public Detail(String city, String phone_number, String email) {
        this.city = city;
        this.phone_number = phone_number;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
