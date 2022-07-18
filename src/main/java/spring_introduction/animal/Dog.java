package spring_introduction.animal;

public class Dog implements Pet {
    @Override
    public void say() {
        System.out.println("Woof");
    }
}
