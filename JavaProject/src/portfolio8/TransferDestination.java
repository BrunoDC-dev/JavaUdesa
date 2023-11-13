package portfolio8;

public class TransferDestination  extends TransferTransaction{

  public TransferDestination( Transfer transfer ) {
    super( transfer );
  }
  
  public TransferOrigin origin() {
    return transfer.origin();
  }

  public int valueForBalance() {
    return value();
  }

  public void accept( PortfolioVisitor visitor ) {
    visitor.visitTransferDestination( this );
  }

}
