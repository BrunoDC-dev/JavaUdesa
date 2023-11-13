package portfolio8;

public class TransferOrigin extends TransferTransaction {

  public TransferOrigin( Transfer transfer ) {
    super( transfer );
  }
  
  public TransferTransaction destination() {
    return transfer.destination();
  }

  public int valueForBalance() {
    return - value();
  }

  public void accept( PortfolioVisitor visitor ) {
    visitor.visitTransferOrigin( this );
  }

}
