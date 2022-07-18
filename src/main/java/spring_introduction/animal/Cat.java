package spring_introduction.animal;

public class Cat implements Pet{
    @Override
    public void say() {
        System.out.println("Miau");
    }
}
