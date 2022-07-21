package aop.lessons;

import aop.config.MyAOPConfig;
import aop.service.Library;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Lesson_13_AspectBefore {
    // Прошлый урок был сугубо теоретическим, теперь немного попрактикуемся.
    // Мы создали некую схему классов. В пакете service у нас есть класс Library помеченный как
    // @Component, в пакете config у нас есть класс MyAOPConfig, помеченный как @Configuration
    // и @ScanComponent("aop"), то есть работаем мы сейчас только в пакете aop.
    // Создадим контекст. Теперь перейдем в MyAOPConfig.
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyAOPConfig.class);
        Library library = context.getBean("libraryBean", Library.class);
        library.getBook();

        context.close();
    }
}
