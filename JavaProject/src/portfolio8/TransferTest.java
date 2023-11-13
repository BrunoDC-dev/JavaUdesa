package portfolio8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class TransferTest {

  @Test void testValueOfTransfer() {
    Transfer transfer = transferOf( 10 );
    assertEquals( 10, transfer.value() );
  }
  
  @Test void testValueOfTransferShouldBePositive() {
    assertThrows( RuntimeException.class, () -> transferOf( 0 ) );
  }

  @Test void testValueOfOriginIsTransferValue() {
    Transfer transfer = transferOf( 10 );
    assertEquals( transfer.value(), transfer.origin().value() );
  }

  @Test void testValueOfDestinationIsTransferValue() {
    Transfer transfer = transferOf( 10 );
    assertEquals( transfer.value(), transfer.destination().value() );
  }

  @Test void testOriginIsDestinationOrigin() {
    Transfer transfer = transferOf( 10 );
    assertEquals( transfer.origin(), transfer.destination().origin() );
  }
  
  @Test void testDestinationIsOriginDestination() {
    Transfer transfer = transferOf( 10 );
    assertEquals( transfer.destination(), transfer.origin().destination() );
  }
 
  @Test void testTransferRegistry() {
    Account anAccount = accountWith10();
    Account anotherAccount = accountWith10();
    
    transferRegister( 10, anAccount, anotherAccount );
    assertEquals( 0, anAccount.balance() );
    assertEquals( 20, anotherAccount.balance() );
  }
   
  private Transfer transferRegister( int anAmmount, Account originAccount, Account destinationAccount ) {
    return Transfer.register( anAmmount, originAccount, destinationAccount );
  }

  private Transfer transferOf( int anAmmount ) {
    return new Transfer( anAmmount );
  }

  private Account accountWith10() {
    return new Account().deposit( 10 );
  }


  
}
