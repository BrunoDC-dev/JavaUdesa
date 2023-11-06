package garage;

public class InvitedCar extends Car {
    public InvitedCar(int numberOfPeople, String PlateNumber) {
        super(numberOfPeople, PlateNumber);
    }
    public int getFee() {
        return 150;
    }
    public boolean isType(String type) {
        return type == "Invited";
    }

}
