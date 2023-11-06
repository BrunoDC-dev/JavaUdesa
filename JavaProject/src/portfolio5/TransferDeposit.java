package portfolio5;

public class TransferDeposit {
    private Transfer transfer;
     public TransferDeposit(Transfer aTransfer) {
      transfer = aTransfer;
     }
     public Object origin() {
      return transfer.origin();
     }
}
