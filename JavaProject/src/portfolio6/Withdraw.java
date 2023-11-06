package portfolio6;

public class Withdraw extends Transaction {

  public Withdraw( int value ) {
    super( value );
  }

  public int valueForBalance() {
    return value * -1;
  }

  public String reportDetail() {
    return "Withdraw: " + value;
  }

}
