package portfolio8;

import java.util.ArrayList;
import java.util.List;

public abstract class ReportVisitor implements PortfolioVisitor {

  protected List<String> report = new ArrayList();
  
  public String list( Accountable target ) {
    target.accept( this );
    
    return String.join("\n", report);
  }

  public abstract void visitPortfolio( Portfolio target );
  public abstract void visitAccount( Account target );
  public abstract void visitDeposit( Deposit deposit );
  public abstract void visitWithdraw( Withdraw withdraw );
  public abstract void visitTransferDestination( TransferDestination transferDestination );
  public abstract void visitTransferOrigin( TransferOrigin transferOrigin );
}
