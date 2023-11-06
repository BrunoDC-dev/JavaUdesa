package portfolio5;

public class TransferWithdraw {
    private Transfer transfer;
 public TransferWithdraw(Transfer aTransfer) {
    transfer = aTransfer;
 }
    public Object destiny() {
        return transfer.destiny();
    }
}
