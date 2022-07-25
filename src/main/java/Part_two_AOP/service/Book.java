package Part_two_AOP.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Book {
    @Value("Pride and prejudice")
    private String bookName;

    public String getBookName() {
        return bookName;
    }

}
