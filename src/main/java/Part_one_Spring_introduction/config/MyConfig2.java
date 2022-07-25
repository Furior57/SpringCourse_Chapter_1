package Part_one_Spring_introduction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import Part_one_Spring_introduction.animal.Cat;
import Part_one_Spring_introduction.animal.PersonWithJava2;
import Part_one_Spring_introduction.animal.Pet;

@Configuration
// этой записью мы указываем путь к файлу со значениями, задание значений в поля пишется так же как
// и до этого
@PropertySource("classpath:myApp.properties")
public class MyConfig2 {
    // Мы оставляем аннотацию @Configuration, но так как поиска в пакете никакого не будет
    // нам нужно самим указать всю логику в этом классе.
    // Для начала определим бин, для этого создадим метод возвращающий обьект Cat
    // и пометим его аннотацией @Bean, id этого бина - название метода.
    @Bean
    @Scope("singleton")
    public Pet catBean() {
        return new Cat();
    }

    // Теперь посмотрим как внедрять зависимости
    @Bean
    public PersonWithJava2 personBean() {
        // Конструктор этого класса требует обьект Pet, мы передаем в него метод возвращающий
        // Cat, собственно все. Аннотация @Autowired в таком способе не используется, все
        // зависимости мы прописываем вручную.
        return new PersonWithJava2(catBean());
    }


}
