package Portfolio5ww;

public class Deposit extends Transaction {

  public Deposit( int value ) {
    super( value );
  }

  public int valueForBalance() {
    return value;
  }

  public String reportDetail() {
    return "Deposit: " + value;
  }

}