package spring_introduction.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class PersonWithJava2 {

    private Pet pet;
    @Value("${person.surname}")
    private String surname;
    @Value("${person.age}")
    private int age;



    public PersonWithJava2(Pet pet) {
        this.pet = pet;
        System.out.println("Constructor PersonWithJava class with Pet argument");
    }

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
