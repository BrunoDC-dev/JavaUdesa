package portfolio8;


public class SummaryTreeReport extends ReportVisitor {
  private String prefix = "";
  
  public static String report( Accountable anAccount ) {
    SummaryTreeReport report = new SummaryTreeReport();
    return report.list( anAccount );
  }

  public void visitPortfolio( Portfolio target ) {
    report.add( prefix + "Portfolio:" );
    String oldPrefix = prefix;
    prefix = prefix + "  ";
    target.visitAccountsOn( this );
    prefix = oldPrefix;
  }

  public void visitAccount( Account target ) {
    report.add( prefix + "Cuenta:" );
    target.visitTransactionsOn( this );
  }

  public void visitDeposit( Deposit deposit ) {
    report.add( "Deposit: " + deposit.value() );  
  }

  public void visitWithdraw( Withdraw withdraw ) {
    report.add( "Withdraw: " + withdraw.value() );      
  }

  public void visitTransferDestination( TransferDestination transferDestination ) {
    report.add( "Depósito por transferencia de: " + transferDestination.value() );      
  }
  public void visitTransferOrigin( TransferOrigin transferOrigin ) {
    report.add( "Débito por transferencia de: " + transferOrigin.value() );      
  }

}
