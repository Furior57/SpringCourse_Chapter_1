package spring_introduction.animal;

public class Cat implements Pet{

    public Cat() {
        System.out.println("Cat bean is created");
    }

    public void init() {
        System.out.println("Class Cat: init method");
    }

    public void destroy() {
        System.out.println("Class Cat: destroy method");
    }

    @Override
    public void say() {
        System.out.println("Miau");
    }
}
