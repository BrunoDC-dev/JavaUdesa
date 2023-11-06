package Portfolio5ww;

import java.util.List;

public abstract class Accountable {
	
	public abstract int balance();	    
	public abstract String report(String prefix);
	public abstract boolean contains(Accountable anAccountable);
	public abstract List<Account> accounts();

	public String report() {
		return report("");
	}

}
