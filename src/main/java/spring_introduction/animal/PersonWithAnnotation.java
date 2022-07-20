package spring_introduction.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
// Точно так же пометили его как Component чтобы Spring увидел его при сканировании
// и определили конструктор класса, который требует тип Pet, потом пометили конструктор как @Autowired,
// теперь спринг при создании этого объекта будет искать подходящий тип аргумента для конструктора
// и подставлять его. Интересный момент в том, что мы можем определить пустой конструктор, но
// при наличии конструктора помеченного как @Autowired он никогда не будет использоваться.
// Чуть позже мы рассмотрим момент с двумя подходящими под конструктор бинами, такой вариант
// по-умолчанию бросает исключение.
// Этой же аннотацией мы можем работать с сеттерами, точно так же будет найден подходящий бин и
// подставлен в качестве аргумента, но дело в том, что сейчас абсолютно не важно сеттер ли это, или
// какой-то метод с аргументами, если есть эта аннотация, будут найдены подходящие бины, подставлены,
// а метод запущен.
// В случае с полями мы можем точно по тем же принципам поставить @Autowired, однако в этом случае
// если эти поля используются в конструкторе, мы должны убрать с него аннотацию, чтобы не получилось
// масло масленое, а если в конструкторе есть какая-то логика, то наоборот лучше не отмечать поля.
// Вернемся в Lesson_8 на 20 строку

@Component("personBean")
public class PersonWithAnnotation {

    private Pet pet;
    private String surname;
    private int age;


    @Autowired
    public PersonWithAnnotation(Pet pet) {
        this.pet = pet;
        System.out.println("Constructor PersonWithAnnotation class with Pet argument");
    }
//    @Autowired
    public void setPet(Pet pet) {
        System.out.println("Class Person: set pet.");
        this.pet = pet;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void callYourPet() {
        System.out.println("Hello, my lovely pet");
        pet.say();
    }
}
