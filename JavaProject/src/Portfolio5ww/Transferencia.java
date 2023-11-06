package Portfolio5ww;

public class Transferencia {

    Account origin;
    Account destiny;
    int amount;

    public Transferencia(Account origin, Account destiny, int amount){
        this.origin = origin;
        this.destiny = destiny;
        this.amount = amount;
    }

    public Transferencia enviar(){
        if (amount == 0 || amount < 0 || origin == destiny){
            throw new RuntimeException();
        }
        origin.withdraw(amount);
        destiny.deposit(amount);
        return this;
    }

    public Account getOrigin() {
        return origin;
    }

    public Account getDestiny() {
        return destiny;
    }
}
