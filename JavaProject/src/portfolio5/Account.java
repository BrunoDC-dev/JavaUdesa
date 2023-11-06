package portfolio5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Account implements Accountable {
  private List<Transaction> transactions = new ArrayList();
  
  public int balance() {
    return transactions.stream()
        .map( (transaction) -> transaction.valueForBalance() )
        .reduce(0, (a, b) -> a + b);
  }

  public Account deposit( int anAmount ) {
    transactions.add( new Deposit( anAmount ) );
    return this;
  }

  public Account withdraw( int anAmount ) {
    if (balance() - anAmount < 0) {
      throw new RuntimeException( "not enough money in the account" );
    }
    
    transactions.add( new Withdraw( anAmount ) );
    return this;  }

  public String report() { return report( "" ); }
  public String report( String prefix ) {
    List<String> report = new ArrayList();
    
    report.add( prefix + "Cuenta:" );
    transactions.forEach( (transaction) -> {
      report.add( prefix + "  " + transaction.reportDetail() );
    });

    report.add( prefix + "Balance: " + balance() );
    
    return String.join("\n", report);
  }
  
  public boolean contains( Accountable anAccountable ) {
    return equals( anAccountable );
  }

  public List<Account> accounts() {
    return Arrays.asList( this );
  }
}
