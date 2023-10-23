package portfolio3;

public class Deposit extends Transaction {
    public Deposit( char type, int value ) {
        super( type, value );
    }

    public boolean isDeposit() {
        return true;
    }
    public int value() {
        return value;
    }
    public int valueForBalance() {
        return value;
    }
    public String reportDetail() {
        return "Deposit: " + value;
    }

}
