package Portfolio5ww;

import static org.junit.Assert.assertTrue;
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
  
  @Test void testBalanceOnNewPortfolio() {
	  assertEquals(0, new Portfolio().balance());
  }
  
  @Test void testBalancePortfolioWithAnAccount() {
	  Portfolio portfolio = new Portfolio();
	  portfolio.add(new Account().deposit(10));
	  assertEquals(10, portfolio.balance());
  }
  
  @Test void testBalancePortfolioAfterAddingAnAccountTwiceShouldFail() {
	  Portfolio portfolio = new Portfolio();
	  Account account = new Account().deposit(10);
	  portfolio.add(account);
	  assertThrows(Exception.class, () -> portfolio.add(account));
  }
  
  @Test void testBalancePortfolioWithAPortfolio() {
	  Portfolio portfolio = new Portfolio();
	  portfolio.add(new Account().deposit(10));
	  Portfolio otroPortfolio = new Portfolio();
	  otroPortfolio.add(portfolio);
	  assertEquals(10, portfolio.balance());
  }
  
  @Test public void testCannotAddPortfolioWithAnAccountThatIsAlreadyInPortfolio() {
	  Account account = new Account().deposit(10);
	  Portfolio portfolio1 = new Portfolio();
	  Portfolio portfolio2 = new Portfolio();
	  portfolio1.add(account);
	  portfolio2.add(account);
	  assertThrows( RuntimeException.class, () -> portfolio1.add(portfolio2) );
	  
  }

  @Test public void testTransferenciaCannotBeZero(){
        Account origin = new Account().deposit(10);
        Account destiny = new Account().deposit(20);
        assertThrows( RuntimeException.class, () -> new Transferencia(origin, destiny,0).enviar() );
  }

  @Test public void testTransferenciaCannotBeNegative(){
      Account origin = new Account().deposit(10);
      Account destiny = new Account().deposit(20);
      assertThrows( RuntimeException.class, () -> new Transferencia(origin, destiny, -10).enviar() );
  }

  @Test public void testTransferenciaCannotBeMadeIfOriginHasUnsufficientFunds(){
      Account origin = new Account().deposit(10);
      Account destiny = new Account().deposit(20);
      assertThrows( RuntimeException.class, () -> new Transferencia(origin, destiny, 20).enviar() );
  }

  @Test public void testTransferenciaCannotBeMadeToTheSameAccountThatIsOrigin(){
      Account origin = new Account().deposit(10);
      assertThrows( RuntimeException.class, () -> new Transferencia(origin, origin, 20).enviar() );
  }

  @Test public void testBalanceAfterTransferencia(){
      Account origin = new Account().deposit(10);
      Account destiny = new Account().deposit(20);
      Transferencia transferencia = new Transferencia(origin, destiny, 10).enviar();
      assertEquals(origin.balance() ,0);
      assertEquals(destiny.balance(), 30);
  }

  @Test public void testReportAfterTransferencia(){
      Account origin = new Account().deposit(10);
      Account destiny = new Account().deposit(20);
      Transferencia transferencia = new Transferencia(origin, destiny, 10).enviar();
      assertEquals(origin.report(), "Deposit: 10\nWithdraw: 10\nBalance: 0");
      assertEquals(destiny.report(), "Deposit: 20\nDeposit: 10\nBalance: 30");
  }

   @Test public void testFromWhichAccountWasTheTransferenciaMade(){
       Account origin = new Account().deposit(10);
       Account destiny = new Account().deposit(20);
       assertEquals(new Transferencia(origin, destiny, 10).enviar().getOrigin(), origin);
   }

  @Test public void testToWhichAccountWasTheTransferenciaMade(){
      Account origin = new Account().deposit(10);
      Account destiny = new Account().deposit(20);
      assertEquals(new Transferencia(origin, destiny, 10).enviar().getDestiny(), destiny);
  }
}
