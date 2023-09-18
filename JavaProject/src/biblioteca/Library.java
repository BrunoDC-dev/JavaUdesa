package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {

  private List<Book> books;
  private List<Book> rented;

  public Library() {
    books = new ArrayList<>();
    rented = new ArrayList<>();
  }

  public void addBook( Book book ) {
    if (books.contains( book ) || rented.contains( book ) ) { 
      throw new RuntimeException( "book already registered" );
    }
    books.add( book );
  }
  
  public void rentBook( Book book ) {
    if (!books.contains( book )) { 
      throw new RuntimeException( "book unavailable" );
    }
    rented.add( book );
    for (int i = 0; i < books.size(); i++) {
      if (books.get( i ).equals( book )) {
        books.remove( i );
        break;
      }
    }
  }

  public void returnBook( Book book ) {
    if (!rented.contains( book )) { 
      throw new RuntimeException( "book not rented" );
    }
    books.add( book );
    for (int i = 0; i < rented.size(); i++) {
      if (rented.get( i ).equals( book )) {
        rented.remove( i );
        break;
      }
    }
  }

  public void removeBook( Book book ) {
    if (!books.contains( book ) && !rented.contains( book ) ) { 
      throw new RuntimeException( "cannot remove, book not registered" ); 
    }

    if ( books.contains( book ) ) {
      for (int i = 0; i < books.size(); i++) {
        if (books.get( i ).equals( book )) {
          books.remove( i );
          break;
        }
      }
    }
    
    if ( rented.contains( book ) ) {
      for (int i = 0; i < rented.size(); i++) {
        if (rented.get( i ).equals( book )) {
          rented.remove( i );
          break;
        }
      }
    }
  }

  public List<Book> searchAvailableByGenre( String genre ) {
    List<Book> result = new ArrayList<>();
    for (Book book : books) {
      if (book.getGenre().equals( genre )) { result.add( book ); }
    }
    return result;
  }

  public List<Book> searchAvailableByAuthor( String author ) {
    List<Book> result = new ArrayList<>();
    for (Book book : books) {
      if (book.getAuthor().equals( author )) { result.add( book ); }
    }
    return result;
  }

  public int available() {
    return books.size();
  }
}
