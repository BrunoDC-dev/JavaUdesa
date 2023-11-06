package portfolio4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class PortfolioTest {

  @Test void testBalanceOnNewAccount() {
    assertEquals( 0, new Account().balance() );
  }

  @Test void testBalanceAfterADeposit() {
    assertEquals( 10, new Account().deposit( 10 ).balance() );
  }
  
  @Test void testBalanceAfterAWithdraw() {
    Account account = new Account();
    account.deposit( 10 )
           .withdraw( 5 );
    assertEquals( 5, account.balance() );
  }

  @Test void testWithdrawFailsIfNoResidue() {
    Account account = new Account();
    assertThrows( RuntimeException.class, () -> account.withdraw( 5 ) );
    assertEquals( 0, account.balance() );
  }
  

  @Test void testReportAfterADeposit() {
    assertEquals( "Deposit: 10\n" +
                  "Balance: 10",
                  new Account().deposit( 10 ).report() );
  }
  
  @Test void testReportAfterAWithdraw() {
    Account account = new Account();
    account.deposit( 10 )
           .withdraw( 5 );
    assertEquals( "Deposit: 10\n" +
                  "Withdraw: 5\n" +
                  "Balance: 5", account.report() );
  }

  @Test void testBalanceOnNewPotfolio() {
    assertEquals( 0, new Portfolio().balance() );
  }
  
  @Test void testBalanceAfterAddingAnAccount() {
    Portfolio portfolio = new Portfolio();
    portfolio.add( new Account().deposit( 10 ) );
    assertEquals( 10, portfolio.balance() );
  }

  @Test void testBalanceAfterAddingAnAccountTwice() {
    Portfolio portfolio = new Portfolio();
    Account account = new Account().deposit( 10 );
    portfolio.add( account );
    assertThrows( Exception.class, () -> portfolio.add( account ) );
  }

  @Test void testBalanceAfterAddingAPortfolio() {
    Portfolio portfolio = new Portfolio();
    Account account = new Account().deposit( 10 );
    portfolio.add( account );
    Portfolio anotherPortfolio = new Portfolio();
    anotherPortfolio.add( portfolio );
    assertEquals( 10, portfolio.balance() );
  }
  @Test void testReportPortfolioComplex(){
    String report = new Portfolio().add(new Portfolio().add(new Account().deposit(10)))
                                                      .add(new Account().deposit(10))
                                                      .add(new Portfolio().add(new Account().deposit(10))).report(" ") ;
    System.out.println(report);
    /*assertEquals( "Portfolio:\n"+
                  " Portfolio:\n"+
                  "  Cuenta:\n"+
                  "   Deposit: 10\n"+
                  " Cuenta:\n" + 
                  "  Deposit: 10\n"+
                  " Portfolio:\n"+
                  "  Cuenta:\n"+
                  "   Deposit: 10\n", new Portfolio().add(new Portfolio().add(new Account().deposit(10)))
                                                      .add(new Account().deposit(10))
                                                      .add(new Portfolio().add(new Account().deposit(10))).report(" ") );
  */
                                                    }
}
