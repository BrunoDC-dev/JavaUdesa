package garage0;

import java.util.ArrayList;
import java.util.List;

public class Garage {

  public int capacity;
  public List<Car> cars = new ArrayList<>();
  private int fees = 0;
  private List<Car> members = new ArrayList<>(); // List to store associated members

  public Garage( int size ) {
    this.capacity = size;
  }

  public boolean isEmpty() { return cars.isEmpty(); }

  public int getNumCars() { return cars.size(); }

  public Garage parkCar( Car car ) {
    if (cars.size() == capacity) { 
      throw new RuntimeException( "No space available" ); 
    }

    for (int i = 0; i < cars.size(); i++) {
      if (cars.get( i ).getPlateNumber().equals( car.getPlateNumber() )) {
        throw new RuntimeException( "Twin Cars!" ); 
      }
    }

    fees += getFee( car );
    cars.add( car );
    return this;
  }

  public Garage unparkCar( Car car ) {

    for (int i = 0; i < cars.size(); i++) {
      if (cars.get( i ).getPlateNumber().equals( car.getPlateNumber() )) {
        cars.remove( i );
        return this;
      }
    }
    throw new RuntimeException( "Missing car!" );
  }

  public int getFee( Car car ) {
    if (members.contains(car)) {
      return 5; // If the car is associated, the fee is 5
    } else {
      return 10; // Default fee for non-associated cars
    }
  }

  public int totalFees() {
    return fees;
  }
  
  public Garage associate(Car car) {
    members.add(car);
    return this;
  }
}
