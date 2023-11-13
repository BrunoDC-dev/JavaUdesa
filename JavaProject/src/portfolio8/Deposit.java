package portfolio8;

public class Deposit extends Transaction {

  public Deposit( int value ) {
    super( value );
  }

  public int valueForBalance() {
    return value;
  }

  public void accept( PortfolioVisitor visitor ) {
    visitor.visitDeposit( this );
  }

}
