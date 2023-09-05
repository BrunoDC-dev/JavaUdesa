package garage2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class GarageTest {

  @Test public void testNewParkingIsEmpty() {
    assertTrue( new Garage( 3 ).isEmpty() );
  }

  @Test public void testNewParkingHasNoMoney() {
    assertEquals( 0, new Garage( 3 ).totalFees() );
  }

  @Test public void testParkOneCar() {
    assertFalse( garageWithAlvins( 3 ).isEmpty() );
  }

  @Test public void testOneCarFee() {
    assertEquals( 10, garageWithAlvins( 3 ).totalFees() );
  }

  @Test public void testManyCarsFees() {
    Garage garage = garageWithAlvins( 3 );
    garage.parkCar( theodoresCar() );
    
    assertEquals( 20, garage.totalFees() );
  }

  @Test public void testInOutOneCar() {
    Garage garage = garageWithAlvins( 3 );

    assertFalse( garage.isEmpty() );
    garage.unparkCar( alvinsCar() );
    
    assertTrue( garage.isEmpty() );
  }

  @Test public void testInOutOneCarFees() {
    Garage garage = garageWithAlvins( 3 );

    assertFalse( garage.isEmpty() );
    garage.unparkCar( alvinsCar() );

    assertEquals( 10, garage.totalFees() );
  }

  @Test public void testUnparkAnEmptyLot() {
    assertThrowsLike( () -> new Garage( 3 ).unparkCar( alvinsCar() ), 
                      Garage.MissingCar );
  }

  @Test public void testFullLot() {
    assertTrue( garageWithAlvins( 1 ).isFull() );
  }
  
  @Test public void testOverFillLot() {
    Garage garage = garageWithAlvins( 1 );
 
    assertThrowsLike( () -> garage.parkCar( alvinsCar() ), 
                      Garage.NoSpaceAvaliable );
    
    assertEquals( 1, garage.getNumCars() );
  }
  
  @Test public void testParkOneCarTwice() {
    Garage garage = garageWithAlvins( 3 );

    assertThrowsLike( () -> garage.parkCar( alvinsCar() ), Garage.TwinCars );
    assertEquals( 1, garage.getNumCars() );
  }

  @Test public void testRotateLot() {
    Garage garage = garageWithAlvins( 1 );
    garage.unparkCar( alvinsCar() );
 
    garage.parkCar( alvinsCar() );
    assertEquals( 1, garage.getNumCars() );
    assertEquals( 20, garage.totalFees() );
  }
  
  private void assertThrowsLike( Executable executable, String message ) {
    assertEquals( message, 
                  assertThrows( Exception.class, executable )
                   .getMessage() );
  }
  
  private Car alvinsCar() {
    return new Car( "ABC123" );
  }

  private Car theodoresCar() {
    return new Car( "DEF456" );
  }

  public Garage garageWithAlvins( int size ) {
    Garage garage = new Garage( size );
    garage.parkCar( alvinsCar() );
    return garage;
  }
  
}
