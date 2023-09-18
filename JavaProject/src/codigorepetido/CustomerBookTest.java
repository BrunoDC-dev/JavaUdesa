package codigorepetido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerBookTest {
  @Test public void testAddingCustomerShouldNotTakeMoreThan50Milliseconds() {
  ensureTakesLesThanMilliseconds(()-> customerBookWith(johnLennon), 50);
  }
  @Test public void testRemovingCustomerShouldNotTakeMoreThan100Milliseconds() {
    ensureTakesLesThanMilliseconds(()-> customerBookWith(paulMcCartney).removeCustomerNamed(paulMcCartney), 100);
  }
  @Test public void testCanNotAddACustomerWithEmptyName() {
    CustomerBook customerBook =new CustomerBook();
    assertThrowsLike(()-> customerBook.addCustomerNamed(""), CustomerBook.CustomerNameCannotBeEmpty);
    assertTrue( customerBook.isEmpty() );
  }
  @Test public void testCanNotRemoveAnInvalidCustomer() {
    CustomerBook customerBook = customerBookWith(johnLennon);
      assertThrowsLike(()-> customerBook.removeCustomerNamed(paulMcCartney),  CustomerBook.CustomerNotFound);
      extracted2(customerBook,johnLennon); 
  }
  @Test public void testSuspendingACustomerShouldNotRemoveItFromCustomerBook() {
    CustomerBook customerBook = customerBookWithSuspended(paulMcCartney);
    extracted(customerBook, 0 , 1 ,1 , paulMcCartney , true);  
  }
  @Test public void testRemovingASuspendedCustomerShouldRemoveItFromCustomerBook() {
    CustomerBook customerBook = customerBookWithSuspended(paulMcCartney);
    customerBook.removeCustomerNamed( paulMcCartney );
    extracted(customerBook, 0, 0, 0, paulMcCartney,false);
  }
  @Test public void testAddingNewCustomerDoesAffectSuspendedStatus() {
    CustomerBook customerBook = customerBookWithSuspended(paulMcCartney);
    customerBook.addCustomerNamed( ringoStar );
    extracted(customerBook, 1, 1, 2, paulMcCartney, true);
  }
  @Test public void testCanNotSuspendAnInvalidCustomer() {
    CustomerBook customerBook = customerBookWith(johnLennon);
    assertThrowsLike(()-> customerBook.suspendCustomerNamed(GeorgeHarrison), CustomerBook.CannotSuspend);
    extracted2(customerBook,johnLennon);
  }
  @Test public void testCanNotSuspendAnAlreadySuspendedCustomer() {
    CustomerBook customerBook =customerBookWithSuspended(johnLennon);
    assertThrowsLike(()-> customerBook.suspendCustomerNamed(johnLennon),  CustomerBook.CannotSuspend);
    extracted2(customerBook, johnLennon);
  }
  private void extracted2(CustomerBook customerBook, String customerName) {
    assertEquals( 1, customerBook.numberOfCustomers() );
    assertTrue( customerBook.includesCustomerNamed( customerName ) );
  }
  private String paulMcCartney = "Paul McCartney";
  private String johnLennon = "John Lennon";
  private String ringoStar = "Ringo Star";
  private String GeorgeHarrison = "George Harrison";
  private CustomerBook customerBookWith(String customerName) {
    CustomerBook customerBook = new CustomerBook();
    customerBook.addCustomerNamed( customerName );
    return customerBook;
  }
  private CustomerBook customerBookWithSuspended (String customerName) {
    CustomerBook customerBook = customerBookWith( customerName );
    customerBook.suspendCustomerNamed( customerName );
    return customerBook;
  }
  private void extracted(CustomerBook customerBook , Number numberActiveCusotmers, Number numberSuspendCusotmers, Number numberCusotmers, String Customer, Boolean typeAssert) {
    assertEquals( numberActiveCusotmers, customerBook.numberOfActiveCustomers() );
    assertEquals( numberSuspendCusotmers, customerBook.numberOfSuspendedCustomers() );
    assertEquals( numberCusotmers, customerBook.numberOfCustomers() );
    if(typeAssert) {
      assertTrue( customerBook.includesCustomerNamed( Customer ) );
    }else { 
      assertFalse( customerBook.includesCustomerNamed( Customer ) );
    }
  }
  private void ensureTakesLesThanMilliseconds( Runnable run, int timeToMeassure ) {
    long millisecondsBeforeRunning = System.currentTimeMillis();
    run.run();
    assertTrue( System.currentTimeMillis() - millisecondsBeforeRunning < timeToMeassure );
  }
    private void assertThrowsLike( Executable executable, String message ) {
    assertEquals( message,assertThrows( Exception.class, executable ).getMessage() );}
}
//private void exceptionMethod(CustomerBook customerBook, Runnable action, String exceptionMessage) {
//  assertThrows(RuntimeException.class, () -> {action.run(); throw new RuntimeException(exceptionMessage);});
//}