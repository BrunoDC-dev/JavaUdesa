package garage;

public class MemberCar extends Car {
    public MemberCar(int numberOfPeople , String PlateNumber) {
        super(numberOfPeople, PlateNumber );
    }
    public int getFee() {
        return 100;
    }
    public boolean isType(String type) {
        return type == "Member";
    }
    
}
