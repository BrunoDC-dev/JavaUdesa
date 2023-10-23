package portfolio2;

import java.util.ArrayList;
import java.util.List;

public class Account {
  private List<Transaction> transactions = new ArrayList();
  
  public int balance() {
    return transactions.stream()
        .map( (transaction) -> { 
          if ( transaction.type() == 'D' ) {
            return transaction.value();
          } else {
            return transaction.value() * -1;
          }
        } )
        .reduce(0, (a, b) -> a + b);
  }

  public Account deposit( int anAmount ) {
    transactions.add( new Transaction( 'D', anAmount ) );
    return this;
  }

  public Account withdraw( int anAmount ) {
    if (balance() - anAmount < 0) {
      throw new RuntimeException( "not enough money in the account" );
    }
    
    transactions.add( new Transaction( 'W', anAmount ) );
    return this;
  }

  public String report() {
    List<String> report = new ArrayList();
    
    transactions.forEach( (transaction) -> {
      if ( transaction.type() == 'D' ) 
        report.add( "Deposit: " + transaction.value() );
      else
        report.add( "Withdraw: " + transaction.value() );
    });
    
    report.add( "Balance: " + balance() );
    
    return String.join("\n", report);
  }
}
