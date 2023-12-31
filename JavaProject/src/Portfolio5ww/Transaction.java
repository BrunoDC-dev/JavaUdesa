package Portfolio5ww;

public abstract class Transaction {
  protected int value;

  public Transaction( int value ) {
    this.value = value;
  }
  
  public abstract int valueForBalance();
  public abstract String reportDetail();
  
  public int value() {
    return value;
  }
  
}
