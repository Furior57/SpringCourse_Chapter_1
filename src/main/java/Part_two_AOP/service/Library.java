package Part_two_AOP.service;

import org.springframework.stereotype.Component;

@Component("libraryBean")
public class Library {

    public void getBook() {
        System.out.println("We take a book");
        System.out.println("---------------------------------------------------");
    }

    public void getBook(String bookName, int bookId) {
        System.out.println("We take a book:" + bookName+" id: "+ bookId);
        System.out.println("------------------------------------------------");
    }

    public void getBook(Book book) {
        System.out.println("We take a book object: "+book.getBookName());
        System.out.println("------------------------------------------------------");
    }

    public void setBookName() {
        System.out.println("Set book name");
    }

    public void setReaderName() {
        System.out.println("Set reader name");
    }

    public void addBook(String person_name, Book book) {
        System.out.println("Book is added in library");
        System.out.println("------------------------------------------------------------");
    }

    public void readBook() {
        System.out.println("Reading book");
        System.out.println("-----------------------------------------------------------");
    }

    public void returnBook() {
        System.out.println("Return book");
        System.out.println("--------------------------------------------------------");
    }

}
