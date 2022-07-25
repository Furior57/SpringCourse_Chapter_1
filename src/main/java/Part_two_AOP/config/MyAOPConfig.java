package Part_two_AOP.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
// К нашему конфигу мы добавили аннотацию @EnableAspectJAutoProxy, чтобы мы могли пользоваться SpringAOP.
// Теперь нам необходимо создать класс, который и будет AOP-proxy, в пакете service создадим еще один пакет
// aspects и там создадим класс LoggingAspect, перейдем в него.
@Configuration
@ComponentScan("Part_two_AOP")
@EnableAspectJAutoProxy
public class MyAOPConfig {
}
