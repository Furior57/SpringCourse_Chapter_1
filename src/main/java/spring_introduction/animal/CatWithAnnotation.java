package spring_introduction.animal;

import org.springframework.stereotype.Component;

// Добавим аннотацию, в скобках указываем id бина, что в прочем не обязательно,
// если мы не указываем id, то Spring автоматически создает его сам, меняя первую букву класса
// на прописную, в нашем случае бин будет называться catWithAnnotation, однако есть исключение,
// если в названии класса первые две или более букв заглавные, например SQLTest, то название бина
// становится таким же как название класса, так и останется SQLTest.
// Вернемся в Lesson_7
@Component("catBean")
public class CatWithAnnotation implements Pet{

    public CatWithAnnotation() {
        System.out.println("Cat with annotation bean is created");
    }

    public void init() {
        System.out.println("Class CatWithAnnotation: init method");
    }

    public void destroy() {
        System.out.println("Class CatWithAnnotation: destroy method");
    }

    @Override
    public void say() {
        System.out.println("Miau");
    }
}
