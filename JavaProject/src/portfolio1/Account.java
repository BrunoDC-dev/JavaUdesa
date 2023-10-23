package portfolio1;

import java.util.ArrayList;
import java.util.List;

public class Account {
  private List<Integer> transactions = new ArrayList();
  
  public int balance() {
    return transactions.stream().reduce(0, (a, b) -> a + b);
  }

  public Account deposit( int anAmount ) {
    transactions.add( anAmount );
    return this;
  }

  public Account withdraw( int anAmount ) {
    if (balance() - anAmount < 0) {
      throw new RuntimeException( "not enough money in the account" );
    }
    
    transactions.add( anAmount * -1 );
    return this;
  }

  public String report() {
    List<String> report = new ArrayList();
    
    transactions.forEach( (transaction) -> {
      if ( transaction > 0) 
        report.add( "Deposit: " + transaction );
      else
        report.add( "Withdraw: " + (transaction * -1) );
    });
    
    report.add( "Balance: " + balance() );
    
    return String.join("\n", report);
  }
}
