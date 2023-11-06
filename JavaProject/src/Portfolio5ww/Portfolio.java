package Portfolio5ww;

import java.util.ArrayList;
import java.util.List;

public class Portfolio extends Accountable {
	private List<Accountable> accountables = new ArrayList<Accountable>();
	
	public int balance(){
		return accountables.stream().map( each -> each.balance())
			   .reduce(0, (aBalance, acc) -> aBalance + acc);
//		return accounts.stream().map(Account :: balance)
//                              .reduce(0, Integer::sum);
	}
	
	public void add(Accountable anAccountable) {
		if (contains(anAccountable)){
			throw new RuntimeException();
		}
		accountables.add(anAccountable);
	}
	

	public boolean contains(Accountable anAccountable) {
		List<Account> reminder = accounts();
		reminder.retainAll(anAccountable.accounts());
		return !reminder.isEmpty();
		
	}
	
	public String report(String prefix) {
		List<String> report = new ArrayList<String>();
		report.add(prefix + "Portfolio:");
		accountables.forEach((accountable)-> {report.add(accountable.report(prefix + " "));});
		
		return String.join("\n", report);
	}
	
	public List<Account> accounts(){
		return accountables.stream().map((accountable) -> accountable.accounts())
				       .reduce(new ArrayList<Account>(),(a,b) -> {a.addAll(b); return a;});
				
	}

}
