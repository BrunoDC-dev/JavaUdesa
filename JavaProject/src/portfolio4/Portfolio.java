package portfolio4;
import java.util.ArrayList;
import java.util.List;

public class Portfolio implements Accountable {
    List<Account> accounts = new ArrayList();
    List<Accountable> accountables= new ArrayList();


    public int balance() {
        return accounts.stream()
                       .mapToInt( each -> {
                            // if(each instanceof Account){
                            // if(each.getClass().equals( Account.class)){
                            if (Account.class.isInstance( each )) {
                                return ((Account)each).balance();
                            }else{
                                return (Portfolio.class.cast( each )).balance();
                            }
                       } )
                       .reduce( 0, (aBalance, acc) -> aBalance + acc );
                       // .sum();
        // return  accounts.stream()
        //                 .map( Account::balance )
        //                 .reduce( 0, (a,b) -> a + b );
    }
    
    public Accountable add(Account anAccount) {
        if(accounts.contains(anAccount)){
            throw new IllegalArgumentException("Account already exists in portfolio");
        }
        accounts.add(anAccount);
        return this;
    }

    public Accountable add(Accountable aPortfolio) {
        if(accountables.contains(aPortfolio)){
            throw new IllegalArgumentException("Account already exists in portfolio");
        }
        accountables.add(aPortfolio);
        return this;
    }

    public String report(String prefix) {
        String reportString = "Portfolio\n";
        for (Account account : accounts) {
            //concatenate the report 
            reportString +=  " Cuenta:\n" + account.reporte(prefix + "  ");
        }
        for (Accountable accountable : accountables) {
            //concatenate the report 
            reportString +=  accountable.report(prefix + "  ");
        }
        return reportString;
    }
    public int balance(String accountType) {
        return accountables.stream()
                       .filter( each -> each.getClass().getSimpleName().equals( accountType ) )
                       .mapToInt( each -> each.balance() )
                       .sum();
    }
    public String report() {
        return report("");
    }
    public boolean contains(Accountable anAccountable) {
        return accountables.contains(anAccountable);
    }
    public List<Accountable> getAccountables() {
        return accountables;
    }
}
