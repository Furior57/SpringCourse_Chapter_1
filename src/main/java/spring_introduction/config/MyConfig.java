package spring_introduction.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Чтобы объяснить Spring где находится наш конфиг используются аннотации @Configuration и
// @ComponentScan. Первая объявляет этот класс конфигурационным, вторая указывает в
// каком пакете искать бины. Вернемся в Lesson_10
@Configuration
@ComponentScan("spring_introduction")
public class MyConfig {
}
