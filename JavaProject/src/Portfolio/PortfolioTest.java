package Portfolio;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PortfolioTest {

    @Test public void testPortofolioInitializesEmpty (){
        assertEquals(0, new Account().getBalance());
    }

    @Test public void testPortofolioDeposit (){
        assertEquals(100, new Account().Deposit(100).getBalance());
    }

    @Test public void testPortofolioWithdraw (){
        assertEquals(50, new Account().Deposit(100).Withdraw(50).getBalance());
    }
    @Test void testPortofolioWithdrawFailsIfNoresidue (){
        Account account = new Account();
        assertThrows(RuntimeException.class, () -> account.Withdraw(50));
        assertEquals(0, account.getBalance());
    }
    @Test void testReporteAfterDeposit (){
        assertEquals("Deposit: 10 \n" + "Balance: 10", new Account().Deposit(10).report());
    }
    @Test void testReporteAfterWithdraw (){
        assertEquals("Deposit:\n"+"Withdraw: 50 \n" + "Balance: 50", new Account()Deposit(100).Withdraw(50).report());
    }
    @Test void testReporteAfterDepositAndWithdraw (){
        assertEquals("Deposit:50\n"+"Deposit:50 \n"+"Withdraw:25 \n" + "Withdraw:25\n"+ "Balance: 50", new Account()Deposit(10).Withdraw(50).report());
    }
}