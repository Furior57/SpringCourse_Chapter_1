package Part_one_Spring_introduction.animal;

public class Person {
    private Pet pet;
    private String surname;
    private int age;
    // очистим конструктор, выведя в нем только диагностическое сообщение
    public Person() {
        System.out.println("Constructor Person class");
    }
    // конструктор пришлось вернуть.
    public Person(Pet pet) {
        this.pet = pet;
        System.out.println("Constructor Person class with Pet argument");
    }
    // здесь мы добавим сеттер для поля pet,
    // мы создали новый файл конфигурации newContext.xml перейдем в него,
    // к бину Person.
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
