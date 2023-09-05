package garage2;

import java.util.ArrayList;
import java.util.List;

public class Garage {

  public static String TwinCars = "Twin Cars!";
  public static String NoSpaceAvaliable = "No space available";
  public static String MissingCar = "Missing car!";
      
  private int capacity;
  private List<Car> cars = new ArrayList<>();
  private int fees = 0;

  public Garage( int size ) {
    this.capacity = size;
  }

  public Garage parkCar( Car car ) {
    if (isFull()) { 
      throw new RuntimeException( NoSpaceAvaliable ); 
    }

    if ( cars.stream().anyMatch( (each) -> each.equals( car ) ) ) {
        throw new RuntimeException( TwinCars ); 
    }

    fees += getFee( car );
    cars.add( car );
    return this;
  }

  public Garage unparkCar( Car car ) {
    if ( !cars.remove( car ) ) {
      throw new RuntimeException( MissingCar );
    }
    return this;
  }

  public boolean isFull() { return cars.size() == capacity; }
  public boolean isEmpty() { return cars.isEmpty(); }

  public int getNumCars() { return cars.size(); }
  public int getFee( Car car ) {     return 10;  }
  public int totalFees() {    return fees;  }

}