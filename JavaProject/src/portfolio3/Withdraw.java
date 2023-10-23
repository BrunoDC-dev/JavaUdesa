package portfolio3;

public class Withdraw  extends Transaction{
    public Withdraw( char type, int value ) {
        super( type, value );
    }

    public boolean isDeposit() {
        return false;
    }
    public int value() {
        return value;
    }
    public int valueForBalance() {
        return value * -1;
    }
    public String reportDetail() {
        return "Withdraw: " + value;
    }
    
}
