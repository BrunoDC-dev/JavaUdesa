package portfolio2;

public class Transaction {
  private int value;
  private char type;

  public Transaction( char type, int value ) {
    this.type = type;
    this.value = value;
  }
   
  public char type() { return type; }

  public int value() {
    return value;
  }  

}
