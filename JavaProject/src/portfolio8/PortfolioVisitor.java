package portfolio8;

public interface PortfolioVisitor {

  public void visitPortfolio( Portfolio target );
  public void visitAccount( Account target );
  public void visitDeposit( Deposit deposit );
  public void visitWithdraw( Withdraw withdraw );
  public void visitTransferDestination( TransferDestination transferDestination );
  public void visitTransferOrigin( TransferOrigin transferOrigin );
}
