package portfolio6;

public abstract class Transaction {
  protected int value;

  public Transaction() {
  }
  public Transaction( int value ) {
    this.value = value;
  }
  
  public abstract int valueForBalance();
  public abstract String reportDetail();
  
  public int value() {
    return value;
  }
  
}
