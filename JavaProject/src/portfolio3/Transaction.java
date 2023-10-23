package portfolio3;


public abstract class Transaction {
  protected int value;
  protected char type;

  static public Transaction deposit( int value ) { return new Deposit  ( 'D', value );  }
  static public Transaction withdraw( int value ) { return new Withdraw( 'W', value );  }

  public Transaction( char type, int value ) {
    this.type = type;
    this.value = value;
  }
  
  public abstract boolean isDeposit();
  
  public abstract int value();
  
  public abstract int valueForBalance() ;
  
  public abstract String reportDetail() ;
  

}
