package biblioteca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

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
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    assertEquals( 1, library.available() );
  }

  @Test public void testLibraryWhitABookFindsItsGenre() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    assertTrue( library.searchAvailableByGenre( new Book( "Martin Fowler", "Java4Dummies", "IT" ).getGenre() ).contains( new Book( "Martin Fowler", "Java4Dummies", "IT" ) ) );
  }

  @Test public void testLibraryWhitABookFindsItsAuthor() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    assertTrue( library.searchAvailableByAuthor( new Book( "Martin Fowler", "Java4Dummies", "IT" ).getAuthor() ).contains( new Book( "Martin Fowler", "Java4Dummies", "IT" ) ) );
  }

  @Test public void testLibraryWhitABookRemoved() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );

    assertEquals( 1, library.available() );

    library.removeBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    assertEquals( 0, library.available() );
  }

  @Test public void testLibraryWhitNoBooktoRemove() {
    Library library = new Library();
        
    try {
      library.removeBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
      fail( "Ups" );
    } catch (Exception e) {
      assertEquals( "cannot remove, book not registered", e.getMessage() );
    }
  }
  
  @Test public void testLibraryWhitTwiceABook() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
        
    try {
      library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
      fail( "Ups" );
    } catch (Exception e) {
      assertEquals( "book already registered", e.getMessage() );
      assertEquals( 1, library.available() );
    }
  }

  @Test public void testFiltersAuthorsOnLibrary() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    library.addBook( new Book( "Chamond Liu", "pythonForDummies", "IT" ) );
    library.addBook( new Book( "Martin Fowler", "TolkienUniverse", "SiFi" ) );

    assertEquals( 3, library.available() );
    assertTrue( library.searchAvailableByAuthor( "Martin Fowler" ).contains( new Book( "Martin Fowler", "Java4Dummies", "IT" ) ) );
    assertTrue( library.searchAvailableByAuthor( "Martin Fowler" ).contains( new Book( "Martin Fowler", "TolkienUniverse", "SiFi" ) ) );
    assertFalse( library.searchAvailableByAuthor( "Martin Fowler" ).contains( new Book( "Chamond Liu", "pythonForDummies", "IT" ) ) );
  }

  @Test public void testFiltersGenreOnLibrary() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    library.addBook( new Book( "Chamond Liu", "pythonForDummies", "IT" ) );
    library.addBook( new Book( "Martin Fowler", "TolkienUniverse", "SiFi" ) );

    assertEquals( 3, library.available() );
    assertTrue( library.searchAvailableByGenre( "IT" ).contains( new Book( "Martin Fowler", "Java4Dummies", "IT" ) ) );
    assertTrue( library.searchAvailableByGenre( "IT" ).contains( new Book( "Chamond Liu", "pythonForDummies", "IT" ) ) );
    assertFalse( library.searchAvailableByGenre( "IT" ).contains( new Book( "Martin Fowler", "TolkienUniverse", "SiFi" ) ) );
  }

  // nuevo feature, alquiler de libros!
  
  @Test public void testSucessfulRent() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    
    library.rentBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    
    assertEquals( 0, library.available() );
  }

  @Test public void testUnexistentRent() {
    Library library = new Library();
        
    try {
      library.rentBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
      fail( "Ups" );
    } catch (Exception e) {
      assertEquals( "book unavailable", e.getMessage() );
    }
  }

  @Test public void testRentTwice() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    
    library.rentBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
      
    try {
      library.rentBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
      fail( "Ups" );
    } catch (Exception e) {
      assertEquals( "book unavailable", e.getMessage() );
    }
  }

  @Test public void testRestoreRented() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    
    library.rentBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );

    library.returnBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
      
    assertEquals( 1, library.available() );
  }

  @Test public void testRestoreUnrented() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    
    try {
      library.returnBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
      fail( "Ups" );
    } catch (Exception e) {
      assertEquals( "book not rented", e.getMessage() );
    }
     
    assertEquals( 1, library.available() );
  }

  @Test public void testLibraryWhitARentedBookRemoved() {
    Library library = new Library();
    library.addBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );

    library.rentBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );

    library.removeBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
    try {
      library.returnBook( new Book( "Martin Fowler", "Java4Dummies", "IT" ) );
      fail( "Ups" );
    } catch (Exception e) {
      assertEquals( "book not rented", e.getMessage() );
    } 
  }
}
