package portfolio6;

public class Transfer {

    private int value;
    private TransferOrigin origin;
    private TransferDestination destination;
    
    public static Transfer register( int anAmmount, Account originAccount, Account destinationAccount ) {
      Transfer transfer = new Transfer( anAmmount );
      originAccount.register( transfer.origin() );
      destinationAccount.register( transfer.destination );
      return transfer;
    }

    public Transfer( int anAmmount ) {
      if ( anAmmount <= 0 ) throw new RuntimeException();
      
      value = anAmmount;
      origin = new TransferOrigin( this );
      destination = new TransferDestination( this );
    }

    public int value() {
      return value;
    }

    public TransferOrigin origin() {
      return origin;
    }

    public TransferDestination destination() {
      return destination;
    }

}
