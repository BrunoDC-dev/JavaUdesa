package portfolio5;

import java.util.ArrayList;
import java.util.List;

public class Portfolio implements Accountable {
  private List<Accountable> accounts = new ArrayList();
  
  public int balance() {
    return accounts.stream()
        .map( (transaction) -> transaction.balance() )
        .reduce(0, (a, b) -> a + b);
  }

  public String report() { return report( "" ); }
  public String report( String prefix ) {
    List<String> report = new ArrayList();
    
    report.add( prefix + "Portfolio:" );
    accounts.forEach( (accountable) -> {
        report.add( accountable.report( prefix + "  ") );
    });
    
    return String.join("\n", report);
  }
  
  public Portfolio addAccount( Accountable anAccount ) {
    if ( contains( anAccount ) ) throw new RuntimeException();
    
    accounts.add( anAccount );  
    return this;
  }

  public boolean contains( Accountable anAccount ) {
    List  reminder = accounts();
    reminder.retainAll( anAccount.accounts() );
    return !reminder.isEmpty();
  }
  
  public List<Account> accounts() {
    return accounts.stream().map( (accountable) -> accountable.accounts() )
                            .reduce( new ArrayList(), (a, b) -> { a.addAll( b ); return a; } );
  }
  
}
