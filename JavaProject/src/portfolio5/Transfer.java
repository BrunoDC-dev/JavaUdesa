package portfolio5;

public class Transfer {
    private int value;
    private TransferWithdraw origin;
    private TransferDeposit destiny;

    public Transfer( int value) {
        if (value == 0){
            throw new RuntimeException();
        }
        this.value = value;
        origin = new TransferWithdraw(this);
        destiny = new TransferDeposit(this);
    }
    public int value (){
        return value;
    }
    public TransferWithdraw origin(){
        return origin;
    }
    public TransferDeposit destiny(){
        return destiny;
    }
}
