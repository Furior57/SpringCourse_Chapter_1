package Part_two_AOP.service;

import org.springframework.stereotype.Component;

@Component("libraryBean")
public class Library {

    public void getBook() {
        System.out.println("We take a book");
    }

    public void getBook(String bookName, int bookId) {
        System.out.println("We take a book:" + bookName+" id: "+ bookId);
    }

    public void getBook(Book book) {
        System.out.println("We take a book object: "+book.getBookName());
    }

    public void setBookName() {
        System.out.println("Set book name");
    }

    public void setReaderName() {
        System.out.println("Set reader name");
    }

    public void readBook() {
        System.out.println("Reading book");
    }

    public void returnBook() {
        System.out.println("Return book");
    }

}
