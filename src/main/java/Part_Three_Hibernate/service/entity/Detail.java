package Part_Three_Hibernate.service.entity;


import javax.persistence.*;

@Entity
@Table(name="details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String city;
    @Column
    String phone_number;
    @Column
    String email;

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
