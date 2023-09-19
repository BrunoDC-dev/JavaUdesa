package biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class Library {
    public List<Book> books;
    private List<Book> rented;
    public Library() {
        books = new ArrayList<>();
        rented = new ArrayList<>();
    }
    public void addBook(Book book) {
        if (isBookRegistered(book)) {
            throw new RuntimeException("book already registered");
        }
        books.add(book);
    }
    public void rentBook(Book book) {
        if (!isBookAvailable(book)) {
            throw new RuntimeException("book unavailable");
        }
        rented.add(book);
        books.remove(book);
    }
    public void returnBook(Book book) {
        if (!isBookRented(book)) {
            throw new RuntimeException("book not rented");
        }
        rented.remove(book);
        books.add(book);
    }
    public void removeBook(Book book) {
        if (!isBookRegistered(book)) {
            throw new RuntimeException("cannot remove, book not registered");
        }
        books.removeIf(b -> Objects.equals(b, book));
        rented.removeIf(b -> Objects.equals(b, book));
    }
    public List<Book> searchAvailableByGenre(String genre) {
        return searchAvailableBooks(book -> Objects.equals(book.getGenre(), genre));
    }
    public List<Book> searchAvailableByAuthor(String author) {
        return searchAvailableBooks(book -> Objects.equals(book.getAuthor(), author));
    }
    public List<Book> searchAvailableBooks(Predicate<Book> predicate) {
      return books.stream().filter(predicate).collect(Collectors.toList());
    }
    public int available() {
        return books.size();
    }
    private boolean isBookRegistered(Book book) {
        return books.contains(book) || rented.contains(book);
    }
    private boolean isBookAvailable(Book book) {
        return books.contains(book);
    }
    private boolean isBookRented(Book book) {
        return rented.contains(book);
    }
}
