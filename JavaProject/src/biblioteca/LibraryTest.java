package biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.List;
public class LibraryTest {
  @Test public void testNewLibraryHasNoAuthors() {
    assertTrue( new Library().searchAvailableByAuthor( "Anonimous" ).isEmpty() );
  }
  @Test public void testNewLibraryHasNoGenres() {
    assertTrue( new Library().searchAvailableByGenre( "SiFy" ).isEmpty() );
  }
  @Test public void testNewLibraryHasNoStock() {
    assertEquals( 0, new Library().available() );
  }
  @Test public void testLibraryWhitABookHasStock() {
    Library library = libraryWithElement(Book1);
    assertEquals( 1, library.available() );
  }
  @Test public void testLibraryWhitABookFindsItsGenre() {
    Library library = libraryWithElement(Book1);
    assertTrue( library.searchAvailableByGenre( Book1.getGenre() ).contains( Book1 ) );
  }
  @Test public void testLibraryWhitABookFindsItsAuthor() {
    Library library = libraryWithElement(Book1);
    assertTrue( library.searchAvailableByAuthor( Book1.getAuthor() ).contains( Book1 ) );
  }
  @Test public void testLibraryWhitABookRemoved() {
    Library library = libraryWithElement(Book1);
    assertEquals( 1, library.available() );
    library.removeBook( Book1 );
    assertEquals( 0, library.available() );
  }
  @Test public void testLibraryWhitNoBooktoRemove() {
    Library library = new Library();
    assertThrows(Exception.class, () -> library.removeBook( Book1 ),"cannot remove, book not registered");
  }
  
  @Test public void testLibraryWhitTwiceABook() {
    Library library = libraryWithElement(Book1);
    assertThrows(Exception.class, () ->  library.addBook( Book1 ), "book already registered");
    assertEquals( 1, library.available() );
  }
  @Test public void testFiltersAuthorsOnLibrary() {
    Library library = Librarywith3books();
    search(library,library.searchAvailableByAuthor( "Martin Fowler" ), Book1, Book3, Book2);
  }
  @Test public void testFiltersGenreOnLibrary() {
    Library library = Librarywith3books();
    search(library ,library.searchAvailableByGenre( "IT" ), Book1, Book2, Book3);
  }
  private void search (Library library,List<Book> result, Book FirstBook, Book SecondBook , Book ThirdBook){
    assertEquals( 3, library.available() );
    assertTrue( result.contains( FirstBook) );
    assertTrue( result.contains( SecondBook) );
    assertFalse( result.contains( ThirdBook ) );
  }
  // nuevo feature, alquiler de libros!
  @Test public void testSucessfulRent() {
    Library library = LibraryWithRentedBook(Book1);
    assertEquals( 0, library.available() );
  }
  @Test public void testUnexistentRent() {
    Library library = new Library();
    assertThrows(Exception.class, () ->  library.rentBook( Book1 ), "book unavailable");
  }
  @Test public void testRentTwice() {
    Library library = LibraryWithRentedBook(Book1);
    assertThrows(Exception.class, () ->  library.rentBook( Book1 ), "book unavailable");
  }
  @Test public void testRestoreRented() {
    Library library = LibraryWithRentedBook(Book1);
    library.returnBook( Book1 );
    assertEquals( 1, library.available() );
  }
  @Test public void testRestoreUnrented() {
    Library library = libraryWithElement(Book1);
    assertThrows(Exception.class, () ->  library.returnBook( Book1 ),"book not rented");
    assertEquals( 1, library.available() );
  }
  @Test public void testLibraryWhitARentedBookRemoved() {
    Library library = LibraryWithRentedBook(Book1);
    library.removeBook( Book1 );
    assertThrows(Exception.class, () ->  library.returnBook( Book1 ), "book not rented");
  }
  Book Book1 = CreateBook("Martin Fowler", "Java4Dummies", "IT");
  Book Book2 = CreateBook("Chamond Liu", "pythonForDummies", "IT");
  Book Book3 = CreateBook("Martin Fowler", "TolkienUniverse", "SiFi");
  private Library libraryWithElement(Book book) {
    Library library = new Library();
    library.addBook(book );
    return library;
  }
  private Library LibraryWithRentedBook(Book book) {
    Library library = libraryWithElement(book);
    library.rentBook( book );
    return library;
  }
    private Library Librarywith3books() {
    Library library = libraryWithElement(Book1);
    library.addBook( Book2 );
    library.addBook( Book3 );
    return library;
  }
  private Book CreateBook(String author, String name,String genre) {
    return new Book( author,name, genre );
  }
}