package biblioteca;

import java.util.Objects;

public class Book {

    private String author;
    private String name;
    private String genre;

    public Book(String author, String name, String genre) {
        this.author = author;
        this.name = name;
        this.genre = genre;
    }
    public int hashCode() {
        return Objects.hash(author, name, genre);
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        return Objects.equals(author, other.author) &&
               Objects.equals(name, other.name) &&
               Objects.equals(genre, other.genre);
    }
    public String getAuthor() {
        return author;
    }
    public String getName() {
        return name;
    }
    public String getGenre() {
        return genre;
    }
}
