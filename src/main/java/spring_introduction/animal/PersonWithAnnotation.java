package spring_introduction.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
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
// масло масленое, а если в конструкторе есть какая-то логика, то лучше совсем не отмечать поля.

// Теперь рассмотрим вариант когда у нас два однотипных бина и нам необходимо выбрать один из них.
// Для этого используется аннотация @Qualifier(), в случае с полями и методами класса, мы записываем
// ее под Autowired и в скобках указываем id бина(в кавычках конечно же), однако в случае с
// конструктором класса синтаксис отличается, мы точно так же пишем аннотацию и id бина, но
// делаем это в скобках куда принимаются аргументы конструктора.

// Теперь немного о полях с простыми типами. Мы можем напрямую задать им значение с помощью
// аннотации @Value(), в скобки передаем значение(опять же в кавычках), но здесь надо быль
// осторожнее, ранее когда мы делали это в xml файле напрямую, ide проверяла какой тип мы передаем,
// теперь она этого не делает. Однако это нежелательный вариант, лучше, как и ранее, использовать
// файл с записанными в нем значениями, для этого в xml прописываем, где этот файл лежит, в нашем
// случае мы прописываем <context:property-placeholder location="classpath:myApp.properties"/>
// а в аннотации @Value, записываем в кавычки, уже знакомый нам синтаксис ${parameter.name}

// Вернемся в Lesson_8 на 20 строку

@Component("personBean")
@Scope("prototype")
public class PersonWithAnnotation {

    private Pet pet;
    @Value("${person.surname}")
    private String surname;
    @Value("${person.age}")
    private int age;


    @Autowired
    public PersonWithAnnotation(@Qualifier("dogBean")Pet pet) {
        this.pet = pet;
        System.out.println("Constructor PersonWithAnnotation class with Pet argument");
    }
    @Autowired
    @Qualifier("dogBean")
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
