package garage;
import java.util.ArrayList;

public class Garage {
    private ArrayList<Car> cars = new ArrayList<Car>();
    private String CarAlreadyExistsCar = "Car Already Exists";
    public int getNumberOfCars() {
        return cars.size(); 
    }

    public Integer getNumberOfPeople() {
        return cars.stream().mapToInt(Car::getNumberOfPeople).sum();
    }
    public int getInvitedCar (){
        return (int) cars.stream().filter(car -> car.isType("Invited")).count();
    }
    public int getCarMemebers() {
        return (int) cars.stream().filter(car -> car.isType("Member")).count();
    }

    public Integer getReferenceCars() {
        return (int) cars.stream().filter(car -> car.isType("Reference")).count();
    }

    public Garage addInivitedCar(int people , String PlateNumber) {
        checkIfCarIsAlreadyParked(PlateNumber);
        cars.add(new InvitedCar(people, PlateNumber));
        return this;
    }

    public Integer getFee() {
        return cars.stream().mapToInt(Car::getFee).sum();
    }

    public Garage addMemberCar(int people , String PlateNumber) {
        checkIfCarIsAlreadyParked(PlateNumber);
        cars.add(new MemberCar(people, PlateNumber));
        return this;
    }

    public Garage addReferenceCar(int people,  String PlateNumber) {
        checkIfCarIsAlreadyParked(PlateNumber);
        cars.add(new ReferenceCar(people, PlateNumber));
        return this;
    }

    public void checkIfCarIsAlreadyParked(String PlateNumber) {
        cars.stream()
            .filter(car -> car.getPlateNumber().equals(PlateNumber))
            .findFirst()
            .ifPresent(car -> {
                throw new RuntimeException(CarAlreadyExistsCar);
            });
    }
    public Garage carLeaves(String PlateNumber) {
        cars.removeIf(car -> car.getPlateNumber().equals(PlateNumber));
        return this;
    }

}
