package garage;

public abstract class Car {
    private int numberOfPeople;
    private String PlateNumber;
    private String messageErroString = "There can't be less than 1 person in a car";
    public Car(int numberOfPeople, String PlateNumber) {
        if (numberOfPeople <= 0) {
            throw new   RuntimeException(messageErroString);
            
        }
        this.PlateNumber = PlateNumber;
        this.numberOfPeople = numberOfPeople;
    }
    public int getNumberOfPeople() {
        return numberOfPeople;
    }
    public abstract int getFee();
    public abstract boolean isType(String type); 
    public String getPlateNumber() {
        return PlateNumber;
    }
}
