package garage;

public class ReferenceCar extends Car {
    
    public ReferenceCar(int numberOfPeople , String PlateNumber) {
        super(numberOfPeople , PlateNumber);
    }
    
    public int getFee() {
        return 50 + 60 * getNumberOfPeople();
    }
    
    public boolean isType(String type) {
        return type == "Reference";
    }
}
