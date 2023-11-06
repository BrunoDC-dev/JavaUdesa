package portfolio6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Account extends Accountable {
  private List<Transaction> transactions = new ArrayList();
  
  public int balance() {
    return transactions.stream()
        .map( (transaction) -> transaction.valueForBalance() )
        .reduce(0, (a, b) -> a + b);
  }
  
  public Account deposit( int anAmount ) {
    return register( new Deposit( anAmount ) );
  }

  public Account withdraw( int anAmount ) {
    if (balance() - anAmount < 0) {
      throw new RuntimeException( "not enough money in the account" );
    }
    
    return register( new Withdraw( anAmount ) );
  }

  public Account register( Transaction trannsactionToRegister ) {
     transactions.add( trannsactionToRegister );
     return this;
  }

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
