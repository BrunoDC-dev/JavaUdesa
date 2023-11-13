package portfolio8;

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

  
  public boolean contains( Accountable anAccountable ) {
    return equals( anAccountable );
  }

  public List<Account> accounts() {
    return Arrays.asList( this );
  }

  public void accept( PortfolioVisitor visitor ) {
    visitor.visitAccount( this );
  }

  public void visitTransactionsOn( PortfolioVisitor summaryTreeReport ) {
    transactions.forEach( (transaction) -> {
      transaction.accept( summaryTreeReport );
    });
  }
}
